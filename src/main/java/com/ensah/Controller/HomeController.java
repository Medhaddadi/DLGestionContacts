package com.ensah.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController     {
    @GetMapping
    public String home( Model model) {
        model.addAttribute("message", "Bienvenue sur notre application de gestion de contacts");
        return "Contacts/index";
    }
}
