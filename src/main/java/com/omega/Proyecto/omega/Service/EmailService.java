package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.DTO.EmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService implements IEmailService{
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendMail(EmailDTO emailDTO) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(emailDTO.getReceiver());
        helper.setSubject(emailDTO.getSubject());

        Context context = new Context();
        context.setVariable("name", emailDTO.getClientName());
        context.setVariable("destinations", emailDTO.getDestinations());
        context.setVariable("seller", emailDTO.getSellerName());
        context.setVariable("cost", emailDTO.getCost());
        context.setVariable("date", emailDTO.getSaleDate());
        String contentHtml = templateEngine.process("email", context);

        helper.setText(contentHtml, true);
        javaMailSender.send(message);
    }
}