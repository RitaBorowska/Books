package com.project.books.address;

import lombok.Builder;
import org.springframework.stereotype.Component;

@Builder
@Component
public class AddressMapper {


    AddressDto mapToAddressDto(Address newAddress) {
        return AddressDto.builder()
                .id(newAddress.getId())
                .street(newAddress.getStreet())
                .num(newAddress.getNum())
                .postCode(newAddress.getPostCode())
                .city(newAddress.getCity())
                .region(newAddress.getRegion())
                .build();
    }

    AddressDefinition mapToAddressDefinition(AddressDto newAddressDto) {
        return AddressDefinition.builder()
                .street(newAddressDto.getStreet())
                .num(newAddressDto.getNum())
                .postCode(newAddressDto.getPostCode())
                .city(newAddressDto.getCity())
                .region(newAddressDto.getRegion())
                .build();
    }
}
