package hi.place.service.impl.user;

import hi.place.model.user.User;
import hi.place.repository.user.UserRepository;
import hi.place.service.UserService;
import hi.place.service.impl.EmailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getByEmail(String email) {
        return userRepository.getUserByEmail(email).orElseThrow(() ->
                new RuntimeException("Can't find user by email: " + email));
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't find user by id: " + id));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getAllByMainTypeOfServiceId(Long mainTypeOfServiceId,
                                                  String city,
                                                  String sortByType,
                                                  Boolean sortByAtSalon,
                                                  Boolean sortByHomeVisit,
                                                  Boolean sortByOnlineCounseling,
                                                  Pageable pageable) {
        return userRepository.findUsersByMainTypeOfServiceId(mainTypeOfServiceId,
                                                            city,
                                                            sortByType,
                                                            sortByAtSalon,
                                                            sortByHomeVisit,
                                                            sortByOnlineCounseling,
                                                            pageable);
    }

    @Override
    public Page<User> getAllByTypeOfServiceId(Long typeOfServiceId,
                                              String city,
                                              String sortByType,
                                              Boolean sortByAtSalon,
                                              Boolean sortByHomeVisit,
                                              Boolean sortByOnlineCounseling,
                                              Pageable pageable) {
        return userRepository.findUsersByTypeOfServiceIdAndCity(typeOfServiceId,
                                                                city,
                                                                sortByType,
                                                                sortByAtSalon,
                                                                sortByHomeVisit,
                                                                sortByOnlineCounseling,
                                                                pageable);
    }

    @Override
    public Page<User> getAllByServiceItemId(Long serviceItemId, String city,
                                            String sortByType,
                                            Boolean sortByAtSalon,
                                            Boolean sortByHomeVisit,
                                            Boolean sortByOnlineCounseling,
                                            Pageable pageable) {
        return userRepository.findUsersByServiceItemId(serviceItemId, city,
                sortByType,
                sortByAtSalon,
                sortByHomeVisit,
                sortByOnlineCounseling,
                pageable);
    }

    @Override
    public void addLogoUrlToUser(String logoUrl, Long userId) {
        userRepository.addLogoUrlToUser(logoUrl, userId);
    }

    @Override
    public boolean setEmailConfirmed(String token) {
        User user = userRepository.findByVerificationToken(token);
        if (user != null) {
            user.setEmailVerified(true);
            user.setVerificationToken(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
