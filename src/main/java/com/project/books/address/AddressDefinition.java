package com.project.books.address;

import com.project.books.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDefinition {

    private String street;
    private String num;
    private String postCode;
    private String city;
    private String region;
    private List<User> users;
}
