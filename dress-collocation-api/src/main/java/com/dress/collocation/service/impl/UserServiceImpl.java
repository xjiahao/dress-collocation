package com.dress.collocation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dress.collocation.constants.MailConstants;
import com.dress.collocation.dao.RedisDao;
import com.dress.collocation.exception.SystemBizException;
import com.dress.collocation.mapper.UserDao;
import com.dress.collocation.pojo.User;
import com.dress.collocation.propelling.mail.SimpleMail;
import com.dress.collocation.redisbo.VerificationBo;
import com.dress.collocation.service.UserService;
import com.dress.collocation.task.MailSendTask;
import com.dress.collocation.util.Md5Utils;
import com.dress.collocation.util.VerificationCodeUtils;
import com.dress.collocation.vo.*;
import freemarker.template.TemplateException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;

import static com.dress.collocation.constants.Constants.*;

/**
 * Description: UserServiceImpl
 * Created by xuejiahao on 2016/10/23.
 */
@Service
public class UserServiceImpl implements UserService {

    public static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    UserDao userDao;

    @Autowired
    MailSendTask mailSendTask;

    @Autowired
    RedisDao redisDao;


    @Override
    @Transactional
    public UserRegisterVo registerUser(UserRegisterVo userRegisterVo) {
        Integer count = userDao.getCountByUserName(userRegisterVo.getUserName());
        if (count > 0) throw new SystemBizException("该账号已被注册");
        VerificationBo verificationBo = (VerificationBo) redisDao.get(REGISTER_USERNAME + userRegisterVo.getUserName());
        if (verificationBo == null || !userRegisterVo.getVerificationCode().equals(verificationBo.getVerificationCode())) {
            throw new SystemBizException("验证码错误");
        }
        User user = new User();
        user.setUserName(userRegisterVo.getUserName());
        user.setUserPwd(Md5Utils.MD5(userRegisterVo.getPassword(), userRegisterVo.getUserName()));
        if (userRegisterVo.getUserName().contains("@")) {
            user.setEmail(user.getUserName());
        } else {
            user.setPhoneNumber(user.getUserName());
        }
        Long userId = userDao.addUser(user);
        redisDao.remove(REGISTER_USERNAME + userRegisterVo.getUserName());
        userRegisterVo.setUserId(userId);
        userRegisterVo.setPassword(null);
        userRegisterVo.setVerificationCode(null);
        return userRegisterVo;
    }


    @Override
    public void registerGetVerificationCode(String userName) throws IOException, TemplateException, MessagingException {
        Integer count = userDao.getCountByUserName(userName);
        if (count > 0) throw new SystemBizException("该" + (userName.contains("@") ? "邮箱" : "手机号") + "已经被注册");
        sendVerificationCode(userName, MailConstants.VERIFICATE_REGISTER, REGISTER_USERNAME);
    }

    @Override
    public void findPwdGetVerificationCode(String userName) throws IOException, TemplateException {
        Integer count = userDao.getCountByUserName(userName);
        if (count == 0) throw new SystemBizException("该" + (userName.contains("@") ? "邮箱" : "手机号") + "未被注册");
        sendVerificationCode(userName, MailConstants.FIND_PWD_REGISTER, FIND_PWD_USERNAME);
    }

    @Override
    public JSONObject loginGetVerificationCode(String userName) {
        JSONObject object = new JSONObject();
        User user = userDao.getUserByUserName(userName);
        if (user == null) throw new SystemBizException("用户不存在");
        VerificationCodeImg verificationCodeImg = VerificationCodeUtils.buildImg();
        redisDao.set(ERROR_PWD_CODE_USERID + user.getUserId(), new VerificationBo(verificationCodeImg.getVerificationCode(), new Date().getTime()), 60 * 1000L);
        object.put("code", verificationCodeImg.getBase64());
        return object;
    }

    @Override
    public void initUserInfo(InitUserInfoVo initUserInfoVo) {
        User user = userDao.getUserByUserId(initUserInfoVo.getUserId());
        if (user == null) throw new SystemBizException("用户不存在");
        if (user.getLastLoginTime() != null) throw new SystemBizException("不能进行该操作");
        User newUser = new User();
        newUser.setUserNick(initUserInfoVo.getUserNick());
        newUser.setSex(initUserInfoVo.getSex());
        userDao.updateUserByUserId(newUser);
    }


