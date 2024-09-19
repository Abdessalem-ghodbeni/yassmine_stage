package com.nejib.authentifcation_verif_email.Controller;


import com.nejib.authentifcation_verif_email.Entites.Questions;
import com.nejib.authentifcation_verif_email.Services.ServiceImpl.QuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/questions")
public class QuestionsController {

    private final QuestionsService questionsService;

    // Créer une nouvelle question
    @PostMapping("add/{idCategorie}/{iduser}")
    public ResponseEntity<Questions> createQuestion(@PathVariable Long idCategorie,@PathVariable Long iduser, @RequestBody Questions question) {
        Questions createdQuestion = questionsService.saveQuestion(idCategorie,question,iduser);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    // Obtenir toutes les questions
    @GetMapping(path = "all")
    public ResponseEntity<List<Questions>> getAllQuestions() {
        List<Questions> questions = questionsService.findAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }


    @GetMapping(path = "all/{idpu}")
    public ResponseEntity<List<Questions>> getAllPersonelQuestions(@PathVariable("idpu") Long idpu) {
        List<Questions> questions = questionsService.gellUserQuestions(idpu);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    // Obtenir toutes les questions d'une catégorie
    @GetMapping("/ByCategorie/{idCategorie}")
    public ResponseEntity<List<Questions>> getQuestionsByCategorie(@PathVariable Long idCategorie) {
        List<Questions> questions = questionsService.findQuestionsByCategorie(idCategorie);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    // Obtenir une question par ID
    @GetMapping("/{id}")
    public ResponseEntity<Questions> getQuestionById(@PathVariable Long id) {
        Optional<Questions> question = questionsService.findQuestionById(id);
        return question.map(q -> new ResponseEntity<>(q, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Marquer une question comme résolue
    @PutMapping("/{id}/markAsSolved")
    public ResponseEntity<Questions> markAsSolved(@PathVariable Long id) {
        Questions updatedQuestion = questionsService.markAsSolved(id);
        return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
    }

    // Rechercher une question dynamiquement par mot-clé
    @GetMapping("/search")
    public ResponseEntity<List<Questions>> searchQuestions(@RequestParam String keyword) {
        List<Questions> questions = questionsService.searchQuestions(keyword);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    // Mettre à jour une question
//    @PutMapping("/{id}")
//    public ResponseEntity<Questions> updateQuestion(@PathVariable Long id, @RequestBody Questions questionDetails) {
//        Optional<Questions> questionOptional = questionsService.findQuestionById(id);
//        if (!questionOptional.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        Questions question = questionOptional.get();
//        question.setTitre(questionDetails.getTitre());
//        question.setDescription(questionDetails.getDescription());
//
//        Questions updatedQuestion = questionsService.saveQuestion(question.getCategorie().getIdCategorie(), question);
//        return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
//    }

    // Supprimer une question
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        Optional<Questions> questionOptional = questionsService.findQuestionById(id);
        if (!questionOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        questionsService.deleteQuestion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


//
//
//    @PutMapping("/{id}/dislike")
//    public ResponseEntity<Questions> dislikeQuestion(@PathVariable Long id) {
//        Questions question = questionsService.incrementDislike(id);
//        return question != null ? ResponseEntity.ok(question) : ResponseEntity.notFound().build();
//    }
//
//
//    @PutMapping("/{id}/dislike/decrement")
//    public ResponseEntity<Questions> decrementDislike(@PathVariable Long id) {
//        Questions question = questionsService.decrementDislike(id);
//        return question != null ? ResponseEntity.ok(question) : ResponseEntity.notFound().build();
//    }
//
//
//
//
//
//
//
//    @PutMapping("/{id}/likes")
//    public ResponseEntity<Questions> likeQuestion(@PathVariable Long id) {
//        Questions question = questionsService.incrementlike(id);
//        return question != null ? ResponseEntity.ok(question) : ResponseEntity.notFound().build();
//    }
//
//
//    @PutMapping("/{id}/like/decrement")
//    public ResponseEntity<Questions> decrementlike(@PathVariable Long id) {
//        Questions question = questionsService.decrementlikes(id);
//        return question != null ? ResponseEntity.ok(question) : ResponseEntity.notFound().build();
//    }
//
//




    @PutMapping("/{id}/like")
    public ResponseEntity<?> toggleLike(@PathVariable Long id, @RequestParam Long userId) {
        questionsService.toggleLike(id, userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/dislike")
    public ResponseEntity<?> toggleDislike(@PathVariable Long id, @RequestParam Long userId) {
        questionsService.toggleDislike(id, userId);
        return ResponseEntity.ok().build();
    }

}

