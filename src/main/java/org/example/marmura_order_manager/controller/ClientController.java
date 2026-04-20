package org.example.marmura_order_manager.controller;


import org.example.marmura_order_manager.model.Client;
import org.example.marmura_order_manager.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/clienti")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getClients(){
        return clientService.totiClientii();
    }

    @PostMapping
    public Client adaugaClient(@RequestBody Client client){
        return clientService.salveazaClient(client);
    }

}
