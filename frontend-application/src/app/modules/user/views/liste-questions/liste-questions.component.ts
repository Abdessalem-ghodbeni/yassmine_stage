import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { QuestionService } from 'src/app/core/services/questions/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-liste-questions',
  templateUrl: './liste-questions.component.html',
  styleUrls: ['./liste-questions.component.css'],
})
export class ListeQuestionsComponent implements OnInit {
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
    this.questionService.getAllQuestions().subscribe(
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
    this.loadCategories();
    const userconnect = localStorage.getItem('userconnect');
    if (userconnect) {
      const parsedUser = JSON.parse(userconnect);
      this.userId = parsedUser.id;
      console.log(this.userId);
    } else {
      console.log('Aucun utilisateur connecté trouvé dans le localStorage.');
    }
  }

  getImageUrl(image: string): string {
    const baseUrl = 'http://localhost:8089/vegoo/auth';
    return `${baseUrl}/${image}`;
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

  toggleLike(questionId: any) {
    const userconnect = localStorage.getItem('userconnect');
    if (userconnect) {
      const parsedUser = JSON.parse(userconnect);
      const userId = parsedUser.id;

      this.questionService.incrementer_liks(questionId, userId).subscribe(
        (response) => {
          console.log('Like toggled');
          this.loadCategories(); // Recharger la liste pour voir les mises à jour
        },
        (error) => {
          console.error('Error toggling like', error);
        }
      );
    } else {
      console.error('User not found in localStorage');
    }
  }

  toggleDislike(questionId: any) {
    const userconnect = localStorage.getItem('userconnect');
    if (userconnect) {
      const parsedUser = JSON.parse(userconnect);
      const userId = parsedUser.id;

      this.questionService.incrementer_dislike(questionId, userId).subscribe(
        (response) => {
          console.log('Dislike toggled');
          this.loadCategories();
        },
        (error) => {
          console.error('Error toggling dislike', error);
        }
      );
    } else {
      console.error('User not found in localStorage');
    }
  }
}
