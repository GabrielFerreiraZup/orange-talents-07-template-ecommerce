package br.com.zup.mercadolivre.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String remetente, String corpo, String assunto ){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("gabriel_ferreira@id.uff.br");
        message.setTo(remetente);
        message.setText(corpo);
        message.setSubject(assunto);
        mailSender.send(message);
        System.out.println("teoricamente email foi...");
    }

}
