package com.ensah.Controller;


import com.ensah.Helpers.ContactHelp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.ensah.Entity.Contact;
import com.ensah.Service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    private IContactService iContactService;

    @GetMapping("/")
    public String getAll(Model model) {
        model.addAttribute("contacts", iContactService.getAllContacts());
        return "Contacts/index";
    }

    @GetMapping("/add")
    public String addContact(Model model) {
        model.addAttribute("contact", new Contact());
        return "Contacts/create";
    }

    @PostMapping("/add")
    public String addContact(@Valid @ModelAttribute("contact") Contact contact,
                             BindingResult result, RedirectAttributes redirectAttributes) {
        System.out.println("Contact: " + contact);
        if (result.hasErrors()) {
            return "Contacts/create";
        }
        iContactService.createContact(contact);
        redirectAttributes.addFlashAttribute("success", "Contact added successfully");
        return "redirect:/contacts/";
    }

    @GetMapping("/edit/{id}")
    public String updateContact(Model model, @PathVariable("id") Long id) {
        model.addAttribute("contact", iContactService.getContact(id));
        return "Contacts/edit";
    }

    @PostMapping("/edit")
    public String updateContact(@Valid @ModelAttribute("contact") Contact newContact, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "Contacts/edit";
        }
        iContactService.updateContact(newContact);

        redirectAttributes.addFlashAttribute("success", "Contact" + newContact.getId() + " updated successfully");
        return "redirect:/contacts/edit/" + newContact.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(RedirectAttributes redirectAttributes, @PathVariable String id) {
        iContactService.deleteContact(Long.valueOf(id));
        redirectAttributes.addFlashAttribute("success", "Contact deleted successfully");
        return "redirect:/contacts/";
    }

    @GetMapping("/show/{id}")
    public String detailsContact(Model model, @PathVariable Long id) {
        model.addAttribute("contact", iContactService.getContact(id));
        return "Contacts/show";
    }

    @PostMapping("/show")
    public String detailsContact(Model model, @ModelAttribute("contact") Contact contact) {
        model.addAttribute("contact", iContactService.getContact(contact.getId()));
        return "Contacts/show";
    }

    @PostMapping("/search")
    public String searchContacts(@RequestParam("searchType") String searchType,
                                 @RequestParam("searchQuery") String searchQuery,
                                 Model model) {
        List<Contact> contacts = switch (searchType) {
            case "nom" -> iContactService.getAllByNames(searchQuery);
            case "telephone_personnel" -> iContactService.getAllByNumber1(searchQuery);
            case "telephone_pro" -> iContactService.getAllByNumber2(searchQuery);
            case "all" -> iContactService.getAllByQuery(searchQuery);
            default -> iContactService.getAllContacts();
        };

        model.addAttribute("contacts", contacts);

        return "Contacts/index";
    }
}
