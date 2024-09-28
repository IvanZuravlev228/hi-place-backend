package hi.place.service;

import hi.place.model.user.Client;

public interface ClientService {
    Client getById(Long id);

    Client add(Client client);
}
