package org.example.marmura_order_manager.service;


import org.example.marmura_order_manager.model.Material;
import org.example.marmura_order_manager.model.TIP_MATERIAL;
import org.example.marmura_order_manager.repository.MaterialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MaterialServiceTest {
    private MaterialRepository materialRepository;
    private MaterialService materialService;


    @BeforeEach
    void setup(){
        materialRepository = mock(MaterialRepository.class);
        materialService = new MaterialService(materialRepository);
    }

    @Test
    void createMaterial_shouldSaveAndReturnMaterial(){
        Material material = new Material();
        material.setName("Test");
        material.setGrosime(2);
        material.setId(1L);
        material.setPret(100);
        material.setOrigine("Italia");
        material.setTipMaterial(TIP_MATERIAL.Marmura);

        when(materialRepository.save(any(Material.class))).thenReturn(material);

        Material result = materialService.createMaterial(material);
        assertEquals(1L,result.getId());
        assertEquals("Test",result.getName());
        assertEquals(2,result.getGrosime());
        assertEquals(100,result.getPret());
        assertEquals("Italia",result.getOrigine());
        assertEquals(TIP_MATERIAL.Marmura,result.getTipMaterial());
    }

    @Test
    void updateMaterialTest(){
        Material material = new Material();
        material.setName("Vechi");
        material.setGrosime(3);
        material.setId(1L);
        material.setPret(200);
        material.setOrigine("China");
        material.setTipMaterial(TIP_MATERIAL.Granit);

        when(materialRepository.save(material)).thenReturn(material);
        material.setName("Nou");
        material.setGrosime(2);
        material.setId(2L);
        material.setPret(100);
        material.setOrigine("Spania");
        material.setTipMaterial(TIP_MATERIAL.Quartz);
        Material result = materialService.createMaterial(material);

        assertEquals(2L,result.getId());
        assertEquals("Nou",result.getName());
        assertEquals(2,result.getGrosime());
        assertEquals(100,result.getPret());
        assertEquals("Spania",result.getOrigine());
        assertEquals(TIP_MATERIAL.Quartz,result.getTipMaterial());
    }

    @Test
    void cautaMaterialDupaId(){
        Material material = new Material();
        material.setName("testt");
        material.setId(1L);

        when(materialRepository.findById(1L)).thenReturn(Optional.of(material));
        Material result = materialService.getMaterialById(1L);

        assertEquals(1L,result.getId());
        assertEquals("testt",result.getName());
    }

    @Test
    void getAllMaterialsTest(){
        Material mat1 = new Material();
        Material mat2 = new Material();
        Material mat3 = new Material();
        mat1.setName("t1");
        mat2.setName("t2");
        mat3.setName("t3");

        when(materialRepository.findAll()).thenReturn(List.of(mat1,mat2,mat3));
        List<Material> result = materialService.getMaterials();

        assertEquals(3, result.size());
        assertEquals("t1", result.get(0).getName());
        assertEquals("t2", result.get(1).getName());
        assertEquals("t3", result.get(2).getName());
        verify(materialRepository).findAll();
    }

    @Test
    void deleteMaterialTest(){
        Material material = new Material();
        material.setId(1L);
        when(materialRepository.findById(1L)).thenReturn(Optional.of(material));
        materialService.deleteMaterial(1L);
        verify(materialRepository).deleteById(1L);
    }

}
