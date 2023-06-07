package com.ensah.Service;


import com.ensah.Entity.Contact;
import com.ensah.Entity.Groupe;
import com.ensah.database.IGroupeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class implGroupeService  implements IGroupeService{
    @Autowired
    private IGroupeDao groupeDao;

    @Override
    public void createGroupe(Groupe groupe) {
        groupeDao.save(groupe);
    }

    @Override
    public void updateGroupe(Groupe groupe) {
        groupeDao.save(groupe);
    }

    @Override
    public void deleteGroupe(Long id) {
        Groupe groupe = groupeDao.findById(id).get();
        groupe.getContacts().clear();
        groupeDao.deleteById(id);
    }

    @Override
    public Groupe getGroupe(Long id) {
        return groupeDao.findById(id).get();
    }

    @Override
    public List<Groupe> getAllGroupes() {
        return groupeDao.findAll();
    }

    @Override
    public List<Groupe> getAllGroupesByNames(String nom) {
        return groupeDao.findAllByNom(nom);
    }

    @Override
    public List<Groupe> getAllGroupesByNamesLike(String nom) {
        return groupeDao.findAllByNomLike(nom);
    }

    @Override
    public List<Groupe> getAllGroupesByQuery(String searchQuery) {
        return groupeDao.findAllByQuery( searchQuery);
    }

    @Override
    public List<Contact> getAllGroupesNotInGroupe(Long id) {
        return groupeDao.findAllContactsNotInGroupe(id);
    }
}
