/**
 *
 */
package com.dress.collocation.propelling.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;


/**
 * Description:邮件发送
 *
 * @author xuejiahao  2016年10月23日
 */
public class MailSender {
    /**
     * 邮箱session
     */
    private transient Session session;

    private String userNick;

    private String userName;


    public MailSender() {

    }


    /**
     * Description:发送邮件  2016年10月23日
     *
     * @param recipient
     * @param subject
     * @param content
     * @throws AddressException
     * @throws MessagingException
     * @author xuejiahao
     */
    private void send(String recipient, String subject, Object content)
            throws MessagingException, UnsupportedEncodingException {
        // 创建mime类型邮件
        MimeMessage message = new MimeMessage(session);
        //设置发件人昵称
        String nick = MimeUtility.encodeText(userNick);
        // 设置发信人
        message.setFrom(new InternetAddress(nick + "<" + userName + ">"));
        // 设置收件人
        message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
        // 设置主题
        message.setSubject(subject);
        // 设置邮件内容
        message.setContent(content.toString(), "text/html;charset=utf-8");
        // 设置发送时间
        message.setSentDate(new Date());
        //从session中取mail.smtp.protocol指定协议的Transport
        Transport transport = session.getTransport();
        //建立与指定的SMTP服务器的连接
        transport.connect();//此时不需要任务参数
        //发给所有指定的收件人,若使用message.getAllRecipients()则还包括抄送和暗送的人
        transport.sendMessage(message, message.getRecipients(RecipientType.TO));
        //关闭连接
        transport.close();
    }

    /**
     * Description:群发邮件  2016年10月23日
     *
     * @param recipients
     * @param subject
     * @param content
     * @throws AddressException
     * @throws MessagingException
     * @author xuejiahao
     */
    private void send(List<String> recipients, String subject, Object content)
            throws MessagingException, UnsupportedEncodingException {
        // 创建mime类型邮件
        MimeMessage message = new MimeMessage(session);
        //设置发件人昵称
        String nick = MimeUtility.encodeText(userNick);
        // 设置发信人
        message.setFrom(new InternetAddress(nick + "<" + userName + ">"));
        // 设置收件人们
        int num = recipients.size();
        InternetAddress[] addresses = new InternetAddress[num];
        for (int i = 0; i < num; i++) {
            addresses[i] = new InternetAddress(recipients.get(i));
        }

        message.setRecipients(RecipientType.TO, addresses);
        // 设置主题
        message.setSubject(subject);
        // 设置邮件内容
        message.setContent(content.toString(), "text/html;charset=utf-8");
        // 发送
        // 设置发送时间
        message.setSentDate(new Date());
        //从session中取mail.smtp.protocol指定协议的Transport
        Transport transport = session.getTransport();
        //建立与指定的SMTP服务器的连接
        transport.connect();//此时不需要任务参数
        //发给所有指定的收件人,若使用message.getAllRecipients()则还包括抄送和暗送的人
        transport.sendMessage(message, message.getRecipients(RecipientType.TO));
        //关闭连接
        transport.close();
    }

    /**
     * Description:单发邮件  2016年10月23日
     *
     * @param mail      邮件对象
     * @throws AddressException
     * @throws MessagingException
     * @author xuejiahao
     */
    public void send(SimpleMail mail)
            throws AddressException, MessagingException, UnsupportedEncodingException {
        send(mail.getUserName(), mail.getSubject(), mail.getContent());
    }

    /**
     * Description:群发邮件  2016年10月23日
     *
     * @param recipients
     * @param mail
     * @throws AddressException
     * @throws MessagingException
     * @author xuejiahao
     */
    public void send(List<String> recipients, SimpleMail mail)
            throws AddressException, MessagingException, UnsupportedEncodingException {
        send(recipients, mail.getSubject(), mail.getContent());
    }


    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
