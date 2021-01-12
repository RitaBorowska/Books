package com.project.books.user;


import com.project.books.address.UserAddress;
import com.project.books.booking.Booking;
import com.project.books.books.Books;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "login", unique = true)
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "email", unique = true)
    private String email;
    private LocalDateTime dateOfRegistration;

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
