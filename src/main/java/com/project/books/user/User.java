package com.project.books.user;


import com.project.books.user.address.Address;
import com.project.books.booking.Booking;
import com.project.books.books.Books;
import com.project.books.user.role.UserRole;
import lombok.*;

import javax.persistence.*;
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

//    @Column(name = "name")
    private String name;
//    @Column(name = "surname")
    private String surname;
//    @Column(name = "login", unique = true)
    private String login;
//    @Column(name = "password")
    private String password;
//    @Column(name = "email", unique = true)
    private String email;
    private LocalDateTime dateOfRegistration;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Address address;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    private UserRole userRole;

    @OneToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Books> books;

    @OneToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Booking> bookings;

}
