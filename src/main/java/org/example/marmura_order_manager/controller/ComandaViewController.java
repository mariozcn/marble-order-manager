package org.example.marmura_order_manager.controller;

import org.example.marmura_order_manager.model.Status;
import org.example.marmura_order_manager.service.ComandaService;
import org.example.marmura_order_manager.service.MaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/comenzi")
public class ComandaViewController {
    private final ComandaService comandaService;
    private final MaterialService materialService;

    @GetMapping("/{id}/detalii")
    public String detaliiComanda(@PathVariable Long id, Model model) {
        model.addAttribute("comanda", comandaService.getComandaById(id));
        return "detalii-comanda";
    }
    @GetMapping("/{id}/status/{status}")
    public String updateStatus(@PathVariable Long id, @PathVariable String status) {
        comandaService.updateComanda(id, Status.valueOf(status));
        return "redirect:/api/comenzi/" + id + "/detalii";
    }

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