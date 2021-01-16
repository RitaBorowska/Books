package com.project.books.user;

import com.project.books.user.address.Address;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserService userService;

    @Test
    void userServiceTest_CreateUser() {

        //given
        Address address = new Address();
        address.setStreet("bursztynowa");
        address.setNum("11");
        address.setPostCode("44-444");
        address.setCity("gdansk");
        address.setRegion("pomorskie");

        when(userRepository.save(any(User.class))).thenReturn(new User());
        when(userMapper.mapToUserDto(any(User.class))).thenReturn(new UserDto());

        //when
        UserDto result = userService.createUser(UserDto.builder()
                .id(null)
                .name("rita")
                .surname("borowska")
                .login("123456")
                .password("334455")
                .email("ritaaa@wp.pl")
                .address(address)
                .build());

        //then
        assertThat(result).isInstanceOf(UserDto.class);
        verify(userRepository).save(any(User.class));

    }


}
