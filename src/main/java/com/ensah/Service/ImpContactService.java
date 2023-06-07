package com.ensah.Service;

import com.ensah.Entity.Contact;
import com.ensah.database.IContactDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpContactService implements IContactService {
    @Autowired
    private IContactDao contactDao;

    @Override
    public void createContact(Contact contact) {
        contactDao.save(contact);
    }

    @Override
    public void updateContact(Contact contact) {
        System.out.println("Contact: " + contact);
        contactDao.save(contact);
    }

    @Override
    public void deleteContact(Long id) {
        //delete contact from all groups
        Contact contact = contactDao.findById(id).get();
        contact.getGroupes().forEach(groupe -> groupe.getContacts().remove(contact));
        contactDao.deleteById(id);
    }

    @Override
    public Contact getContact(Long id) {
        return  contactDao.findById(id).get();
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactDao.findAll();
    }

    @Override
    public List<Contact> getAllContactsByGroupe(Long id) {
        return null;
    }


    @Override
    public List<Contact> getAllByNames(String nom) {
        return contactDao.findAllByNomLike(nom);
    }

    @Override
    public List<Contact> getAllByNumber(String number) {
        return contactDao.findAllByTelephone1OrTelephone2(number, number);
    }

    @Override
    public List<Contact> getAllByNumber1(String number) {
        return contactDao.findAllByTelephone1(number);
    }

    @Override
    public List<Contact> getAllByNumber2(String number) {
        return contactDao.findAllByTelephone2(number);
    }

    @Override
    public List<Contact> getAllByQuery(String searchQuery) {
        return contactDao.findAllByQuery(searchQuery);
    }

}
