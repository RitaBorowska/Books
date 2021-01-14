package com.project.books.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.books.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @GetMapping("/users")
    List<UserDto> getAllUser(){
        return userService.getAllUsers()
                .stream()
                .map(userMapper :: mapToUserDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/userid/{id}")
    UserDto getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return userMapper.mapToUserDto(user);
    }

    @GetMapping("/userlogin/{login}")
    UserDto getUserByLogin(@PathVariable String login){
        User user = userService.getUserByLogin(login);
        return userMapper.mapToUserDto(user);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDefinition userDefinition = userMapper.mapToUserDefinition(userDto);
        User newUser = userService.createUser(userDefinition);
        log.info("create new user: " + newUser);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userMapper.mapToUserDto(newUser));

    }

    @PatchMapping("/updateuser/{id}")
    public ResponseEntity<UserDto> updateUser (@RequestBody UserDto userDto, @PathVariable Long id) {
    UserDefinition userDefinition = userMapper.mapToUserDefinition(userDto);
        User user = userService.updateUser(id);
        log.info("zmien dane uzytkownika o id: " + id );
        if(user == null) {
            throw new NotFoundException("uzytkownik o id: " + id + "nie istnieje");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public void deleteById(@PathVariable Long id){
        userService.deleteById(id);
    }


}
