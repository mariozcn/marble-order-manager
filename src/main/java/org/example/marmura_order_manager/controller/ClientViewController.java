package org.example.marmura_order_manager.controller;


import org.example.marmura_order_manager.model.Client;
import org.example.marmura_order_manager.model.Comanda;
import org.example.marmura_order_manager.model.LinieComanda;
import org.example.marmura_order_manager.model.Status;
import org.example.marmura_order_manager.service.ClientService;
import org.example.marmura_order_manager.service.ComandaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value="api/clienti")
public class ClientViewController {
    private final ClientService clientService;
    private final ComandaService comandaService;

    public ClientViewController(ClientService clientService, ComandaService comandaService) {
        this.clientService = clientService;
        this.comandaService = comandaService;
    }

    @GetMapping("/pagina")
    public String paginaClienti(Model model) {
        model.addAttribute("clienti", clientService.totiClientii());
        return "clienti";
    }

    @PostMapping("/adauga")
    public String adaugaClientForm(@ModelAttribute Client client) {
        clientService.salveazaClient(client);
        return "redirect:/api/clienti/pagina";
    }

    @GetMapping("/{id}/comenzi-pagina")
    public String paginaComenziClient(@PathVariable Long id,Model model){
        Client client = clientService.getClientById(id);
        List<Comanda> comenzi = comandaService.getComenziByClient(id);


        double totalCheltuit = comenzi.stream()
                .flatMap(c -> c.getLinii().stream())
                .mapToDouble(LinieComanda::getPret)
                .sum();

        model.addAttribute("client",client);
        model.addAttribute("comenzi",comenzi);
        model.addAttribute("totalCheltuit", totalCheltuit);

        return "comenzi-client";
    }

    @PatchMapping("/{id}/status/{status}")
    @ResponseBody
    public void updateStatus(@PathVariable Long id, @PathVariable Status status) {
        comandaService.updateComanda(id, status);
    }

}