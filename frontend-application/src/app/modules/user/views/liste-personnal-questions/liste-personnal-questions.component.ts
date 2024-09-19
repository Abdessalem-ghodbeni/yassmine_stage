import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { QuestionService } from 'src/app/core/services/questions/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-liste-personnal-questions',
  templateUrl: './liste-personnal-questions.component.html',
  styleUrls: ['./liste-personnal-questions.component.css'],
})
export class ListePersonnalQuestionsComponent implements OnInit {
  listeQuestions: any[] = [];
  reponsequestion: FormGroup;
  userId!: any;
  idquestion!: any;
  constructor(
    private router: Router,
    private questionService: QuestionService,
    private formBuilder: FormBuilder
  ) {
    // Initialiser le formulaire
    this.reponsequestion = this.formBuilder.group({
      texte: ['', Validators.required],
    });
  }
  loadCategories() {
    this.questionService.getAllPersonnelQuestions(this.userId).subscribe(
      (data) => {
        this.listeQuestions = data as any;
        console.log(this.listeQuestions);
      },
      (error) => {
        console.error('Error fetching questions', error);
      }
    );
  }

  ngOnInit(): void {
    const userconnect = localStorage.getItem('userconnect');
    if (userconnect) {
      const parsedUser = JSON.parse(userconnect);
      this.userId = parsedUser.id;
      console.log(this.userId);
    } else {
      console.log('Aucun utilisateur connecté trouvé dans le localStorage.');
    }
    this.loadCategories();
  }

  getImageUrl(image: string): string {
    const baseUrl = 'http://localhost:8089/freelance/auth';
    return `${baseUrl}/${image}`;
  }
  supprimerUneQuestion(id: number) {
    Swal.fire({
      title: 'Êtes-vous sûr ?',
      text: 'Cette action est irréversible !',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Oui, supprimer !',
    }).then((result) => {
      if (result.isConfirmed) {
        this.questionService.supprimerQuestion(id).subscribe(
          (response) => {
            Swal.fire({
              title: 'Supprimé !',
              text: 'La question a bien été supprimée.',
              icon: 'success',
            });
            this.loadCategories();
          },
          (error) => {
            Swal.fire({
              title: 'Erreur',
              text: 'Une erreur est survenue lors de la suppression.',
              icon: 'error',
            });
          }
        );
      }
    });
  }
  selectediD(id: number) {
    this.idquestion = id;
    console.log('dddd', this.idquestion);
  }
  validerResponse(id: number) {
    this.questionService.validerReponse(id).subscribe(
      (response) => {
        Swal.fire({
          icon: 'success',
          title: 'response validé avec succès',
          showConfirmButton: false,
          timer: 1500,
        });
        this.ngOnInit();
      },
      (error) => {
        console.error('Error adding question', error);
      }
    );
  }

  nSubmit() {
    if (this.reponsequestion.valid) {
      // Vérifier que l'ID utilisateur est présent
      if (this.userId) {
        this.questionService
          .ajouterResponse(
            this.reponsequestion.value,
            this.userId,
            this.idquestion
          )
          .subscribe(
            (response) => {
              Swal.fire({
                icon: 'success',
                title: 'response ajoutée avec succès',
                showConfirmButton: false,
                timer: 1500,
              });
              this.reponsequestion.reset();
              // Rediriger vers une autre page ou afficher un message de succès
              // this.router.navigate(['/questions']);
              this.ngOnInit();
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
