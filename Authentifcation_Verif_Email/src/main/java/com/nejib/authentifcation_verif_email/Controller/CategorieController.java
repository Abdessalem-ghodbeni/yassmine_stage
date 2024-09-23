package com.nejib.authentifcation_verif_email.Controller;


import com.nejib.authentifcation_verif_email.Entites.Categorie;
import com.nejib.authentifcation_verif_email.Services.ServiceImpl.CategorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequestMapping("/api/categories")

@RestController
@CrossOrigin("*")

@RequiredArgsConstructor
public class CategorieController {


    private final CategorieService categorieService;

    @PostMapping(path = "add")
    public ResponseEntity<Categorie> createCategorie(@RequestBody Categorie categorie) {
        Categorie createdCategorie = categorieService.saveCategorie(categorie);
        return new ResponseEntity<>(createdCategorie, HttpStatus.CREATED);
    }

    @GetMapping(path = "all")
    public ResponseEntity<List<Categorie>> getAllCategories() {
        List<Categorie> categories = categorieService.findAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("cetegorieBY/{id}")
    public ResponseEntity<Categorie> getCategorieById(@PathVariable Long id) {
        Optional<Categorie> categorie = categorieService.findCategorieById(id);
        return categorie.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("categroey/edit/{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable Long id, @RequestBody Categorie categorieDetails) {
        Optional<Categorie> categorieOptional = categorieService.findCategorieById(id);
        if (!categorieOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Categorie categorie = categorieOptional.get();
        categorie.setName(categorieDetails.getName());

        Categorie updatedCategorie = categorieService.saveCategorie(categorie);
        return new ResponseEntity<>(updatedCategorie, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) {
        Optional<Categorie> categorieOptional = categorieService.findCategorieById(id);
        if (!categorieOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        categorieService.deleteCategorie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
