package com.project.books.user;

import lombok.Builder;
import org.springframework.stereotype.Component;

@Builder
@Component
public class UserMapper {

    UserDto mapToUserDto(User newUser) {
        return UserDto.builder()
                .id(newUser.getId())
                .name(newUser.getName())
                .surname(newUser.getSurname())
                .login(newUser.getLogin())
                .password(newUser.getPassword())
                .email(newUser.getEmail())
                .dateOfRegistration(newUser.getDateOfRegistration())
                .address(newUser.getAddress())
                .books(newUser.getBooks())
                .bookings(newUser.getBookings())
                .build();
    }

    UserDefinition mapToUserDefinition(UserDto userDto){
        return  UserDefinition.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .build();
    }
}
