package event_center.ec.email;

import event_center.ec.model.binding.EmailBindingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender emailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendSimpleMessage(EmailBindingDTO emailBindingDTO) {
        String subject = emailBindingDTO.getSubject();
        String text = emailBindingDTO.getTextMessage();
        SimpleMailMessage message = new SimpleMailMessage();
        emailBindingDTO.getTo().forEach(e -> {
            message.setFrom("noreply@eventcenter.com");
            message.setTo(e);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
        });
    }
}
