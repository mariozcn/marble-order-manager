package org.example.marmura_order_manager.service;


import org.example.marmura_order_manager.model.Client;
import org.example.marmura_order_manager.model.Comanda;
import org.example.marmura_order_manager.repository.ClientRepository;
import org.example.marmura_order_manager.repository.ComandaRepository;
import org.example.marmura_order_manager.repository.LinieComandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
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
        return clientRepository.findByTelefonContainingOrNameContaining(query,query);
    }

    public List<Client> totiClientii(){
        return clientRepository.findAll();
    }

    public void deleteClient(Long id){
        Client client = clientRepository.findById(id).orElseThrow();
        List<Comanda> comenzi = comandaRepository.findComandaByClient(client);
        comandaRepository.deleteAll(comenzi);
        clientRepository.deleteById(id);
    }
}
