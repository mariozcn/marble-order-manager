package org.example.marmura_order_manager.service;


import org.example.marmura_order_manager.model.Client;
import org.example.marmura_order_manager.repository.ClientRepository;
import org.example.marmura_order_manager.repository.ComandaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientServiceTest {
    private ClientService clientService;
    private ClientRepository clientRepository;
    private ComandaRepository comandaRepository;


    @BeforeEach
    void setup(){
        clientRepository = mock(ClientRepository.class);
        comandaRepository = mock(ComandaRepository.class);


        clientService = new ClientService(clientRepository,comandaRepository);
    }

    @Test
    void cautaClient_returneazaClient(){
        Client client1 = new Client();
        client1.setName("Test");
        client1.setTelefon("077777777");
        when(clientRepository.findByTelefonContainingOrNameContaining("test","test")).thenReturn(List.of(client1));

        List<Client> result = clientService.cautaClient("test");

        assertEquals(1,result.size());
        assertEquals("Test",result.get(0).getName());
    }

}
