package hi.place.service.impl;

import hi.place.model.MainTypeOfService;
import hi.place.repository.MainTypeOfServiceRepository;
import hi.place.service.MainTypeOfServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainTypeOfServiceServiceImpl implements MainTypeOfServiceService {
    private final MainTypeOfServiceRepository mainTypeOfServiceRepository;

    @Override
    public List<MainTypeOfService> getAll() {
        return mainTypeOfServiceRepository.findAll();
    }

    @Override
    public List<MainTypeOfService> getAllByUserId(Long userId) {
        return mainTypeOfServiceRepository.getAllByUserId(userId);
    }
}
