package hi.place.repository;

import hi.place.model.ServiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ServiceItemRepository extends JpaRepository<ServiceItem, Long> {
    List<ServiceItem> getByTypeOfService_Id(Long typeOfServiceId);

    @Query("select si from ServiceItem si " +
            "join fetch si.typeOfService ts " +
            "join fetch ts.mainType " +
            "where si.id = :id")
    Optional<ServiceItem> getInitializeServiceItemById(@Param("id") Long id);
}
