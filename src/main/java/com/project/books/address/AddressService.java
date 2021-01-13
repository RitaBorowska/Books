package com.project.books.address;

import com.project.books.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public Address getAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Brak adresu o id" + id));
    }

    List<Address> getAllAddresses(){
        return addressRepository.findAll();
    }

    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }

    public Address createAddress(AddressDefinition addressDefinition) {
        String street = addressDefinition.getStreet();
        String num = addressDefinition.getNum();
        String postcode = addressDefinition.getPostCode();
        String city = addressDefinition.getCity();
        String region = addressDefinition.getRegion();

//        if (street.isEmpty() || street.isBlank()) {
//            throw new BadRequestException("nazwa ulicy nie mogze być pusta");
//        }
//        if (num.isEmpty() || num.isBlank()){
//            throw new BadRequestException("prosze podac numer domu");
//        }
//        if (postcode.isEmpty() || postcode.isBlank()) {
//            throw new BadRequestException("kod pocztowy nie może być pusty");
//        }
//        if ( city.isEmpty() || city.isBlank()) {
//            throw new BadRequestException("prosze podaj miasto");
//        }
//        if (region.isEmpty() || region.isBlank()) {
//            throw new BadRequestException("region nie może być pusty");
//        }

        Address address = Address.builder()
                .street(street)
                .num(num)
                .postCode(postcode)
                .city(city)
                .region(region)
                .build();

        return addressRepository.save(address);


    }
}
