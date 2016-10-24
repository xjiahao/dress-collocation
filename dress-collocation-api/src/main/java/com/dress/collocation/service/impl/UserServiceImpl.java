package com.dress.collocation.service.impl;

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
import com.dress.collocation.vo.UserRegisterVo;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;

import static com.dress.collocation.constants.Constants.REGISTER_USERNAME;

/**
 * Description: UserServiceImpl
 * Created by xuejiahao on 2016/10/23.
 */
@Service
public class UserServiceImpl implements UserService {

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
        userRegisterVo.setUserId(userId);
        userRegisterVo.setPassword(null);
        userRegisterVo.setVerificationCode(null);
        return userRegisterVo;
    }


    @Override
    public void registerGetVerificationCode(String userName) throws IOException, TemplateException, MessagingException {

        Integer count = userDao.getCountByUserName(userName);
        if (count > 0) throw new SystemBizException("该" + (userName.contains("@") ? "邮箱" : "手机号") + "已经被注册");
        VerificationBo verificationBo = (VerificationBo) redisDao.get(REGISTER_USERNAME + userName);
        if (verificationBo != null && System.currentTimeMillis() - verificationBo.getCreateTime() < 60 * 1000) {
            throw new SystemBizException("获取验证码过于频繁，请稍后再试");
        }
        String verificationCode = VerificationCodeUtils.buildVerificationCode();
        redisDao.set(REGISTER_USERNAME + userName, new VerificationBo(verificationCode, new Date().getTime()), 10 * 60L);
        if (userName.contains("@")) {
            //邮箱注册
            mailSendTask.addMailSendTask(new SimpleMail(verificationCode, MailConstants.VERIFICATE_REGISTER, userName));
        } else {
            //手机注册 暂不支持
            throw new SystemBizException("暂不支持手机号注册");
        }
    }
}
