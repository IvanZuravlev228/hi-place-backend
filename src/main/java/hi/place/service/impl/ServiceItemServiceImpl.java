package hi.place.service.impl;

import hi.place.model.ServiceItem;
import hi.place.repository.ServiceItemRepository;
import hi.place.service.ServiceItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceItemServiceImpl implements ServiceItemService {
    private final ServiceItemRepository serviceItemRepository;

    @Override
    public List<ServiceItem> getByTypeOfServiceId(Long typeOfServiceId) {
        return serviceItemRepository.getByTypeOfService_Id(typeOfServiceId);
    }

    @Override
    public ServiceItem getById(Long id) {
        return serviceItemRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't find service item by id: " + id));
    }

    @Override
    public ServiceItem getInitializeById(Long id) {
        return serviceItemRepository.getInitializeServiceItemById(id).orElseThrow(() ->
                new RuntimeException("Can't find service item by id: " + id));
    }
}
