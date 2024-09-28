package hi.place.service;

import hi.place.model.ServiceItem;

import java.util.List;
import java.util.Optional;

public interface ServiceItemService {
    List<ServiceItem> getByTypeOfServiceId(Long typeOfServiceId);

    ServiceItem getById(Long id);

    ServiceItem getInitializeById(Long id);
}
