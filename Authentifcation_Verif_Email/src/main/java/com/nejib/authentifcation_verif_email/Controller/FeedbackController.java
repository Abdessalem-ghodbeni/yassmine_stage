package com.nejib.authentifcation_verif_email.Controller;


import com.nejib.authentifcation_verif_email.Entites.Feedback;
import com.nejib.authentifcation_verif_email.Services.ServiceImpl.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin("*")

@RequiredArgsConstructor

@RequestMapping("/api/feedbacks")
public class FeedbackController {


    private final FeedbackService feedbackService;

    // Ajouter un feedback à une réponse
    @PostMapping("/{idReponse}")
    public ResponseEntity<Feedback> addFeedbackToReponse(@PathVariable Long idReponse, @RequestBody Feedback feedback) {
        Feedback createdFeedback = feedbackService.addFeedbackToReponse(idReponse, feedback);
        return new ResponseEntity<>(createdFeedback, HttpStatus.CREATED);
    }

    // Obtenir les feedbacks pour une réponse donnée
    @GetMapping("/ByReponse/{idReponse}")
    public ResponseEntity<List<Feedback>> getFeedbacksForReponse(@PathVariable Long idReponse) {
        List<Feedback> feedbacks = feedbackService.getFeedbacksForReponse(idReponse);
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }

    // Supprimer un feedback
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        Optional<Feedback> feedback = feedbackService.findFeedbackById(id); // Utilisez une méthode pour obtenir un seul feedback par son ID
        if (feedback.isPresent()) {
            feedbackService.deleteFeedback(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
