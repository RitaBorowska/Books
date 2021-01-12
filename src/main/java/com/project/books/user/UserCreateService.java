package com.project.books.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

@Component
@RequiredArgsConstructor
@Validated
public class UserCreateService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;



    public User createUser(@NotNull @RequestParam UserDefinition userDefinition) {
        String name = userDefinition.getName();
        String surname = userDefinition.getSurname();
        String login = userDefinition.getLogin();
        String password = userDefinition.getPassword();
        String email = userDefinition.getEmail();

        User user = User.builder()
                    .name(name)
                    .surname(surname)
                    .login(login)
                    .password(password)
                    .email(email)
                    .build();


            return userRepository.save(user);
        }

    }
