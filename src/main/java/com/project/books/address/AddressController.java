package com.project.books.address;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
@RestController
//@Validated
public class AddressController {

    public final AddressService addressService;
    private final AddressMapper addressMapper;

    @GetMapping("/addressid/{id}")
    AddressDto getAddressById(@PathVariable Long id) {
        Address address = addressService.getAddressById(id);
        return addressMapper.mapToAddressDto(address);
    }

    @PostMapping("/address")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto addressDto) {
        AddressDefinition addressDefinition = addressMapper.mapToAddressDefinition(addressDto);
        Address newAddress = addressService.createAddress(addressDefinition);
        log.info("create address: " + newAddress);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addressMapper.mapToAddressDto(newAddress));
    }

    @GetMapping("/addresses")
    List<AddressDto> getAllAddresses() {
        return addressService.getAllAddresses()
                .stream()
                .map(addressMapper::mapToAddressDto)
                .collect(Collectors.toList());

    }

    @DeleteMapping("/address/{id}")
    public  void  deleteById ( @PathVariable  Long  id ) {
        addressService. deleteById (id);
    }

}
