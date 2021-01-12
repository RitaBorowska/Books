package com.project.books.address;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
@RestController
@Validated
public class AddressController {

    public final AddressFetchService addressFetchService;
    private final AddressMapper addressMapper;
    private final AddressCreateService addressCreateService;

    @GetMapping("/address/{id}")
    AddressDto getAddressById(@PathVariable Long id) {
        Address address = addressFetchService.fetchAddressById(id);
        return addressMapper.mapToAddressDto(address);
    }

    @PostMapping("/address")
    ResponseEntity<AddressDto> createAddress(@RequestBody @Valid AddressDto addressDto) {
        AddressDefinition addressDefinition = addressMapper.mapToAddressDefinition(addressDto);
        Address newAddress = addressCreateService.createAddress(addressDefinition);
        log.info("create address: " + newAddress);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addressMapper.mapToAddressDto(newAddress));
    }

    @GetMapping("/sddresses")
    List<AddressDto> getAllAddresses() {
        return addressFetchService.fetchAllAddresses()
                .stream()
                .map(addressMapper::mapToAddressDto)
                .collect(Collectors.toList());

    }

    @DeleteMapping("/address/{id}")
    public  void  deleteById ( @PathVariable  Long  id ) {
        addressFetchService . deleteById (id);
    }

}
