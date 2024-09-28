package hi.place.service.impl.user;

import hi.place.model.user.Client;
import hi.place.repository.user.ClientRepository;
import hi.place.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public Client getById(Long id) {
        return clientRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't find client by id: " + id));
    }

    @Override
    public Client add(Client client) {
        return clientRepository.save(client);
    }
}
