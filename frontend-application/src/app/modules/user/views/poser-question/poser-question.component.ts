import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { QuestionService } from 'src/app/core/services/questions/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-poser-question',
  templateUrl: './poser-question.component.html',
  styleUrls: ['./poser-question.component.css'],
})
export class PoserQuestionComponent implements OnInit {
  listeCategorie: any[] = [];
  questionForm: FormGroup;
  userId!: any;
  constructor(
    private router: Router,
    private questionService: QuestionService,
    private formBuilder: FormBuilder
  ) {
    // Initialiser le formulaire
    this.questionForm = this.formBuilder.group({
      titre: ['', Validators.required],
      description: ['', Validators.required],
      categorieId: ['', Validators.required],
    });
  }
  loadCategories() {
    this.questionService.getAllCategori().subscribe(
      (data) => {
        this.listeCategorie = data as any;
        console.log(this.listeCategorie);
      },
      (error) => {
        console.error('Error fetching categories', error);
      }
    );
  }

  ngOnInit(): void {
    this.loadCategories();
    const userconnect = localStorage.getItem('userconnect');
    if (userconnect) {
      const parsedUser = JSON.parse(userconnect); // Parse le JSON en objet
      this.userId = parsedUser.id; // Récupère l'ID
      console.log(this.userId);
    } else {
      console.log('Aucun utilisateur connecté trouvé dans le localStorage.');
    }
  }

  onSubmit() {
    if (this.questionForm.valid) {
      // Vérifier que l'ID utilisateur est présent
      if (this.userId) {
        // Récupérer les données du formulaire
        const questionData = this.questionForm.value;
        const categorieId = questionData.categorieId;

        // Envoyer la requête au service pour ajouter la question
        this.questionService
          .poserQuestion(questionData, categorieId, this.userId)
          .subscribe(
            (response) => {
              Swal.fire({
                icon: 'success',
                title: 'question ajoutée avec succès',
                showConfirmButton: false,
                timer: 1500,
              });
              // Rediriger vers une autre page ou afficher un message de succès
              this.router.navigate(['user/liste-question']);
            },
            (error) => {
              console.error('Error adding question', error);
            }
          );
      } else {
        console.error('User not found in localStorage');
      }
    }
  }
}
