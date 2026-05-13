package org.example.marmura_order_manager.service;


import org.example.marmura_order_manager.model.Client;
import org.example.marmura_order_manager.repository.ClientRepository;
import org.example.marmura_order_manager.repository.ComandaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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

    @Test
    void cautaClientDupaId(){
        Client client1 = new Client();
        client1.setName("test");
        client1.setTelefon("07777777");
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client1));

        Client rezultat = clientService.getClientById(1L);

        assertEquals("test",rezultat.getName());
        assertEquals("07777777",rezultat.getTelefon());
    }

    @Test
    void updateClientTest(){
        Client client = new Client();
        client.setName("Nume vechi");

        when(clientRepository.save(client)).thenReturn(client);

        client.setName("Nume nou");
        Client rezultat = clientService.salveazaClient(client);

        assertEquals("Nume nou",rezultat.getName());
    }

    @Test
    void stergeClientTest(){
        Client client = new Client();
        client.setName("Test");

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(comandaRepository.findComandaByClient(client)).thenReturn(List.of());

        clientService.deleteClient(1L);
        verify(clientRepository).deleteById(1L);
    }


}
