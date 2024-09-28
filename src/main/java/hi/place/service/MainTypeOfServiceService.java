package hi.place.service;

import hi.place.model.MainTypeOfService;

import java.util.List;

public interface MainTypeOfServiceService {
    List<MainTypeOfService> getAll();

    List<MainTypeOfService> getAllByUserId(Long userId);
}
