package com.ensah.Controller;


import com.ensah.Entity.Contact;
import com.ensah.Entity.Groupe;
import com.ensah.Service.IContactService;
import com.ensah.Service.IGroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import java.util.List;


@Controller
@RequestMapping("/groups")
public class GroupeControllet {
    @Autowired
    private IGroupeService groupeService;
    @Autowired
    private IContactService contactService;


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("groupes", groupeService.getAllGroupes());
        return "Groupes/index";
    }

    @GetMapping("/add")
    public String create(Model model) {
        model.addAttribute("groupe", new Groupe());
        model.addAttribute("contacts", contactService.getAllContacts());
        return "Groupes/create";
    }

    @PostMapping("/add")
    public String create(@Valid @ModelAttribute("groupe") Groupe groupe, RedirectAttributes redirectAttributes, BindingResult result, @RequestParam List<Long> contacts) {
        if (result.hasErrors()) {
            return "Groupes/create";
        }

        groupeService.createGroupe(groupe);
        for (Long id : contacts) {
            Contact contact = contactService.getContact(id);
            groupe.getContacts().add(contact);
        }
        redirectAttributes.addFlashAttribute("success", "Groupe added successfully");
        return "redirect:/groups/";
    }

    @GetMapping("/edit/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        model.addAttribute("groupe", groupeService.getGroupe(id));
        List<Contact> contacts = groupeService.getAllGroupesNotInGroupe(id);
        model.addAttribute("contacts", contacts);
        return "Groupes/edit";
    }

    @PostMapping("/edit")
    public String editGroup(@Valid @ModelAttribute("groupe") Groupe groupe, @RequestParam(required = false) List<Long> contacts, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "Groupes/edit";
        }
        for (Long id : contacts) {
            Contact contact = contactService.getContact(id);
            if (!groupe.getContacts().contains(contact))
                groupe.getContacts().add(contact);
        }
        groupeService.updateGroupe(groupe);
        redirectAttributes.addFlashAttribute("success", "Groupe updated successfully");
        return "redirect:/groups/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        groupeService.deleteGroupe(id);
        return "forward:/groups/";
    }

    @GetMapping("/show/{id}")
    public String details(Model model, @PathVariable("id") Long id) {
        model.addAttribute("groupe", groupeService.getGroupe(id));
        return "Groupes/show";
    }

    @GetMapping("/show/{id}/contacts")
    public String showContacts(RedirectAttributes redirectAttributes, @PathVariable("id") Long id) {
        List<Contact> contacts = groupeService.getAllGroupesNotInGroupe(id);
        redirectAttributes.addFlashAttribute("contacts", contacts);
        return "redirect:/groups/show/" + id;
    }

    @PostMapping("/addContacts/{id}")
    public String addContacts(@PathVariable("id") Long id, @RequestParam(required = false) List<Long> contacts, RedirectAttributes redirectAttributes) {
        Groupe groupe = groupeService.getGroupe(id);
        if (contacts != null) {
            for (Long idContact : contacts) {
                groupe.getContacts().add(contactService.getContact(idContact));
            }
        }
        groupeService.updateGroupe(groupe);
        redirectAttributes.addFlashAttribute("success", "Contacts added successfully");
        return "redirect:/groups/show/" + id;
    }

    @PostMapping("/search")
    public String searchContacts(@RequestParam("searchType") String searchType,
                                 @RequestParam("searchQuery") String searchQuery,
                                 Model model) {

        List<Groupe> contacts;
        if (searchType.equals("nom")) {
            contacts = groupeService.getAllGroupesByNamesLike(searchQuery);
        } else {
            contacts = groupeService.getAllGroupesByQuery(searchQuery);
        }
        model.addAttribute("groupes", contacts);

        return "Groupes/index";
    }

    @GetMapping("/removeContact/{idGroupe}/{idContact}")
    public String removeContact(@PathVariable("idGroupe") Long idGroupe, @PathVariable("idContact") Long idContact, RedirectAttributes redirectAttributes) {
        Groupe groupe = groupeService.getGroupe(idGroupe);
        Contact contact = contactService.getContact(idContact);
        groupe.getContacts().remove(contact);
        groupeService.updateGroupe(groupe);
        redirectAttributes.addFlashAttribute("success", "Contact removed successfully");
        return "redirect:/groups/";
    }

}
