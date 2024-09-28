package hi.place.service;

import hi.place.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User getByEmail(String email);

    User getById(Long id);

    List<User> getAll();

    Page<User> getAllByMainTypeOfServiceId(Long mainTypeOfServiceId,
                                           String city,
                                           String sortByType,
                                           Boolean sortByAtSalon,
                                           Boolean sortByHomeVisit,
                                           Boolean sortByOnlineCounseling, Pageable pageable);

    Page<User> getAllByTypeOfServiceId(Long typeOfServiceId,
                                       String city,
                                       String sortByType,
                                       Boolean sortByAtSalon,
                                       Boolean sortByHomeVisit,
                                       Boolean sortByOnlineCounseling,
                                       Pageable pageable);

    Page<User> getAllByServiceItemId(Long serviceItemId, String city,
                                     String sortByType,
                                     Boolean sortByAtSalon,
                                     Boolean sortByHomeVisit,
                                     Boolean sortByOnlineCounseling,
                                     Pageable pageable);

    void addLogoUrlToUser(String logoUrl, Long userId);

    boolean setEmailConfirmed(String token);
}
