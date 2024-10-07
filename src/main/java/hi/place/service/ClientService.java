package hi.place.service;

import hi.place.model.user.Client;

import java.util.Optional;

public interface ClientService {
    Client getById(Long id);

    Optional<Client> getByEmail(String email);

    Client add(Client client);
}
