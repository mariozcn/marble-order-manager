package org.example.marmura_order_manager.service;

import org.example.marmura_order_manager.model.Material;
import org.example.marmura_order_manager.repository.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {
    private final MaterialRepository materialRepository;

    public List<Material> getMaterials(){
        return materialRepository.findAll();
    }

    public Material createMaterial(Material material){
        return materialRepository.save(material);
    }

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public void deleteMaterial(Long id){
        materialRepository.deleteById(id);
    }

    public Material updateMaterial(Long id, Material material) {
        Material existent = materialRepository.findById(id).orElseThrow();
        existent.setName(material.getName());
        existent.setOrigine(material.getOrigine());
        existent.setPret(material.getPret());
        existent.setGrosime(material.getGrosime());
        existent.setTipMaterial(material.getTipMaterial());

        return materialRepository.save(existent);
    }
}
