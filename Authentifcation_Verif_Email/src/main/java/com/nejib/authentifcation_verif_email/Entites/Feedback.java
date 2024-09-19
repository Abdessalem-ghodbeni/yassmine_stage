package com.nejib.authentifcation_verif_email.Entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFeedback;

    private String commentaire;

    @ManyToOne
    @JoinColumn(name = "reponse_id")
    private Reponse reponse;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private User utilisateur;


}
