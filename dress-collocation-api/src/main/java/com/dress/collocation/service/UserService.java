package com.dress.collocation.service;


import com.alibaba.fastjson.JSONObject;
import com.dress.collocation.vo.InitUserInfoVo;
import com.dress.collocation.vo.UserFindPwdVo;
import com.dress.collocation.vo.UserLoginVo;
import com.dress.collocation.vo.UserRegisterVo;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * Description: UserService
 * Created by xuejiahao on 2016/10/23.
 */
public interface UserService {

    /**
     * 注册用户
     *
     * @param userRegisterVo 用户注册对象
     * @return
     */
    UserRegisterVo registerUser(UserRegisterVo userRegisterVo);

    /**
     * 获取验证码
     *
     * @param userName 用户名
     */
    void registerGetVerificationCode(String userName) throws IOException, TemplateException, MessagingException;

    /**
     * 找回密码获取验证码
     * @param userName userName
     */
    void findPwdGetVerificationCode(String userName) throws IOException, TemplateException;

    /**
     * 获取登录的验证码图片
     * @param userName userName
     * @return
     */
    JSONObject loginGetVerificationCode(String userName);

    /**
     * 初始化修改用户
     * @param initUserInfoVo initUserInfoVo
     */
    void initUserInfo(InitUserInfoVo initUserInfoVo);

    /**
     * 用户注册
     * @param userLoginVo userLoginVo
     */
    JSONObject login(UserLoginVo userLoginVo);

    /**
     * 找回密码
     * @param userFindPwdVo userFindPwdVo
     */
    void findPwd(UserFindPwdVo userFindPwdVo);
}
