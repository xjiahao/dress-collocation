/**
 * 
 */
package com.dress.collocation.propelling.mail;

import com.dress.collocation.constants.MailConstants;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:邮件对象
 * @author xuejiahao  2015年7月17日
 * 
 */
public class SimpleMail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String subject;
	private String content;
	
	public SimpleMail(){
		
	}
	
	public SimpleMail(String verificationCode,int type ,final String userName) throws IOException, TemplateException {
		this.userName = userName;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,String> params = new HashMap<String, String>();
		params.put("verificationCode",verificationCode);
		params.put("date",sdf.format(new Date()));
		switch (type){
			case MailConstants.VERIFICATE_REGISTER :
				this.subject = MailConstants.REGISTER_SUBJECT;
				this.content = TemplateFactory.generateHtmlFromFtl(MailConstants.REGISTER_TEMPLATE,params);
				break;
			case MailConstants.VERIFICATE_FIND_PWD :

				this.subject = MailConstants.FIND_PWD_SUBJECT;
				this.content = TemplateFactory.generateHtmlFromFtl(MailConstants.FIND_PWD_TEMPLATE,params);
				break;
		}
	}
	
	public SimpleMail(String subject,String content){
		this.subject = subject;
		this.content = content;
	}
	
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
