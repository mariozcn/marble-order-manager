package org.example.marmura_order_manager.service;
import jakarta.transaction.Transactional;
import org.example.marmura_order_manager.dto.ComandaDTO;
import org.example.marmura_order_manager.dto.LinieComandaDTO;
import org.example.marmura_order_manager.model.*;
import org.example.marmura_order_manager.repository.ClientRepository;
import org.example.marmura_order_manager.repository.ComandaRepository;
import org.example.marmura_order_manager.repository.LinieComandaRepository;
import org.example.marmura_order_manager.repository.MaterialRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.time.LocalDate;
import java.util.ArrayList;
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

    public double calculPretCant(LinieComandaDTO linieComandaDTO){
        double suprafata = 0;
        if(linieComandaDTO.isCantStanga())  suprafata += linieComandaDTO.getLatime()   / 100.0;
        if(linieComandaDTO.isCantDreapta()) suprafata += linieComandaDTO.getLatime()   / 100.0;
        if(linieComandaDTO.isCantSus())     suprafata += linieComandaDTO.getLungime()  / 100.0;
        if(linieComandaDTO.isCantJos())     suprafata += linieComandaDTO.getLungime()  / 100.0;
        return suprafata * 40;
    }

    public Comanda updateComanda(Long id, Status status){
        Comanda comanda = comandaRepository.findById(id).orElseThrow();
        comanda.setStatus(status);
        return comandaRepository.save(comanda);
    }

    @Transactional
    public Comanda creareComanda(ComandaDTO comandaDTO){
        Comanda comanda = new Comanda();
        comanda.setObservatii(comandaDTO.getObservatii());
        comanda.setStatus(Status.Noua);
        comanda.setDataComenzii(LocalDate.now());
        comanda.setClient(clientRepository.findById(comandaDTO.getClient_id()).orElseThrow());
        comandaRepository.save(comanda);

        List<LinieComanda> linii = new ArrayList<>();

        for(LinieComandaDTO linieComandaDTO : comandaDTO.getLinii()) {
            LinieComanda linieComanda = new LinieComanda();
            linieComanda.setComanda(comanda);
            linieComanda.setCant(linieComandaDTO.getCant());
            linieComanda.setCantDreapta(linieComandaDTO.isCantDreapta());
            linieComanda.setCantStanga(linieComandaDTO.isCantStanga());
            linieComanda.setCantSus(linieComandaDTO.isCantSus());
            linieComanda.setCantJos(linieComandaDTO.isCantJos());
            linieComanda.setColtJosDreapta(linieComandaDTO.isColtJosDreapta());
            linieComanda.setColtJosStanga(linieComandaDTO.isColtJosStanga());
            linieComanda.setColtSusStanga(linieComandaDTO.isColtSusStanga());
            linieComanda.setColtSusDreapta(linieComandaDTO.isColtSusDreapta());
            linieComanda.setLatime(linieComandaDTO.getLatime());
            linieComanda.setGrosime(linieComandaDTO.getGrosime());
            linieComanda.setLungime(linieComandaDTO.getLungime());
            linieComanda.setMaterial(linieComandaDTO.getMaterial());
            double pret = calcularePret(linieComandaDTO) + calculPretCant(linieComandaDTO);
            linieComanda.setPret(pret);
            linieComandaRepository.save(linieComanda);
            linii.add(linieComanda);
        }

        comanda.setLinii(linii);
        return comanda;
    }

    public List<Comanda> getComenziByClient(Long id){
        Client client = clientRepository.findById(id).orElseThrow();
        return comandaRepository.findComandaByClient(client);
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
