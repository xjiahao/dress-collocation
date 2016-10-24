package com.dress.collocation.controller;

import com.dress.collocation.exception.SystemBizException;
import com.dress.collocation.service.UserService;
import com.dress.collocation.util.ResponseUtils;
import com.dress.collocation.vo.UserRegisterVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Description: 用户control
 * Created by xuejiahao on 2016/10/23.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    public static final Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/register/verificationCode.app")
    public Object test(@RequestParam String userName) throws Exception {
        Object returnObj = null;
        try {
            userService.registerGetVerificationCode(userName);
            returnObj = ResponseUtils.SuccessResponse();
        } catch (SystemBizException e) {
            returnObj = ResponseUtils.ErrorResponse(e);
        } catch (Exception e) {
            LOGGER.error("[获取用户注册验证错误][userName:" + userName + "]", e);
            returnObj = ResponseUtils.ErrorResponse(e);
        }
        return returnObj;
    }

    @ResponseBody
    @RequestMapping("/register/register.app")
    public Object test(@Valid UserRegisterVo userRegisterVo) throws Exception {
        Object returnObj;
        try {
            userService.registerUser(userRegisterVo);
            returnObj = ResponseUtils.SuccessResponse(userRegisterVo);
        } catch (SystemBizException e) {
            returnObj = ResponseUtils.ErrorResponse(e);
        } catch (Exception e) {
            LOGGER.error("[用户注册失败][userName:" + userRegisterVo.getUserName() + "]", e);
            returnObj = ResponseUtils.ErrorResponse(e);
        }
        return returnObj;
    }
}
