package com.project.books.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserFetchService userFetchService;
    private final UserMapper userMapper;
    private final UserCreateService userCreateService;

    @GetMapping("/users")
    List<UserDto> getAllUser(){
        return userFetchService.fetchAllUsers()
                .stream()
                .map(userMapper :: mapToUserDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/user/{id}")
    UserDto getUserById(@PathVariable Long id) {
        User user = userFetchService.fetchUserById(id);
        return userMapper.mapToUserDto(user);
    }

    @GetMapping("/user/{login}")
    UserDto getUserByLogin(@PathVariable String login){
        User user = userFetchService.fetchUserByLogin(login);
        return userMapper.mapToUserDto(user);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDefinition userDefinition = userMapper.mapToUserDefinition(userDto);
        User newUser = userCreateService.createUser(userDefinition);
        log.info("create new user: " + newUser);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userMapper.mapToUserDto(newUser));

    }

    @DeleteMapping("/user/{id}")
    public void deleteById(@PathVariable Long id){
        userFetchService.deleteById(id);
    }
}
