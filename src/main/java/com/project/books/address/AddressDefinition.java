package com.project.books.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
