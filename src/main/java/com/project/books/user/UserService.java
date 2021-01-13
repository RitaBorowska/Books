package com.project.books.user;

import com.project.books.exceptions.BadRequestException;
import com.project.books.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
//@Validated
public class UserService {

    private final UserRepository userRepository;

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

    public User createUser(  UserDefinition userDefinition) {

        String name = userDefinition.getName();
        String surname = userDefinition.getSurname();
        String login = userDefinition.getLogin();
        String password = userDefinition.getPassword();
        String email = userDefinition.getEmail();
        LocalDateTime dateOfRegistration = LocalDateTime.now();
        LocalDateTime dataConverterOfRegistration = dateConverter(dateOfRegistration);

        if(name.isBlank() || name.isEmpty()){
            throw new BadRequestException("Pole z imieniem nie może pozostać puste");

        }
        if (surname.isBlank() || surname.isEmpty()){
            throw new BadRequestException("Pole z nazwiskiem nie może pozostać puste");
        }
        if (login.isEmpty() || login.isBlank()){
            throw new BadRequestException("Pole z loginem nie może pozostać puste");
        }
//        if (userRepository.findByLogin(userDefinition.getLogin()) != null){
//            throw new UserAlredyExists("Użytkownik z podanym loginem już istnieje");
//        }
        if (password.length() < 5) {
            throw new BadRequestException("Hasło musi zawierać conajmniej 5 dowolnych znaków");
        }
        if (email.isBlank() || email.isEmpty()){
            throw new BadRequestException("Pole z adresem e-mail nie może pozostać puste");
        }
        if (!mailChecker(email)){
            throw  new BadRequestException("Niepoprawny adres e-mail");
        }


        User user = User.builder()
                .name(name)
                .surname(surname)
                .login(login)
                .password(password)
                .email(email)
                .dateOfRegistration(dataConverterOfRegistration)
                .build();
        return userRepository.save(user);
    }

    private boolean mailChecker(String email) {
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(email);
        boolean matchFound = m.matches();

        return matchFound;
    }

    private LocalDateTime dateConverter(LocalDateTime date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm");
        String format = date.format(dateTimeFormatter);
        return LocalDateTime.parse(format, dateTimeFormatter);
    }

}
