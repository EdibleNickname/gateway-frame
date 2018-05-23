package com.can.util.email;

import com.can.util.email.model.Email;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @description: 邮件工具
 * @author: LCN
 * @date: 2018-05-23 16:14
 */

public class EmailUtil {

	private static Logger logger = LoggerFactory.getLogger(EmailUtil.class);

	/** 发送方邮件地址 */
	@Value("${spring.mail.username:2659471464@qq.com}")
	private String senderEmail;

	/** 邮件服务器的授权码*/
	@Value("${spring.mail.password:ijxmrdmxhhecdjai}")
	private String authCode;

	/** 邮件服务的地址 */
	@Value("${spring.mail.host:smtp.qq.com}")
	private String serverHost;

	/** 邮件服务器端口 */
	@Value("${spring.mail.port:465}")
	private String serverPort;

	/** 邮件编码 */
	@Value("${spring.mail.default-encoding:UTF-8}")
	private String encoding;

	/**
	 * 发送html邮件
	 * @param email
	 */
	@Async
	public void sendHtmlEmail(Email<?> email) {
		try {
			Properties properties = getEnvPro();

			Template emialTemplate = getTemplate(email.getTempleName());
			//建立会话
			Session session = Session.getDefaultInstance(properties);
			//设置为debug模式
			session.setDebug(true);

			MimeMessage mimeMessage = getMimeMessage(session,emialTemplate, email);

			startSendEmail(session,mimeMessage);

			logger.info("给-----{}-----的邮件发送成功", email.getReceiverEmail());

		} catch (Exception e) {
			logger.error("邮件发送出现异常-------->", e);
		}
	}

	/**
	 * 获取发送邮件需要的环境属性
	 */
	private Properties getEnvPro() {

		Properties properties = new Properties();

		properties.setProperty("mail.host", serverHost);
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.port", serverPort);

		return properties;
	}

	/**
	 * 获取内容模板
	 * @param templateName 模板的名字
	 * @return
	 */
	private Template getTemplate(String templateName) throws Exception{
		Template emailTemplate = null;
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
		cfg.setClassLoaderForTemplateLoading(ClassLoader.getSystemClassLoader(),"ftl");
		emailTemplate = cfg.getTemplate(templateName, encoding);
		return emailTemplate ;
	}

	/**
	 * 生成邮件内容
	 * @param session  session会话
	 * @param contentTemplate 内容模板
	 * @param email 邮件相关内容
	 * @return
	 */
	private MimeMessage getMimeMessage(Session session, Template contentTemplate, Email<?> email ) throws Exception {

		MimeMessage mimeMessage = new MimeMessage(session);

		mimeMessage.addRecipients(Message.RecipientType.TO, email.getReceiverEmail());
		mimeMessage.setFrom(senderEmail);
		mimeMessage.setSubject(email.getSubject());

		// 设置整封邮件的MIME消息体为混合的组合关系
		MimeMultipart mixed = new MimeMultipart("mixed");
		mimeMessage.setContent(mixed);

		// 创建邮件正文
		MimeBodyPart content = new MimeBodyPart();
		mixed.addBodyPart(content);

		//设置正文的MIME类型
		MimeMultipart bodyMimeMultipart = new MimeMultipart("related");

		//将bodyMimeMultipart添加到正文消息体中
		content.setContent(bodyMimeMultipart);

		//正文的HTML部分
		MimeBodyPart bodyPart = new MimeBodyPart();
		StringWriter out = new StringWriter();

		// 如果 key != null,发送的的内容为普通的数据类型
		if(null != email.getKey()) {
			Map root = new HashMap(16);
			root.put(email.getKey(), email.getContent());
			contentTemplate.process(root, out);
		} else {
			contentTemplate.process(email.getContent(), out);
		}

		bodyPart.setContent(out.toString(),"text/html;charset=utf-8");

		//将正文的HTML添加到bodyMimeMultipart中
		bodyMimeMultipart.addBodyPart(bodyPart);
		mimeMessage.saveChanges();

		return mimeMessage;
	}

	/**
	 * 发送邮件
	 * @param session session会话
	 * @param mimeMessage 邮件内容
	 * @throws MessagingException
	 */
	private void startSendEmail(Session session, MimeMessage mimeMessage) throws MessagingException {
		Transport transport = session.getTransport();
		transport.connect(serverHost, senderEmail, authCode);
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
		transport.close();
	}

}
