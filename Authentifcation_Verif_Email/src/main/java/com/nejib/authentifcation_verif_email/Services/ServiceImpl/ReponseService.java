package com.nejib.authentifcation_verif_email.Services.ServiceImpl;


import com.nejib.authentifcation_verif_email.Entites.Questions;
import com.nejib.authentifcation_verif_email.Entites.Reponse;
import com.nejib.authentifcation_verif_email.Entites.User;
import com.nejib.authentifcation_verif_email.Repository.IUserRepository;
import com.nejib.authentifcation_verif_email.Repository.QuestionsRepository;
import com.nejib.authentifcation_verif_email.Repository.ReponseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReponseService {


    private final ReponseRepository reponseRepository;

    private final IUserRepository userRepository;
    private final QuestionsRepository questionsRepository;

    public List<Reponse> findAllReponses() {
        return reponseRepository.findAll();
    }

    public Optional<Reponse> findReponseById(Long id) {
        return reponseRepository.findById(id);
    }
@Transactional
    public Reponse saveReponseForQuestion(Long idQuestion, Reponse reponse,Long useId) {
        Questions question = questionsRepository.findById(idQuestion)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + idQuestion));

        User uts=userRepository.findById(useId) .orElseThrow(() -> new EntityNotFoundException("user not found with id: " + useId));
        reponse.setQuestion(question);
        reponse.setUtilisateur(uts);
        question.setSolved(true);
        questionsRepository.save(question);

        return reponseRepository.save(reponse);
    }

    public List<Reponse> findReponsesByQuestionId(Long idQuestion) {
        return reponseRepository.findByQuestionIdQuestion(idQuestion);
    }

    public void deleteReponse(Long id) {
        reponseRepository.deleteById(id);
    }

//    public Reponse setCorrectAnswer(Long idReponse) {
//        Reponse reponse = reponseRepository.findById(idReponse)
//                .orElseThrow(() -> new EntityNotFoundException("Response not found with id: " + idReponse));
//
//        // Set all responses to not correct first
//        List<Reponse> responses = reponseRepository.findByQuestionIdQuestion(reponse.getQuestion().getIdQuestion());
//        for (Reponse resp : responses) {
//            resp.setCorrectAnswer(true);
//            reponseRepository.save(resp);
//        }
//
//        // Mark the selected response as correct
//        reponse.setCorrectAnswer(true);
//        Reponse updatedReponse = reponseRepository.save(reponse);
//
//        // Mark the question as solved
//        Questions question = reponse.getQuestion();
//        question.setSolved(true);
//        questionsRepository.save(question);
//
//        return updatedReponse;
//    }

    public Reponse setCorrectAnswer(Long idReponse) {
        // Récupérer la réponse par son ID
        Reponse reponse = reponseRepository.findById(idReponse)
                .orElseThrow(() -> new EntityNotFoundException("Réponse non trouvée avec l'id : " + idReponse));

        // Vérifier si la réponse n'est pas déjà correcte
        if (!reponse.isCorrectAnswer()) {
            // Définir la réponse comme correcte
            reponse.setCorrectAnswer(true);

            // Sauvegarder la réponse mise à jour
            Reponse updatedReponse = reponseRepository.save(reponse);

            // Marquer la question associée comme résolue
            Questions question = reponse.getQuestion();
            question.setSolved(true);
            questionsRepository.save(question);

            return updatedReponse;
        } else {
            // Si la réponse est déjà correcte, on peut retourner un message ou une exception selon le besoin
            throw new IllegalStateException("Cette réponse est déjà marquée comme correcte.");
        }
    }

}
