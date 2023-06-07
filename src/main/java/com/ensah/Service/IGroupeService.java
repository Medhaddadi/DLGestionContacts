package com.ensah.Service;

import com.ensah.Entity.Contact;
import com.ensah.Entity.Groupe;

import java.util.List;

public interface IGroupeService {
    void createGroupe(Groupe groupe);
    void updateGroupe(Groupe groupe);
    void deleteGroupe(Long id);
    Groupe getGroupe(Long id);

    List<Groupe> getAllGroupes();
    List<Groupe> getAllGroupesByNames(String nom);

    List<Groupe> getAllGroupesByNamesLike(String nom);

    List<Groupe> getAllGroupesByQuery(String searchQuery);

    List<Contact> getAllGroupesNotInGroupe(Long id);
}
