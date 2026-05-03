package org.example.marmura_order_manager.controller;

import org.example.marmura_order_manager.model.Client;
import org.example.marmura_order_manager.model.Comanda;
import org.example.marmura_order_manager.service.ClientService;
import org.example.marmura_order_manager.service.ComandaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value="api/clienti")
public class ClientController {
    private final ClientService clientService;
    private final ComandaService comandaService;



    public ClientController(ClientService clientService, ComandaService comandaService) {
        this.clientService = clientService;
        this.comandaService = comandaService;
    }

    //get requests

    @GetMapping
    public List<Client> getClients(){
        return clientService.totiClientii();
    }

    @GetMapping("/{id}/comenzi")
    public List<Comanda> getClientOrder(@PathVariable Long id){
        return comandaService.getComenziByClient(id);
    }

    @GetMapping("/cauta")
    @ResponseBody
    public List<Client> cautaClienti(@RequestParam String query){
        return clientService.cautaClient(query);
    }

    //delete request
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
    }

    @PostMapping
    public Client adaugaClient(@RequestBody Client client){
        return clientService.salveazaClient(client);
    }

}
