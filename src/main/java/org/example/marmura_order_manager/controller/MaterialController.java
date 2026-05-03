package org.example.marmura_order_manager.controller;

import org.example.marmura_order_manager.model.Material;
import org.example.marmura_order_manager.service.MaterialService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/materiale")
public class MaterialController {
    private final MaterialService materialService;

    @GetMapping
    public List<Material> getMaterials(){
        return materialService.getMaterials();
    }

    @PostMapping
    public Material postMaterial(@RequestBody Material material){
        return materialService.createMaterial(material);
    }

    @DeleteMapping("/{id}")
    public void deleteMaterial(@PathVariable Long id){
        materialService.deleteMaterial(id);
    }
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }
}
