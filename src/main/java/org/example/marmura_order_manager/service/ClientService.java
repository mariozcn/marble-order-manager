package org.example.marmura_order_manager.service;


import org.example.marmura_order_manager.model.Client;
import org.example.marmura_order_manager.model.Comanda;
import org.example.marmura_order_manager.repository.ClientRepository;
import org.example.marmura_order_manager.repository.ComandaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClientService {
    private static final Logger log = LoggerFactory.getLogger(ClientService.class);

    private final ClientRepository clientRepository;
    private final ComandaRepository comandaRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, ComandaRepository comandaRepository) {
        this.clientRepository = clientRepository;
        this.comandaRepository = comandaRepository;
    }

    public Client salveazaClient(Client client){
        return clientRepository.save(client);
    }

    public List<Client> cautaClient(String query){
        log.info("Searching for client with query: {}",query);
        return clientRepository.findByTelefonContainingOrNameContaining(query,query);
    }

    public List<Client> totiClientii(){
        return clientRepository.findAll();
    }

    public void deleteClient(Long id){
        log.info("Deleting client with id: {}",id);
        Client client = clientRepository.findById(id).orElseThrow();
        List<Comanda> comenzi = comandaRepository.findComandaByClient(client);
        comandaRepository.deleteAll(comenzi);
        clientRepository.deleteById(id);
    }

    public Client getClientById(Long id){
        log.info("Searching for client with id: {}",id);
        return clientRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Clientul cu id " + id + " " +
                "nu exista"));
    }

    public Client updateClient(Long id,Client client){
        log.info("Updating client with id: {}",id);
        Client existent = clientRepository.findById(id).orElseThrow();
        existent.setName(client.getName());
        existent.setTelefon(client.getTelefon());
        return clientRepository.save(existent);
    }
}
