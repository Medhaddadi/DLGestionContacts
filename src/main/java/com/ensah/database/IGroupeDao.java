package com.ensah.database;


import com.ensah.Entity.Contact;
import com.ensah.Entity.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGroupeDao extends JpaRepository<Groupe,Long> {

    @Query("select g from Groupe g where g.Nom = ?1")
    List<Groupe> findAllByNom(String nom);

    @Query("select g from Groupe g where g.Nom like %?1%")
    List<Groupe> findAllByNomLike(String nom);

    @Query("select g from Groupe g where g.Nom like %?1% or concat( g.id ,'' )  like %?1%")
    List<Groupe> findAllByQuery(String searchQuery);

    @Query("select c from Contact c where c not in (select c from Contact c join c.groupes g where g.id = ?1)")
    List<Contact> findAllContactsNotInGroupe(Long id);
}
