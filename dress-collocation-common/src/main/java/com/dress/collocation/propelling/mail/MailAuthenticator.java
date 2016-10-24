/**
 *
 */
package com.dress.collocation.propelling.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Description:邮箱登录验证
 *
 * @author xuejiahao 2015年7月17日
 *
 */
public class MailAuthenticator extends Authenticator {
    /**
     * 用户名（登录邮箱）
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    public MailAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    String getPassword() {
        return password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }

    String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
