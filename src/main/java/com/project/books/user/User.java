package com.project.books.user;


import com.project.books.address.UserAddress;
import com.project.books.booking.Booking;
import com.project.books.books.Books;
import lombok.*;

import javax.persistence.*;
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

    private String name;
    private String surname;
    private String login;
    private String password;
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
