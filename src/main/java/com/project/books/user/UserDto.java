package com.project.books.user;

import com.project.books.user.address.Address;
import com.project.books.booking.Booking;
import com.project.books.books.Books;
import com.project.books.user.role.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    public Long id;
//    @NotNull
    private String name;
//    @NotNull
    private String surname;
//    @NotNull
//    @UniqueElements
    private String login;
//    @NotNull
    private String password;
//    @Email(message = "nie poprawny format email")
    private String email;

//    @NotBlank
//    private UserRole userRole;

    private LocalDateTime dateOfRegistration;
    private Address address;
    private List<Books> books;
    private List<Booking> bookings;

}
