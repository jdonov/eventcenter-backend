package event_center.ec.web;

import event_center.ec.model.binding.UserRegisterDTO;
import event_center.ec.model.service.UserServiceDTO;
import event_center.ec.model.service.UserServiceNameDTO;
import event_center.ec.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.http.HttpRequest;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("http://localhost")
public class UserController {
    private final UserService userService;
    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserServiceDTO> register(@RequestBody UserRegisterDTO userRegisterDTO) throws ServletException {
        UserServiceDTO createdUser = this.userService.registerUser(userRegisterDTO);
        URI location = MvcUriComponentsBuilder.fromMethodName(UserController.class, "register", UserServiceDTO.class)
                .pathSegment("{id}").buildAndExpand(createdUser.getId()).toUri();
        LOGGER.info("User created: {}", location);
        return ResponseEntity.created(location).body(createdUser);
    }

    @GetMapping
    public ResponseEntity<UserServiceNameDTO> getUserName() {
        UserServiceNameDTO userServiceNameDTO = this.userService.getOwnerName();
        return ResponseEntity.ok(userServiceNameDTO);
    }

}
