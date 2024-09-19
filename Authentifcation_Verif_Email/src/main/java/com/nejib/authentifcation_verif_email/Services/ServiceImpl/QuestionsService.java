package com.nejib.authentifcation_verif_email.Services.ServiceImpl;


import com.nejib.authentifcation_verif_email.Entites.Categorie;
import com.nejib.authentifcation_verif_email.Entites.Questions;
import com.nejib.authentifcation_verif_email.Entites.User;
import com.nejib.authentifcation_verif_email.Repository.CategorieRepository;
import com.nejib.authentifcation_verif_email.Repository.IUserRepository;
import com.nejib.authentifcation_verif_email.Repository.QuestionsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionsService {

    @Autowired
    private QuestionsRepository questionsRepository;
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    // Trouver toutes les questions
    public List<Questions> findAllQuestions() {
        return questionsRepository.findAll();
    }

    // Trouver toutes les questions d'une catégorie
    public List<Questions> findQuestionsByCategorie(Long idCategorie) {
        return questionsRepository.findByCategorieIdCategorie(idCategorie);
    }

    public List<Questions> gellUserQuestions(Long iduser) {
        return questionsRepository.findByUtilisateur_Id(iduser);
    }

    // Trouver une question par ID
    @Transactional
    public Optional<Questions> findQuestionById(Long id) {
        return questionsRepository.findById(id);
    }

    // Sauvegarder une question
    public Questions saveQuestion(Long idCategorie, Questions question,Long idUser) {
        Categorie cat = categorieRepository.findById(idCategorie)
                .orElseThrow(() -> new EntityNotFoundException("Categorie not found with id: " + idCategorie));
        User utilisateur=userRepository.findById(idUser).orElseThrow(() -> new EntityNotFoundException("user not found with id: " + idUser));
        question.setCategorie(cat);
        question.setUtilisateur(utilisateur);
        return questionsRepository.save(question);
    }




    public Questions incrementlike(Long idQuestion) {
        Optional<Questions> optionalQuestion = questionsRepository.findById(idQuestion);
        if (optionalQuestion.isPresent()) {
            Questions question = optionalQuestion.get();
            question.setLikes(question.getLikes() + 1); // Incrémenter le dislike
            return questionsRepository.save(question);
        }
        return null;
    }

    // Décrémenter le nombre de dislikes
    public Questions decrementlikes(Long idQuestion) {
        Optional<Questions> optionalQuestion = questionsRepository.findById(idQuestion);
        if (optionalQuestion.isPresent()) {
            Questions question = optionalQuestion.get();
            if (question.getLikes() > 0) { // S'assurer que le nombre de dislikes reste positif
                question.setLikes(question.getLikes() - 1); // Décrémenter le dislike
            }
            return questionsRepository.save(question);
        }
        return null;
    }

    // Incrémenter le nombre de dislikes
    public Questions incrementDislike(Long idQuestion) {
        Optional<Questions> optionalQuestion = questionsRepository.findById(idQuestion);
        if (optionalQuestion.isPresent()) {
            Questions question = optionalQuestion.get();
            question.setDislikes(question.getDislikes() + 1); // Incrémenter le dislike
            return questionsRepository.save(question);
        }
        return null;
    }

    // Décrémenter le nombre de dislikes
    public Questions decrementDislike(Long idQuestion) {
        Optional<Questions> optionalQuestion = questionsRepository.findById(idQuestion);
        if (optionalQuestion.isPresent()) {
            Questions question = optionalQuestion.get();
            if (question.getDislikes() > 0) { // S'assurer que le nombre de dislikes reste positif
                question.setDislikes(question.getDislikes() - 1); // Décrémenter le dislike
            }
            return questionsRepository.save(question);
        }
        return null;
    }

    // Supprimer une question
    public void deleteQuestion(Long id) {
        questionsRepository.deleteById(id);
    }

    // Marquer une question comme résolue
    public Questions markAsSolved(Long id) {
        Questions question = questionsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));
        question.setSolved(true);
        return questionsRepository.save(question);
    }


    public List<Questions> searchQuestions(String keyword) {
        return questionsRepository.findByTitreContainingOrDescriptionContaining(keyword, keyword);
    }


    public void toggleLike(Long questionId, Long userId) {
        Questions question = questionsRepository.findById(questionId).orElseThrow();

        if (question.getUserLikes().contains(userId)) {
            question.getUserLikes().remove(userId);
            question.setTotalLikes(question.getTotalLikes() - 1);
        } else {
            question.getUserLikes().add(userId);
            question.setTotalLikes(question.getTotalLikes() + 1);
        }
        questionsRepository.save(question);
    }

    public void toggleDislike(Long questionId, Long userId) {
        Questions question = questionsRepository.findById(questionId).orElseThrow();

        if (question.getUserDislikes().contains(userId)) {
            question.getUserDislikes().remove(userId);
            question.setTotalDislikes(question.getTotalDislikes() - 1);
        } else {
            question.getUserDislikes().add(userId);
            question.setTotalDislikes(question.getTotalDislikes() + 1);
        }
        questionsRepository.save(question);
    }





}
