package org.example.marmura_order_manager.service;


import org.example.marmura_order_manager.dto.LinieComandaDTO;
import org.example.marmura_order_manager.model.Material;
import org.example.marmura_order_manager.repository.ClientRepository;
import org.example.marmura_order_manager.repository.ComandaRepository;
import org.example.marmura_order_manager.repository.LinieComandaRepository;
import org.example.marmura_order_manager.repository.MaterialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ComandaServiceTest {
    private ComandaService comandaService;
    private ClientRepository clientRepository;
    private ComandaRepository comandaRepository;
    private MaterialRepository materialRepository;
    private LinieComandaRepository linieComandaRepository;

    @BeforeEach
    void setUp(){
        clientRepository = mock(ClientRepository.class);
        comandaRepository = mock(ComandaRepository.class);
        materialRepository = mock(MaterialRepository.class);
        linieComandaRepository = mock(LinieComandaRepository.class);

        comandaService = new ComandaService(clientRepository,comandaRepository,linieComandaRepository,materialRepository);
    }

    @Test
    void calculPretCant_cuCantStangaSiSus_returneazaPretCorect(){
        LinieComandaDTO dto = new LinieComandaDTO();
        dto.setLatime(80);
        dto.setLungime(200);
        dto.setCantStanga(true);
        dto.setCantSus(true);
        dto.setCantDreapta(false);
        dto.setCantJos(false);
        double rezultat = comandaService.calculPretCant(dto);
        assertEquals(112.0,rezultat);
    }

    @Test
    void calculPretCant_cantToate_returneazaZero(){
        LinieComandaDTO dto = new LinieComandaDTO();
        dto.setLatime(100);
        dto.setLungime(100);
        dto.setCantStanga(true);
        dto.setCantDreapta(true);
        dto.setCantSus(true);
        dto.setCantJos(true);

        double rezultat = comandaService.calculPretCant(dto);
        assertEquals(160.0,rezultat);
    }

    @Test
    void calculPretCant_faraCant_returneazaPretCorect(){
        LinieComandaDTO dto = new LinieComandaDTO();
        dto.setLatime(100);
        dto.setLungime(100);
        dto.setCantStanga(false);
        dto.setCantDreapta(false);
        dto.setCantSus(false);
        dto.setCantJos(false);

        double rezultat = comandaService.calculPretCant(dto);
        assertEquals(0.0,rezultat);
    }

    @Test
    void calcularePret_pentruMaterialExistent_returneazaPretCorect(){
        Material material = new Material();
        material.setName("Granit");
        material.setPret(200.0);
        material.setGrosime(2);
        when(materialRepository.findByNameAndGrosime("Granit",2))
                .thenReturn(Optional.of(material));

        LinieComandaDTO dto = new LinieComandaDTO();
        dto.setMaterial("Granit");
        dto.setLungime(50.0);
        dto.setLatime(100.0);
        dto.setGrosime(2);

        double rezultat = comandaService.calcularePret(dto);
        assertEquals(100.0,rezultat);
    }
}
