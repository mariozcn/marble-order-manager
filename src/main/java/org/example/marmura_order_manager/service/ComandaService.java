package org.example.marmura_order_manager.service;

import org.example.marmura_order_manager.dto.ComandaDTO;
import org.example.marmura_order_manager.dto.LiniiComandaDTO;
import org.example.marmura_order_manager.model.Client;
import org.example.marmura_order_manager.model.Comanda;
import org.example.marmura_order_manager.model.LinieComanda;
import org.example.marmura_order_manager.model.Status;
import org.example.marmura_order_manager.repository.ComandaRepository;
import org.example.marmura_order_manager.repository.LiniiComandaRepository;
import org.springframework.stereotype.Service;
import org.example.marmura_order_manager.repository.ClientRepository;
import java.time.LocalDate;

@Service
public class ComandaService {
    private final ComandaRepository comandaRepository;
    private final LiniiComandaRepository liniiComandaRepository;
    private final ClientRepository clientRepository;


    public Comanda salveazaComanda(ComandaDTO dto){
        Client client = clientRepository.findById(dto.getClientId()).orElseThrow();
        Comanda comanda = new Comanda();

        comanda.setClient(client);
        comanda.setObservatii(dto.getObservatii());
        comanda.setDataComenzii(LocalDate.now());
        comanda.setStatus(Status.Noua);

        for(LiniiComandaDTO liniiComandaDTO : dto.getLinii()){
            LinieComanda linieComanda = new LinieComanda();
            linieComanda.setComanda(comanda);
            linieComanda.setCant(liniiComandaDTO.getCant());
            linieComanda.setGrosime(liniiComandaDTO.getGrosime());
            linieComanda.setLatime(liniiComandaDTO.getLatime());
            linieComanda.setLungime(liniiComandaDTO.getLungime());
            linieComanda.setMaterial(liniiComandaDTO.getMaterial());
            linieComanda.setPret(liniiComandaDTO.getPret());

            liniiComandaRepository.save(linieComanda);
        }


        return comandaRepository.save(comanda);
    }





    public ComandaService(ComandaRepository comandaRepository, LiniiComandaRepository liniiComandaRepository, ClientRepository clientRepository) {
        this.comandaRepository = comandaRepository;
        this.liniiComandaRepository = liniiComandaRepository;
        this.clientRepository = clientRepository;
    }
}
