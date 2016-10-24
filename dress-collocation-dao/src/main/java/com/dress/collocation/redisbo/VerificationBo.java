package com.dress.collocation.redisbo;

/**
 * Description:
 * Created by xuejiahao on 2016/10/23.
 */
public class VerificationBo extends BaseRedisBo {

    private static final long serialVersionUID = -2562327579900624649L;

    private String verificationCode;

    private Long createTime;

    public VerificationBo() {

    }

    public VerificationBo(String verificationCode, Long createTime) {
        this.verificationCode = verificationCode;
        this.createTime = createTime;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
