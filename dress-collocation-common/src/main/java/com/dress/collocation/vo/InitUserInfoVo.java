package com.dress.collocation.vo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Description:用户初始化vo
 * Created by xuejiahao on 2016/10/25.
 */
public class InitUserInfoVo {

    @NotNull
    private Long userId;
    @NotEmpty
    private String userNick;
    @NotNull
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
