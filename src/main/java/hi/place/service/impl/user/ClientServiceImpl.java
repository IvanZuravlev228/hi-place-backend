package hi.place.service.impl.user;

import hi.place.exception.EmailAlreadyExistsException;
import hi.place.model.user.Client;
import hi.place.repository.user.ClientRepository;
import hi.place.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public Client getById(Long id) {
        return clientRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find client by id: " + id));
    }

    @Override
    public Optional<Client> getByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public Client add(Client client) {
        return clientRepository.save(client);
    }
}
