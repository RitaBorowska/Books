package com.project.books.address;

import com.project.books.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AddressFetchService {

    private final AddressRepository addressRepository;

    public Address fetchAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Brak adresu p id" + id));
    }

    List<Address> fetchAllAddresses(){
        return addressRepository.findAll();
    }

    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}
