package event_center.ec.email;

import event_center.ec.model.binding.EmailBindingDTO;

public interface EmailService {
    void sendSimpleMessage(EmailBindingDTO emailBindingDTO);
}
