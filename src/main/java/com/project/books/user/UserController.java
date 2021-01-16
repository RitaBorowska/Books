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
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);

    }

    @GetMapping("/users")
    List<UserDto> getAllUser(){
        return userService.getAllUsers()
                .stream()
                .map(userMapper :: mapToUserDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/users/{userId}/roles/{role}")
    public UserDto addRoleToUser(@PathVariable Long id, @PathVariable String role) {
        final User user = userService.getUserById(id);
        return userService.changeRole(user, role);
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

    @PutMapping("/updateuser")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
   }

    @DeleteMapping("/user/{id}")
    public void deleteById(@PathVariable Long id){
        userService.deleteById(id);
    }

}
