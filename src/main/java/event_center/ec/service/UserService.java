package event_center.ec.service;

import event_center.ec.model.binding.UserRegisterDTO;
import event_center.ec.model.entity.User;
import event_center.ec.model.service.UserServiceDTO;
import event_center.ec.model.service.UserServiceNameDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserServiceDTO registerUser(UserRegisterDTO user);

    UserServiceDTO getUserById(String id);

    UserServiceDTO getUserByUsername(String username);

    User getUser(String username);

    UserServiceNameDTO getUserNameById(String id);

    UserServiceNameDTO getOwnerName();
}
