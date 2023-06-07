package com.ensah.Service;


import com.ensah.Entity.Contact;

import java.util.List;

public interface IContactService {
    void createContact(Contact contact);
    void updateContact(Contact contact);
    void deleteContact(Long id);
    Contact getContact(Long id);

    List<Contact> getAllContacts();
    List<Contact> getAllContactsByGroupe(Long id);

    List<Contact> getAllByNames(String nom);

    List<Contact> getAllByNumber(String number);


    List<Contact> getAllByNumber1(String number);

    List<Contact> getAllByNumber2(String number);

    List<Contact> getAllByQuery(String searchQuery);

}
