package org.example.marmura_order_manager.controller;


import org.example.marmura_order_manager.model.Client;
import org.example.marmura_order_manager.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="api/clienti")
public class ClientViewController {
    private final ClientService clientService;

    public ClientViewController(ClientService clientService) {
        this.clientService = clientService;
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

}
