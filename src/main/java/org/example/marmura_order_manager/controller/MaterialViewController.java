package org.example.marmura_order_manager.controller;

import org.example.marmura_order_manager.model.Material;
import org.example.marmura_order_manager.service.ComandaService;
import org.example.marmura_order_manager.service.MaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value="/api/materiale")
public class MaterialViewController {
    private final MaterialService materialService;

    @GetMapping("/pagina")
    public String paginaMateriale(Model model){
        model.addAttribute("materiale", materialService.getMaterials());
        return "materiale";
    }



    @PostMapping("/adauga")
    public String adaugaMaterial(@ModelAttribute Material material) {
        materialService.createMaterial(material);
        return "redirect:/api/materiale/pagina";
    }

    public MaterialViewController(MaterialService materialService) {
        this.materialService = materialService;
    }
}
