package com.project.books.address;

import com.project.books.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String num;
    private String postCode;
    private String city;
    private String region;

    public Optional<String> getRegion(){
        return Optional.of(region);
    }

    @OneToMany(mappedBy = "Address")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<User> users;

}
