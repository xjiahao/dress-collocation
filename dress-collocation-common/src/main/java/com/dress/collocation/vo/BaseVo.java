package com.dress.collocation.vo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Description:
 * Created by xuejiahao on 2016/10/29.
 */
public class BaseVo {
    @NotEmpty
    protected String appVersion;

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}
