package com.project.books.user;


import com.project.books.address.UserAddress;
import com.project.books.booking.Booking;
import com.project.books.books.Books;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank
    @NotEmpty
    private String name;

    @NotBlank
    @NotEmpty
    private String surname;

    @NotBlank
    @NotEmpty
    private String login;

    @NotBlank
    @NotEmpty
    private String password;

    @Email
    @NotBlank
    @NotEmpty
    private String email;

    private LocalDate dateOfRegistration;

    @ManyToOne
    private UserAddress userAddress;

    @OneToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Books> books;

    @OneToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Booking> bookings;
}
