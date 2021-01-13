package com.project.books.address;

import com.project.books.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private Long id;
//    @NotNull(message = "stres")
    private String street;
//    @NotNull(message = "num")
    private String num;
//    @NotNull(message = "pc")
    private String postCode;
//    @NotNull(message = "city")
    private String city;
//    @NotNull(message = "reg")
    private String region;

    private List<User> users;
}
