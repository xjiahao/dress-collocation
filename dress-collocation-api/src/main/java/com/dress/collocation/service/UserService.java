package com.dress.collocation.service;


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
}
