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
}
