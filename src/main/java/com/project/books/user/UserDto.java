package com.project.books.user;

import com.project.books.address.Address;
import com.project.books.booking.Booking;
import com.project.books.books.Books;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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
    private String login;
//    @NotNull
    private String password;
//    @Email(message = "nie pooprawny format email")
    private String email;

    private LocalDateTime dateOfRegistration;
    private Address address;
    private List<Books> books;
    private List<Booking> bookings;
}
