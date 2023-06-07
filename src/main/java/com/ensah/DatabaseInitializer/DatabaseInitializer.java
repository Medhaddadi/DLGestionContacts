package com.ensah.DatabaseInitializer;

import com.ensah.Entity.Contact;
import com.ensah.Entity.Groupe;
import com.ensah.database.IGroupeDao;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;


public class DatabaseInitializer implements CommandLineRunner {


    private IGroupeDao groupeRepository;

    @Override
    public void run(String... args) throws Exception {
        // FAker is a library to generate fake data
        Faker faker = new Faker();
        //(optionnel) Cr´eer des groupes automatiquement constitu´es des contacts
        //de mˆeme nom.
        // 1. Cr´eer 6  groupes
        // 2. Cr´eer 10 contacts par groupe
        for (int i = 0; i < 6; i++) {
            Groupe groupe = new Groupe();
            groupe.setNom(faker.name().lastName());
            for (int j = 0; j < 10; j++) {
                Contact contact = new Contact();
                contact.setNom(groupe.getNom());
                contact.setPrenom(faker.name().firstName());
                contact.setEmail_Personnel(faker.internet().emailAddress());
                contact.setEmail_Professionnel(faker.internet().emailAddress());
                contact.setAdresse(faker.address().fullAddress());
                contact.setTelephone1(faker.phoneNumber().phoneNumber());
                contact.setTelephone2(faker.phoneNumber().phoneNumber());
                groupe.getContacts().add(contact);
            }
            groupeRepository.save(groupe);
        }
    }
}
