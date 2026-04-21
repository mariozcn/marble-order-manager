package org.example.marmura_order_manager.controller;

import org.example.marmura_order_manager.service.ComandaService;
import org.example.marmura_order_manager.service.MaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/comenzi")
public class ComandaViewController {
    private final ComandaService comandaService;
    private final MaterialService materialService;

    public ComandaViewController(ComandaService comandaService, MaterialService materialService) {
        this.comandaService = comandaService;
        this.materialService = materialService;
    }

    @GetMapping("/pagina")
    public String paginaComenzi(Model model) {
        model.addAttribute("comenzi", comandaService.getComenzi());
        model.addAttribute("materiale", materialService.getMaterials());
        return "comenzi";
    }
}