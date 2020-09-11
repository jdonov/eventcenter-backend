package event_center.ec.service.impl;

import event_center.ec.exception.PasswordsNotMatchException;
import event_center.ec.exception.UserAlreadyExistsException;
import event_center.ec.model.binding.UserRegisterDTO;
import event_center.ec.model.entity.Role;
import event_center.ec.model.entity.User;
import event_center.ec.model.service.UserServiceDTO;
import event_center.ec.model.service.UserServiceNameDTO;
import event_center.ec.repository.UserRepository;
import event_center.ec.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.
                map(this::map).
                orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found!"));
    }

    @Override
    public UserServiceDTO registerUser(UserRegisterDTO userDTO) {
        this.userRepository.findByUsername(userDTO.getEmail()).ifPresent(u -> {
            throw new UserAlreadyExistsException(String.format("User with username '%s' already exists.", userDTO.getEmail()));
        });
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            throw new PasswordsNotMatchException("Passwords doesn't match!");
        }
        User user = this.modelMapper.map(userDTO, User.class);
        user.setUsername(userDTO.getEmail());
        user.setAuthorities(Set.of(Role.valueOf("ADMIN")));
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user = this.userRepository.saveAndFlush(user);
        UserServiceDTO userServiceDTO = this.modelMapper.map(user, UserServiceDTO.class);
        userServiceDTO.setEmail(user.getUsername());
        return userServiceDTO;
    }

    @Override
    public UserServiceDTO getUserById(String id) {
        return this.modelMapper.map(this.userRepository.findById(id), UserServiceDTO.class);
    }

    @Override
    public UserServiceDTO getUserByUsername(String username) {
        User user = this.userRepository.findByUsername(username).orElse(null);
        UserServiceDTO userServiceDTO = new UserServiceDTO();
        if (user != null) {
            userServiceDTO = this.modelMapper.map(user, UserServiceDTO.class);
            userServiceDTO.setEmail(user.getUsername());
        }
        return user == null ? null : userServiceDTO;
    }

    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public UserServiceNameDTO getUserNameById(String id) {
        User user = this.userRepository.findById(id).orElse(null);
        return this.modelMapper.map(user, UserServiceNameDTO.class);
    }

    @Override
    public UserServiceNameDTO getOwnerName() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userRepository.findByUsername(username).orElse(null);
        return new UserServiceNameDTO(){{
            setName(user.getUsername());
            setId(user.getId());
        }};
    }

    private UserDetails map(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.
                        getAuthorities().
                        stream().
                        map(this::map).
                        collect(Collectors.toList())
        );
    }
    private GrantedAuthority map(Role role) {
        return new SimpleGrantedAuthority(role.name());
    }
}
