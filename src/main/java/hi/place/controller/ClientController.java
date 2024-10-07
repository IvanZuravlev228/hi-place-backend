package hi.place.controller;

import hi.place.model.user.Client;
import hi.place.service.ClientService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> add(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.add(client), HttpStatus.CREATED);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable Long clientId) {
        return new ResponseEntity<>(clientService.getById(clientId), HttpStatus.OK);
    }
}
