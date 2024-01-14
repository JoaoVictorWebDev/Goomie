package com.Solutions.Goomie.service;

import com.Solutions.Goomie.enums.StatusEmail;
import com.Solutions.Goomie.model.EmailModel;
import com.Solutions.Goomie.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private JavaMailSender emailSender;
    public EmailModel enviarEmail(EmailModel emailModel) {

        emailModel.setSendDateEmail(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailTo());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e) {
            emailModel.setStatusEmail(StatusEmail.ERROR);

        } finally {

            return emailRepository.save(emailModel);
        }
    }
}