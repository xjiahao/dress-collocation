package com.dress.collocation.task;

import com.dress.collocation.propelling.mail.MailSender;
import com.dress.collocation.propelling.mail.SimpleMail;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Description:
 * Created by xuejiahao on 2016/10/23.
 */
public class MailSendTask implements InitializingBean{

    @Autowired
    MailSender mailSender;

    private LinkedBlockingQueue<SimpleMail> mailLinkedBlockingQueue = new LinkedBlockingQueue<SimpleMail>();

    public void addMailSendTask(SimpleMail simpleMail){
        if(simpleMail != null && !mailLinkedBlockingQueue.contains(simpleMail)){
            mailLinkedBlockingQueue.offer(simpleMail);
        }
    }

    /**
     * Invoked by a BeanFactory after it has set all bean properties supplied
     * (and satisfied BeanFactoryAware and ApplicationContextAware).
     * <p>This method allows the bean instance to perform initialization only
     * possible when all bean properties have been set and to throw an
     * exception in the event of misconfiguration.
     *
     * @throws Exception in the event of misconfiguration (such
     *                   as failure to set an essential property) or if initialization fails.
     */
    @Override
    public void afterPropertiesSet() throws Exception{
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SimpleMail mail = null;
                    while ((mail = mailLinkedBlockingQueue.take()) !=null){
                        mailSender.send(mail);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (AddressException e) {
                    e.printStackTrace();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
