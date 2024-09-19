package com.nejib.authentifcation_verif_email.Services.ServiceImpl;


import com.nejib.authentifcation_verif_email.Entites.Feedback;
import com.nejib.authentifcation_verif_email.Entites.Reponse;
import com.nejib.authentifcation_verif_email.Repository.FeedbackRepository;
import com.nejib.authentifcation_verif_email.Repository.ReponseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor

public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private ReponseRepository reponseRepository;

    // Ajouter un feedback à une réponse
    public Feedback addFeedbackToReponse(Long idReponse, Feedback feedback) {
        Reponse reponse = reponseRepository.findById(idReponse)
                .orElseThrow(() -> new EntityNotFoundException("Reponse not found with id: " + idReponse));
        feedback.setReponse(reponse);
        return feedbackRepository.save(feedback);
    }

    // Récupérer les feedbacks d'une réponse
    public List<Feedback> getFeedbacksForReponse(Long idReponse) {
        return feedbackRepository.findByReponseIdReponse(idReponse);
    }

    // Supprimer un feedback
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }

    // Trouver un feedback par ID
    public Optional<Feedback> findFeedbackById(Long id) {
        return feedbackRepository.findById(id);
    }
}
