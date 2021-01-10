package com.project.books.address;

import com.project.books.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String num;
    private String postCode;
    private String city;
    private String region;

    @OneToMany(mappedBy = "userAddress")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<User> users;
}
