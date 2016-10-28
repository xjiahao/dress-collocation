package com.dress.collocation.vo;

import javax.validation.constraints.NotNull;

/**
 * Description:
 * Created by xuejiahao on 2016/10/25.
 */
public class UserInfoVo {

    @NotNull
    private Long userId;

    private String userNick;

    private String email;

    private String phoneNumber;

    private Integer sex;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
