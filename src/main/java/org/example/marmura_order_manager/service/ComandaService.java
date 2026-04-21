package org.example.marmura_order_manager.service;
import org.example.marmura_order_manager.dto.ComandaDTO;
import org.example.marmura_order_manager.dto.LinieComandaDTO;
import org.example.marmura_order_manager.model.Comanda;
import org.example.marmura_order_manager.model.LinieComanda;
import org.example.marmura_order_manager.model.Status;
import org.example.marmura_order_manager.repository.ClientRepository;
import org.example.marmura_order_manager.repository.ComandaRepository;
import org.example.marmura_order_manager.repository.LinieComandaRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDate;


@Service
public class ComandaService {
    private final ClientRepository clientRepository;
    private final ComandaRepository comandaRepository;
    private final LinieComandaRepository linieComandaRepository;


    public Comanda creareComanda(ComandaDTO comandaDTO){
        Comanda comanda = new Comanda();
        comanda.setObservatii(comandaDTO.getObservatii());
        comanda.setStatus(Status.Noua);
        comanda.setDataComenzii(LocalDate.now());
        comanda.setClient(clientRepository.findById(comandaDTO.getClient_id()).orElseThrow());
        comandaRepository.save(comanda);
        for(LinieComandaDTO linieComandaDTO : comandaDTO.getLinii()) {
            LinieComanda linieComanda = new LinieComanda();
            linieComanda.setComanda(comanda);
            linieComanda.setCant(linieComandaDTO.getCant());
            linieComanda.setLatime(linieComandaDTO.getLatime());
            linieComanda.setGrosime(linieComandaDTO.getGrosime());
            linieComanda.setLungime(linieComandaDTO.getLungime());
            linieComanda.setMaterial(linieComandaDTO.getMaterial());
            linieComanda.setPret(linieComandaDTO.getPret());

            linieComandaRepository.save(linieComanda);
        }
           return comanda;
    }










    public ComandaService(ClientRepository clientRepository, ComandaRepository comandaRepository, LinieComandaRepository linieComandaRepository) {
        this.clientRepository = clientRepository;
        this.comandaRepository = comandaRepository;
        this.linieComandaRepository = linieComandaRepository;
    }

}
