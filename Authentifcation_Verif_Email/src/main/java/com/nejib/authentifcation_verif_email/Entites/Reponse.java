package com.nejib.authentifcation_verif_email.Entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Reponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReponse;

    private String texte;
@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Questions question;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private User utilisateur;

    private boolean isCorrectAnswer = false; // Default to false

}