    @Override
    public JSONObject login(UserLoginVo userLoginVo) {
        JSONObject obj = new JSONObject();
        User user = userDao.getUserByUserName(userLoginVo.getUserName());
        if (user == null)
            throw new SystemBizException("该" + (userLoginVo.getUserName().contains("@") ? "邮箱" : "手机号") + "已经被注册");
        Integer errorCount = (Integer) redisDao.get(ERROR_PWD_USERNAME + user.getUserId());
        if (userLoginVo.getVerificationCode() != null) {
            VerificationBo vo = (VerificationBo) redisDao.get(ERROR_PWD_CODE_USERID + user.getUserId());
            if (vo == null || !userLoginVo.getVerificationCode().equals(vo.getVerificationCode())) {
                throw new SystemBizException("验证码错误");
            }
        } else {
            //判断是否需要进行验证码验证
            if (errorCount != null && errorCount == 5) {
                //需要验证
                obj.put("needVerificate", 1);
                return obj;
            }
        }
        if (!userLoginVo.getUserName().equals(Md5Utils.MD5(userLoginVo.getPassword(), user.getUserName()))) {
            if (errorCount != null) {
                errorCount += 1;
                if (errorCount == 5) {
                    //需要验证
                    obj.put("needVerificate", 1);
                    return obj;
                }
            } else {
                errorCount = 1;
            }
            redisDao.set(ERROR_PWD_USERNAME + user.getUserId(), errorCount, 24 * 60 * 60 * 1000L);
            throw new SystemBizException("密码错误");
        }
        if (user.getLastLoginTime() == null)
            LOGGER.info("[用户第一次登录][userId:" + user.getUserId() + "][userName" + userLoginVo.getUserName() + "]");
        User newUser = new User();
        newUser.setUserId(user.getUserId());
        newUser.setLastLoginTime(new Date());
        userDao.updateUserByUserId(newUser);
        redisDao.remove(ERROR_PWD_USERNAME + user.getUserId());
        redisDao.remove(ERROR_PWD_CODE_USERID + user.getUserId());
        obj.put("needVerificate", 0);
        return obj;
    }

    @Override
    public void findPwd(UserFindPwdVo userFindPwdVo) {
        User user = userDao.getUserByUserName(userFindPwdVo.getUserName());
        if (user == null) throw new SystemBizException("该账号未注册");
        VerificationBo verificationBo = (VerificationBo) redisDao.get(FIND_PWD_USERNAME + userFindPwdVo.getUserName());
        if (verificationBo == null || !userFindPwdVo.getVerificationCode().equals(verificationBo.getVerificationCode())) {
            throw new SystemBizException("验证码错误");
        }
        String newPassword = Md5Utils.MD5(userFindPwdVo.getNewPassword(), user.getUserName());
        User newUser = new User();
        newUser.setUserId(user.getUserId());
        newUser.setUserPwd(newPassword);
        userDao.updateUserByUserId(user);
        LOGGER.info("[用户密码重置][userId:" + user.getUserId() + "][userName:" + user.getUserName() + "]");
        redisDao.remove(FIND_PWD_USERNAME + userFindPwdVo.getUserName());
    }

    /**
     * 发送验证码
     *
     * @param userName             userName
     * @param mailType             mailType
     * @param verificationCodeType verificationCodeType
     * @throws IOException
     * @throws TemplateException
     */
    private void sendVerificationCode(String userName, int mailType, String verificationCodeType) throws IOException, TemplateException {

        VerificationBo verificationBo = (VerificationBo) redisDao.get(verificationCodeType + userName);
        if (verificationBo != null && System.currentTimeMillis() - verificationBo.getCreateTime() < 60 * 1000) {
            throw new SystemBizException("获取验证码过于频繁，请稍后再试");
        }
        String verificationCode = VerificationCodeUtils.buildVerificationCode();
        redisDao.set(verificationCodeType + userName, new VerificationBo(verificationCode, new Date().getTime()), 10 * 60 * 1000L);
        if (userName.contains("@")) {
            //邮箱注册
            mailSendTask.addMailSendTask(new SimpleMail(verificationCode, mailType, userName));
        } else {
            //手机注册 暂不支持
            throw new SystemBizException("暂不支持手机号注册");
        }
    }


}
