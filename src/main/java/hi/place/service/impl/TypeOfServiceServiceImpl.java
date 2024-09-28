package hi.place.service.impl;

import hi.place.dto.TypeOfServiceCountDto;
import hi.place.model.TypeOfService;
import hi.place.repository.TypeOfServiceRepository;
import hi.place.service.TypeOfServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeOfServiceServiceImpl implements TypeOfServiceService {
    private final TypeOfServiceRepository typeOfServiceRepository;

    @Override
    public List<TypeOfService> getAll() {
        return typeOfServiceRepository.findAll();
    }

    @Override
    public List<TypeOfService> getAllWithDiscount(Long currentDate) {
        return typeOfServiceRepository.getAllWithDiscount(currentDate);
    }

    @Override
    public List<TypeOfService> getByMainTypeId(Long mainTypeId) {
        return typeOfServiceRepository.findByMainType_Id(mainTypeId);
    }

    @Override
    public List<TypeOfServiceCountDto> getTypeOfServiceCountByUserId(Long userId) {
        return typeOfServiceRepository.getTypeOfServiceAnfCountByUserId(userId);
    }

    @Override
    public TypeOfService getById(Long id) {
        return typeOfServiceRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't find type of service by id: " + id));
    }
}
