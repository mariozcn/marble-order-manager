package org.example.marmura_order_manager.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Value("${app.demo.password}")
    private String demoPassword;

    @GetMapping("/")
    public String landing(Model model) {
        model.addAttribute("demoUsername", "demo");
        model.addAttribute("demoPassword", demoPassword);
        return "landing";
    }
}