import { Component, OnInit } from '@angular/core';
import { QuestionService } from 'src/app/core/services/questions/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-liste-questions',
  templateUrl: './liste-questions.component.html',
  styleUrls: ['./liste-questions.component.css'],
})
export class ListeQuestionsComponent implements OnInit {
  listeQuestions: any[] = [];
  ngOnInit(): void {
    this.LoadlisteQuestions();
  }
  constructor(private questionService: QuestionService) {}
  LoadlisteQuestions() {
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
            this.LoadlisteQuestions();
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
}
