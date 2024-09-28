package hi.place.service;

import hi.place.model.address.Address;

import java.util.List;

public interface AddressService {
    Address save(Address address);

    Address getById(Long id);

    List<Address> getByUserId(Long userId);

    void deleteById(Long id);

    List<Address> getAllNearAddress(Double lat, Double lon);

    List<String> getAllCities();
}
