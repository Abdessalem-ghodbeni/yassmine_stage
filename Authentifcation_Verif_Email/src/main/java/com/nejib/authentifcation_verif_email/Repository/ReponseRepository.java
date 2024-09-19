package com.nejib.authentifcation_verif_email.Repository;


import com.nejib.authentifcation_verif_email.Entites.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReponseRepository extends JpaRepository<Reponse, Long> {
    List<Reponse> findByQuestionIdQuestion(Long idQuestion);
}

