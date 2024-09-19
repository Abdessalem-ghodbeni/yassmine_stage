package com.nejib.authentifcation_verif_email.Entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idQuestion;

    private String titre;
    private String description;

    // Champ pour indiquer si la question est résolue ou non
    private boolean isSolved = false;
private  Integer likes=0;
private Integer dislikes=0;


    private int totalLikes = 0;
    private int totalDislikes = 0;

    @ElementCollection
    private Set<Long> userLikes = new HashSet<>();

    @ElementCollection
    private Set<Long> userDislikes = new HashSet<>();
    @ManyToOne
    private Categorie categorie;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private User utilisateur;

// @JsonIgnore
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private Set<Reponse> reponses;


}
