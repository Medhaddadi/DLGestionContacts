package com.ensah.database;


import com.ensah.Entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContactDao extends JpaRepository<Contact,Long> {

    // find all by name Like

    @Query("select c from Contact c where c.Nom like %?1%")
    List<Contact> findAllByNomLike(String nom);


    // find all by order alphabetique for Nom else For Prenom
    @Query("select c from Contact c order by c.Nom,c.Prenom")
    List<Contact> findAllByOrderNom();

    // recherche par numero en pricise le type de telephone1
    @Query("select c from Contact c where c.Telephone1 like %?1%  order by c.Nom,c.Prenom")
    List<Contact> findAllByTelephone1(String telephone1);

    // recherche par numero en pricise le type de telephone2
    @Query("select c from Contact c where c.Telephone2 like %?1% order by c.Nom,c.Prenom")
    List<Contact> findAllByTelephone2(String telephone2);


    @Query("select c from Contact c where c.Telephone1 like %?1% or c.Telephone2 like %?1% order by c.Nom,c.Prenom")
    List<Contact> findAllByTelephone1OrTelephone2(String number, String number1);

    @Query("select c from Contact c where c.Nom like %?1% or c.Prenom like %?1% or c.Email_Personnel  like %?1% or c.Email_Professionnel like %?1% or c.Adresse like %?1% or c.Telephone1 like %?1% or c.Telephone2 like %?1% order by c.Nom,c.Prenom")
    List<Contact> findAllByQuery(String searchQuery);

}
