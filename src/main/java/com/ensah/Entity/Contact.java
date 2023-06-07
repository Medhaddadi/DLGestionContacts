package com.ensah.Entity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Data
@Entity
@NoArgsConstructor(force = true)
@AllArgsConstructor
@ToString
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Le Nom est obligatoire")
    @Size(min = 3, max = 20, message = "Le Nom doit contenir entre 3 et 20 caractères")
    private String Nom;

    @NotEmpty(message = "Le Prenom est obligatoire")
    @Size(min = 3, max = 20, message = "Le Prenom doit contenir entre 3 et 20 caractères")
    private String Prenom;

    @Pattern(regexp = "^[0-9]{10}$",message = "Le Telephone Personnel doit contenir 10 chiffres")
    @NotEmpty(message = "Le Telephone Personnel est obligatoire")
    private String Telephone1;

    @Pattern(regexp = "^[0-9]{10}$",message = "Le Telephone Professionnel doit contenir 10 chiffres")
    private String Telephone2;

    @NotEmpty(message = "Le Telephone Professionnel est obligatoire")
    @Email(message = "Email Personnel non valide")
    private String Email_Personnel;

    @Email(message = "Email Professionnel non valide")
    private String Email_Professionnel;

    @NotEmpty(message = "L'Adresse est obligatoire")
    private String Adresse;

    @NotEmpty(message = "Le Genre est obligatoire")
    private String Genre;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @PreRemove
    protected void onDelete() {
        deletedAt = LocalDateTime.now();
    }
    //a dd creation date and update date and delete date

    @ManyToMany(mappedBy = "contacts")
    private List<Groupe> groupes=new ArrayList<>();
}
