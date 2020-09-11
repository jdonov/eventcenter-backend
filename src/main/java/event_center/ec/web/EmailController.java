package event_center.ec.web;

import event_center.ec.email.EmailService;
import event_center.ec.model.binding.EmailBindingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/email")
public class EmailController {
    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<String> sendEmails(@RequestBody EmailBindingDTO emailBindingDTO) {
        this.emailService.sendSimpleMessage(emailBindingDTO);
        return ResponseEntity.ok().body("Messages sent successfully!");
    }
}
