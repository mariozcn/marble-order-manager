package org.example.marmura_order_manager.service;


import org.example.marmura_order_manager.model.Client;
import org.example.marmura_order_manager.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client salveazaClient(Client client){
        return clientRepository.save(client);
    }

    public List<Client> totiClientii(){
        return clientRepository.findAll();
    }



}
