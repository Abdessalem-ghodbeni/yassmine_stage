package com.nejib.authentifcation_verif_email.Repository;


import com.nejib.authentifcation_verif_email.Entites.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Long> {
    List<Questions> findByCategorieIdCategorie(Long idCategorie);

    // Recherche dynamique par titre ou description
    List<Questions> findByTitreContainingOrDescriptionContaining(String titre, String description);
    List<Questions>findByUtilisateur_Id(Long id);
}


