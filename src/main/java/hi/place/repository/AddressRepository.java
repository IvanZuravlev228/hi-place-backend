package hi.place.repository;

import hi.place.model.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> getAllByUser_Id(Long userId);

    @Query("SELECT DISTINCT city FROM Address")
    List<String> getAllCities();
}
