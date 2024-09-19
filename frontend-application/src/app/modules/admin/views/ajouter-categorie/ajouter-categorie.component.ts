import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { QuestionService } from 'src/app/core/services/questions/question.service';

import Swal from 'sweetalert2';

@Component({
  selector: 'app-ajouter-categorie',
  templateUrl: './ajouter-categorie.component.html',
  styleUrls: ['./ajouter-categorie.component.css'],
})
export class AjouterCategorieComponent {
  constructor(
    private router: Router,
    private questiionService: QuestionService
  ) {}

  categorieForm = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(3)]),
  });

  AJOUTER() {
    if (this.categorieForm.valid) {
      this.questiionService
        .ajouterCategorie(this.categorieForm.value)
        .subscribe({
          next: () => {
            // Afficher une alerte de succès
            Swal.fire({
              icon: 'success',
              title: 'Catégorie ajoutée avec succès',
              showConfirmButton: false,
              timer: 1500,
            });
            // Rediriger l'utilisateur ou réinitialiser le formulaire
            this.router.navigate(['admin/liste-categorie']); // Modifiez le chemin selon vos besoins
          },
          error: (err) => {
            // Gérer les erreurs ici (facultatif)
            Swal.fire({
              icon: 'error',
              title: 'Erreur!',
              text: "Une erreur est survenue lors de l'ajout de la catégorie.",
            });
          },
        });
    } else {
      Swal.fire({
        icon: 'warning',
        title: 'Erreur de validation',
        text: 'Veuillez remplir le formulaire correctement.',
      });
    }
  }
}
