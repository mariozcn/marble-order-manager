package org.example.marmura_order_manager.service;
import jakarta.transaction.Transactional;
import org.example.marmura_order_manager.dto.ComandaDTO;
import org.example.marmura_order_manager.dto.LinieComandaDTO;
import org.example.marmura_order_manager.model.*;
import org.example.marmura_order_manager.repository.ClientRepository;
import org.example.marmura_order_manager.repository.ComandaRepository;
import org.example.marmura_order_manager.repository.LinieComandaRepository;
import org.example.marmura_order_manager.repository.MaterialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class ComandaService {
    private final static Logger log = LoggerFactory.getLogger(ComandaService.class);

    private final ClientRepository clientRepository;
    private final ComandaRepository comandaRepository;
    private final LinieComandaRepository linieComandaRepository;
    private final MaterialRepository materialRepository;

    public double calcularePret(LinieComandaDTO linieComandaDTO){
        Material material = materialRepository.findByNameAndGrosime(linieComandaDTO.getMaterial(),
                linieComandaDTO.getGrosime()).orElseThrow();

        log.info("Calculating price for material: {}",material.getName());

        double suprafata = (linieComandaDTO.getLungime() * linieComandaDTO.getLatime()) /10000.0;
        return suprafata * material.getPret();
    }

    public double calculPretCant(LinieComandaDTO linieComandaDTO){
        log.info("Calculating edge finishing price: {}",linieComandaDTO.getCant());
        double suprafata = 0;
        if(linieComandaDTO.isCantStanga())  suprafata += linieComandaDTO.getLatime()   / 100.0;
        if(linieComandaDTO.isCantDreapta()) suprafata += linieComandaDTO.getLatime()   / 100.0;
        if(linieComandaDTO.isCantSus())     suprafata += linieComandaDTO.getLungime()  / 100.0;
        if(linieComandaDTO.isCantJos())     suprafata += linieComandaDTO.getLungime()  / 100.0;
        return suprafata * 40;
    }

    public Comanda getComandaById(Long id){
        log.info("Searching for order with id: {}",id);
        return comandaRepository.findById(id).orElseThrow();
    }

    public Comanda updateComanda(Long id, Status status){
        log.info("Changing order {} status to {}",id,status);
        Comanda comanda = comandaRepository.findById(id).orElseThrow();
        comanda.setStatus(status);
        return comandaRepository.save(comanda);
    }

    @Transactional
    public Comanda creareComanda(ComandaDTO comandaDTO){
        log.info("Creating order for client with id: {}",comandaDTO.getClient_id());
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
        log.info("Order created with id: {}",comanda.getId());
        return comanda;
    }

    public List<Comanda> getComenziByClient(Long id){
        Client client = clientRepository.findById(id).orElseThrow();
        log.info("Searching for client's order with id: {}",client.getId());

        return comandaRepository.findComandaByClient(client);
    }

    public List<Comanda> getComenzi(){
        log.info("Searching for all orders");
        return comandaRepository.findAll();
    }


    public ComandaService(ClientRepository clientRepository, ComandaRepository comandaRepository, LinieComandaRepository linieComandaRepository, MaterialRepository materialRepository) {
        this.clientRepository = clientRepository;
        this.comandaRepository = comandaRepository;
        this.linieComandaRepository = linieComandaRepository;
        this.materialRepository = materialRepository;
    }

    public void deleteComanda(Long id){
        comandaRepository.deleteById(id);
    }

}
