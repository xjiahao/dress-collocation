package com.dress.collocation.vo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Description:用户登录vo
 * User:Xue jiahao (xuejiahao@raycloud.com)
 * Date: 16/10/28
 * Time: 22:17
 * Version: 1.0
 **/
public class UserLoginVo extends BaseVo{

    @NotEmpty
    private String userName;
    @NotEmpty
    private String password;
    private String verificationCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
