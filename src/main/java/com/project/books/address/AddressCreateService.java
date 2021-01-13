package com.project.books.address;

import com.project.books.exceptions.BadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AddressCreateService {

    private final AddressRepository addressRepository;

    Address createAddress(AddressDefinition addressDefinition) {
        String street = addressDefinition.getStreet();
        String num = addressDefinition.getNum();
        String postcode = addressDefinition.getPostCode();
        String city = addressDefinition.getCity();
        String region = addressDefinition.getRegion();

        if (street.isEmpty() || street.isBlank() || num.isEmpty() || num.isBlank()) {
            throw new BadRequest("ulica i numer domu nie mogą być puste");
        }
        if (postcode.isEmpty() || postcode.isBlank() || city.isEmpty() || city.isBlank()) {
            throw new BadRequest("kod pocztowy i nazwa miasta nie może bbyć pusta");
        }
        if (region.isEmpty() || region.isBlank()) {
            throw  new BadRequest("region nie może być pusty");
        }

        Address address = new Address();
        address.setStreet(addressDefinition.getStreet());
        address.setNum(addressDefinition.getNum());
        address.setPostCode(addressDefinition.getPostCode());
        address.setCity(addressDefinition.getCity());
        address.setRegion(addressDefinition.getRegion());

        return addressRepository.save(address);


    }
}
