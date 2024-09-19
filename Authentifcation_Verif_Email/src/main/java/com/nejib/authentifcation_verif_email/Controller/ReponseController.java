package com.nejib.authentifcation_verif_email.Controller;

 ;
 import com.nejib.authentifcation_verif_email.Entites.Reponse;
 import com.nejib.authentifcation_verif_email.Services.ServiceImpl.ReponseService;
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
@RequestMapping("/api/reponses")
public class ReponseController {


    private final ReponseService reponseService;

    // Créer une réponse et marquer la question comme résolue
    @PostMapping("/{idQuestion}/{userid}")
    public ResponseEntity<Reponse> createReponse(@PathVariable Long idQuestion,@PathVariable Long userid, @RequestBody Reponse reponse) {
        Reponse createdReponse = reponseService.saveReponseForQuestion(idQuestion, reponse,userid);
        return new ResponseEntity<>(createdReponse, HttpStatus.CREATED);
    }

    // Obtenir toutes les réponses
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Reponse>> getAllReponses() {
        List<Reponse> reponses = reponseService.findAllReponses();
        return new ResponseEntity<>(reponses, HttpStatus.OK);
    }

    // Obtenir les réponses pour une question donnée
    @GetMapping("/ByQuestion/{idQuestion}")
    public ResponseEntity<List<Reponse>> getReponsesByQuestion(@PathVariable Long idQuestion) {
        List<Reponse> reponses = reponseService.findReponsesByQuestionId(idQuestion);
        return new ResponseEntity<>(reponses, HttpStatus.OK);
    }

    // Obtenir une réponse par ID
    @GetMapping("/{id}")
    public ResponseEntity<Reponse> getReponseById(@PathVariable Long id) {
        Optional<Reponse> reponse = reponseService.findReponseById(id);
        return reponse.map(r -> new ResponseEntity<>(r, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Supprimer une réponse
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReponse(@PathVariable Long id) {
        Optional<Reponse> reponse = reponseService.findReponseById(id);
        if (!reponse.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        reponseService.deleteReponse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/set-correct/{id}")
    public ResponseEntity<Reponse> setCorrectAnswer(@PathVariable Long id) {
        Reponse updatedReponse = reponseService.setCorrectAnswer(id);
        return ResponseEntity.ok(updatedReponse);
    }
}
