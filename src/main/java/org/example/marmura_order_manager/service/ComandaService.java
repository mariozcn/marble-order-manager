package org.example.marmura_order_manager.service;
import org.example.marmura_order_manager.dto.ComandaDTO;
import org.example.marmura_order_manager.dto.LinieComandaDTO;
import org.example.marmura_order_manager.model.Comanda;
import org.example.marmura_order_manager.model.LinieComanda;
import org.example.marmura_order_manager.model.Material;
import org.example.marmura_order_manager.model.Status;
import org.example.marmura_order_manager.repository.ClientRepository;
import org.example.marmura_order_manager.repository.ComandaRepository;
import org.example.marmura_order_manager.repository.LinieComandaRepository;
import org.example.marmura_order_manager.repository.MaterialRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;


@Service
public class ComandaService {
    private final ClientRepository clientRepository;
    private final ComandaRepository comandaRepository;
    private final LinieComandaRepository linieComandaRepository;
    private final MaterialRepository materialRepository;

    public double calcularePret(LinieComandaDTO linieComandaDTO){
        Material material = materialRepository.findByNameAndGrosime(linieComandaDTO.getMaterial(),
                linieComandaDTO.getGrosime()).orElseThrow();

        double suprafata = (linieComandaDTO.getLungime() * linieComandaDTO.getLatime()) /10000.0;
        return suprafata * material.getPret();
    }


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
            double pret = calcularePret(linieComandaDTO);
            linieComanda.setPret(pret);

            linieComandaRepository.save(linieComanda);
        }
           return comanda;
    }

    public List<Comanda> getComenzi(){
        return comandaRepository.findAll();
    }


    public ComandaService(ClientRepository clientRepository, ComandaRepository comandaRepository, LinieComandaRepository linieComandaRepository, MaterialRepository materialRepository) {
        this.clientRepository = clientRepository;
        this.comandaRepository = comandaRepository;
        this.linieComandaRepository = linieComandaRepository;
        this.materialRepository = materialRepository;
    }
}
