package hi.place.service.impl;

import hi.place.model.address.Address;
import hi.place.repository.AddressRepository;
import hi.place.service.AddressService;
import hi.place.util.CityNormalizer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public Address save(Address address) {
        address.setCity(CityNormalizer.normalizeCityName(address.getCity()));
        return addressRepository.save(address);
    }

    @Override
    public Address getById(Long id) {
        return addressRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't find address with id: " + id)
        );
    }

    @Override
    public List<Address> getByUserId(Long userId) {
        return addressRepository.getAllByUser_Id(userId);
    }

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public List<Address> getAllNearAddress(Double lat, Double lon) {
        List<Address> addresses = addressRepository.findAll();
        return findAddressesWithinRadius(addresses, lat, lon, 1.0);
    }

    @Override
    public List<String> getAllCities() {
        return addressRepository.getAllCities();
    }

    private List<Address> findAddressesWithinRadius(List<Address> addresses, double lat, double lon, double radius) {
        List<Address> result = new ArrayList<>();
        for (Address address : addresses) {
            double distance = Haversine.calculateDistance(lat, lon, address.getLat(), address.getLon());
            if (distance <= radius) {
                result.add(address);
            }
        }
        return result;
    }
}
