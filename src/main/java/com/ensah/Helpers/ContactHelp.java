package com.ensah.Helpers;

import com.ensah.Entity.Contact;

public class ContactHelp {

    public static Contact copyContact(Contact oldContact,Contact newContact){
        oldContact.setNom(newContact.getNom());
        oldContact.setPrenom(newContact.getPrenom());
        oldContact.setAdresse(newContact.getAdresse());
        oldContact.setEmail_Personnel(newContact.getEmail_Personnel());
        oldContact.setEmail_Professionnel(newContact.getEmail_Professionnel());
        oldContact.setTelephone1(newContact.getTelephone1());
        oldContact.setTelephone2(newContact.getTelephone2());
        oldContact.setGroupes(newContact.getGroupes());
        return oldContact;
    }
}
