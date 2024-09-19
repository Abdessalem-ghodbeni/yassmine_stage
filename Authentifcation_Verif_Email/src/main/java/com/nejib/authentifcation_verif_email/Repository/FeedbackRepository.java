package com.nejib.authentifcation_verif_email.Repository;


import com.nejib.authentifcation_verif_email.Entites.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByReponseIdReponse(Long idReponse);
}
