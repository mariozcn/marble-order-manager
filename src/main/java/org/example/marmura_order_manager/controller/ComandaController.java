package org.example.marmura_order_manager.controller;

import org.example.marmura_order_manager.dto.ComandaDTO;
import org.example.marmura_order_manager.model.Comanda;
import org.example.marmura_order_manager.service.ComandaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/comenzi")
public class ComandaController {
    private final ComandaService comandaService;

    @PostMapping
    public Comanda postComanda(@RequestBody ComandaDTO comanda){
        return comandaService.creareComanda(comanda);
    }


    @GetMapping
    public List<Comanda> getComenzi(){
        return comandaService.getComenzi();
    }


    public ComandaController(ComandaService comandaService) {
        this.comandaService = comandaService;
    }
}
