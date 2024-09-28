package hi.place.service;

import hi.place.dto.TypeOfServiceCountDto;
import hi.place.model.TypeOfService;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeOfServiceService {
    List<TypeOfService> getAll();

    List<TypeOfService> getAllWithDiscount(Long currentDate);

    List<TypeOfService> getByMainTypeId(Long mainTypeId);

    List<TypeOfServiceCountDto> getTypeOfServiceCountByUserId(Long userId);

    TypeOfService getById(Long id);
}
