package com.shop.bike.service;

import com.shop.bike.service.dto.MailRequestDTO;
import com.shop.bike.vm.MailResponseVM;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class MailService {

	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private Configuration config;
	
	public MailResponseVM sendEmail(MailRequestDTO request, Map<String, Object> model) {
		MailResponseVM response = new MailResponseVM();
		MimeMessage message = sender.createMimeMessage();
		try {
			// set mediaType
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			// add attachment
			//helper.addAttachment("logo.png", new ClassPathResource("logo.png"));
			
			Template t = config.getTemplate("email-template.ftl"); //email-template.ftl
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
			
			helper.setTo(request.getTo());
			helper.setText(html, true);
			helper.setSubject(request.getSubject());
			helper.setFrom(request.getFrom());
			sender.send(message);
			
			response.setMessage("mail send to : " + request.getTo());
			response.setStatus(Boolean.TRUE);
			
		} catch (MessagingException | IOException | TemplateException e) {
			response.setMessage("Mail Sending failure : "+e.getMessage());
			response.setStatus(Boolean.FALSE);
		}
		
		return response;
	}
	
}
