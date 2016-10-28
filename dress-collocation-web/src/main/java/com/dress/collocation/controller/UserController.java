package com.dress.collocation.controller;

import com.dress.collocation.exception.SystemBizException;
import com.dress.collocation.service.UserService;
import com.dress.collocation.util.ResponseUtils;
import com.dress.collocation.vo.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @RequestMapping("/verificationCode.app")
    public Object verificationCode(@Valid GetVerificationCodeVo getVerificationCodeVo) throws Exception {
        Object returnObj = null;
        try {
            switch (getVerificationCodeVo.getVerificationCodeType()) {
                case USER_REGISTER:
                    userService.registerGetVerificationCode(getVerificationCodeVo.getUserName());
                    break;
                case FIND_PWD:
                    userService.findPwdGetVerificationCode(getVerificationCodeVo.getUserName());
                    break;
                default:
                    throw new SystemBizException("请求参数出错");
            }
            returnObj = ResponseUtils.SuccessResponse();
        } catch (SystemBizException e) {
            returnObj = ResponseUtils.ErrorResponse(e);
        } catch (Exception e) {
            LOGGER.error("[获取用户注册验证错误][userName:" + getVerificationCodeVo.getUserName() + "]", e);
            returnObj = ResponseUtils.ErrorResponse(e);
        }
        return returnObj;
    }

    @ResponseBody
    @RequestMapping("/register.app")
    public Object register(@Valid UserRegisterVo userRegisterVo) throws Exception {
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

    @ResponseBody
    @RequestMapping("/initUserInfo.app")
    public Object initUserInfo(@Valid InitUserInfoVo initUserInfoVo) {
        Object returnObj;
        try {
            userService.initUserInfo(initUserInfoVo);
            returnObj = ResponseUtils.SuccessResponse();
        } catch (SystemBizException e) {
            returnObj = ResponseUtils.ErrorResponse(e);
        } catch (Exception e) {
            LOGGER.error("[用户信息初始化][userId:" + initUserInfoVo.getUserId() + "]", e);
            returnObj = ResponseUtils.ErrorResponse(e);
        }
        return returnObj;
    }

    @ResponseBody
    @RequestMapping("/login.app")
    public Object login(@Valid UserLoginVo userLoginVo) {
        Object returnObj;
        try {
            returnObj = ResponseUtils.SuccessResponse(userService.login(userLoginVo));
        } catch (SystemBizException e) {
            returnObj = ResponseUtils.ErrorResponse(e);
        } catch (Exception e) {
            LOGGER.error("[用户登录出错][userId:" + userLoginVo.getUserName() + "][错误信息:" + e.getMessage() + "]", e);
            returnObj = ResponseUtils.ErrorResponse(e);
        }
        return returnObj;
    }

    @ResponseBody
    @RequestMapping("/findPwd.app")
    public Object findPwd(@Valid UserFindPwdVo userFindPwdVo) {
        Object returnObj;
        try {
            userService.findPwd(userFindPwdVo);
            returnObj = ResponseUtils.SuccessResponse();
        } catch (SystemBizException e) {
            returnObj = ResponseUtils.ErrorResponse(e);
        } catch (Exception e) {
            LOGGER.error("[用户找回密码出错][userName:" + userFindPwdVo.getUserName() + "][错误信息:" + e.getMessage() + "]", e);
            returnObj = ResponseUtils.ErrorResponse(e);
        }
        return returnObj;
    }
}
