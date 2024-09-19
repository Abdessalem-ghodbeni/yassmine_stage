package com.nejib.authentifcation_verif_email.Services.ServiceImpl;


import com.nejib.authentifcation_verif_email.Entites.Categorie;
import com.nejib.authentifcation_verif_email.Repository.CategorieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategorieService {


    private final CategorieRepository categorieRepository;

    public List<Categorie> findAllCategories() {
        return categorieRepository.findAll();
    }

    public Optional<Categorie> findCategorieById(Long id) {
        return categorieRepository.findById(id);
    }

    public Categorie saveCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }
}
