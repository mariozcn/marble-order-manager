package org.example.marmura_order_manager.controller;

import org.example.marmura_order_manager.model.Client;
import org.example.marmura_order_manager.model.Comanda;
import org.example.marmura_order_manager.model.LinieComanda;
import org.example.marmura_order_manager.model.Material;
import org.example.marmura_order_manager.model.Status;
import org.example.marmura_order_manager.service.ClientService;
import org.example.marmura_order_manager.service.ComandaService;
import org.example.marmura_order_manager.service.MaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class DashboardController {
    private final ComandaService comandaService;
    private final ClientService clientService;
    private final MaterialService materialService;

    public DashboardController(ComandaService comandaService,
                               ClientService clientService,
                               MaterialService materialService) {
        this.comandaService = comandaService;
        this.clientService = clientService;
        this.materialService = materialService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Comanda> comenzi = comandaService.getComenzi();
        List<Client> clienti = clientService.totiClientii();
        List<Material> materiale = materialService.getMaterials();

        double venitTotal = comenzi.stream()
                .flatMap(c -> c.getLinii() == null ? java.util.stream.Stream.empty() : c.getLinii().stream())
                .mapToDouble(LinieComanda::getPret)
                .sum();

        long comenziNoua = comenzi.stream().filter(c -> c.getStatus() == Status.Noua).count();
        long comenziInLucru = comenzi.stream().filter(c -> c.getStatus() == Status.InLucru).count();
        long comenziGata = comenzi.stream().filter(c -> c.getStatus() == Status.Gata).count();
        long comenziRidicata = comenzi.stream().filter(c -> c.getStatus() == Status.Ridicata).count();
        long comenziActive = comenziNoua + comenziInLucru + comenziGata;

        List<Map<String, Object>> topClienti = comenzi.stream()
                .filter(c -> c.getClient() != null)
                .collect(Collectors.groupingBy(c -> c.getClient().getId()))
                .values().stream()
                .map(group -> {
                    Client cli = group.get(0).getClient();
                    double total = group.stream()
                            .flatMap(c -> c.getLinii() == null ? java.util.stream.Stream.empty() : c.getLinii().stream())
                            .mapToDouble(LinieComanda::getPret)
                            .sum();
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", cli.getId());
                    map.put("nume", cli.getName());
                    map.put("telefon", cli.getTelefon());
                    map.put("total", total);
                    map.put("comenzi", (long) group.size());
                    return map;
                })
                .sorted((a, b) -> Double.compare((double) b.get("total"), (double) a.get("total")))
                .limit(5)
                .collect(Collectors.toList());

        List<Comanda> comenziRecente = new ArrayList<>(comenzi);
        comenziRecente.sort(Comparator.comparing(
                Comanda::getDataComenzii,
                Comparator.nullsLast(Comparator.reverseOrder())));
        if (comenziRecente.size() > 8) {
            comenziRecente = comenziRecente.subList(0, 8);
        }

        model.addAttribute("totalComenzi", comenzi.size());
        model.addAttribute("totalClienti", clienti.size());
        model.addAttribute("totalMateriale", materiale.size());
        model.addAttribute("venitTotal", venitTotal);
        model.addAttribute("comenziActive", comenziActive);
        model.addAttribute("comenziNoua", comenziNoua);
        model.addAttribute("comenziInLucru", comenziInLucru);
        model.addAttribute("comenziGata", comenziGata);
        model.addAttribute("comenziRidicata", comenziRidicata);
        model.addAttribute("topClienti", topClienti);
        model.addAttribute("comenziRecente", comenziRecente);
        model.addAttribute("today", LocalDate.now());

        return "dashboard";
    }
}
