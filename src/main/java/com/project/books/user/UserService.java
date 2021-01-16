package com.project.books.user;

import com.project.books.exceptions.BadRequestException;
import com.project.books.exceptions.NotFoundException;
import com.project.books.exceptions.ResourceNotFoundException;
import com.project.books.exceptions.UserAlreadyExist;
import com.project.books.user.address.Address;
import com.project.books.user.role.UserRole;
import com.project.books.user.role.UserRoleConfig;
import com.project.books.user.role.UserRoleRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Component
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleConfig userRoleConfig;
    private final UserMapper userMapper;
    private final UserRoleRepository userRoleRepository;;

    public UserDto createUser(UserDto userDto) {
//        Role userRole = new Role();
//        userRole.setUserRole(roleConfiguration.getDefaultRole());

        String name = userDto.getName();
        String surname = userDto.getSurname();
        String login = userDto.getLogin();
        String password = userDto.getPassword();
        String email = userDto.getEmail();
        Address address = userDto.getAddress();
        LocalDateTime dateOfRegistration = LocalDateTime.now();
        LocalDateTime dateConverterOfRegistration = dateConverter(dateOfRegistration);

        if (name.isBlank() || name.isEmpty()) {
            throw new BadRequestException("Pole z imieniem nie może pozostać puste");
        }
        if (surname.isBlank() || surname.isEmpty()) {
            throw new BadRequestException("Pole z nazwiskiem nie może pozostać puste");
        }
        if (login.isEmpty() || login.isBlank()) {
            throw new BadRequestException("Pole z loginem nie może pozostać puste");
        }
        if (password.length() < 5 ) {
            throw new BadRequestException("Hasło musi zawierać conajmniej 5 dowolnych znaków");
        }
        if (email.isBlank() || email.isEmpty()) {
            throw new BadRequestException("Pole z adresem e-mail nie może pozostać puste");
        }
        if (!mailChecker(email)) {
            throw new BadRequestException("Niepoprawny adres e-mail");
        }
//        if (loginExistChecker(login)){
//            throw new UserAlreadyExist("Użytkownik z podanym loginem już istnieje");
//        }

        User user = User.builder()
                .name(name)
                .surname(surname)
                .login(login)
                .password(password)
                .email(email)
                .dateOfRegistration(dateConverterOfRegistration)
                .address(address)
                .build();

//        if (!roleRepository.findByUserRole(userRole.getUserRole()).isPresent()) {
//            user.setRole(userRole);
//        }

        return userMapper.mapToUserDto(userRepository.save(user));
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie ma użytownika o podanym id " + id));
    }

    public List<User> getAllUsers(){
            return userRepository.findAll();
        }

    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

//    private User returnUserIfExistsById(Long id) {
//        Optional<User> userById = userRepository.findById(id);
//        if (userById.isEmpty()) {
//            throw new ResourceNotFoundException("Uzytkownik o id: {" + id + "} nie odnaleziono");
//        } else
//            return userById.get();
//    }

    private boolean mailChecker(String email) {
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(email);
        boolean matchFound = m.matches();

        return matchFound;
    }
//    private boolean loginExistChecker(String login) {
//        if (userRepository.findByLogin(login) != null) {
//            System.out.println("Login zajęty");
//        }
//        return true;
//    }

    private LocalDateTime dateConverter(LocalDateTime date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm");
        String format = date.format(dateTimeFormatter);
        return LocalDateTime.parse(format, dateTimeFormatter);
    }

    public UserDto changeRole(User user, String role) {
        UserRole userRole = new UserRole();
        userRoleConfig.getRoles().stream().filter(r -> r.equals(role))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Podana rola nie istnieje" + role));
        userRole.setUserRole(role);
        user.setUserRole(userRole);
        return userMapper.mapToUserDto(userRepository.save(user));
    }
}
