import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { QuestionService } from 'src/app/core/services/questions/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-liste-user',
  templateUrl: './liste-user.component.html',
  styleUrls: ['./liste-user.component.css'],
})
export class ListeUserComponent implements OnInit {
  listeUser: any[] = [];

  idquestion!: any;
  constructor(
    private router: Router,
    private questionService: QuestionService
  ) {}
  loadUserListe() {
    this.questionService.getAllUser().subscribe(
      (data) => {
        this.listeUser = (data as any[]).filter(
          (user) => user.role !== 'ADMIN'
        );
        console.log(this.listeUser);
        console.log('olga', this.listeUser);
      },
      (error) => {
        console.error('Error fetching users', error);
      }
    );
  }

  ngOnInit(): void {
    this.loadUserListe();
  }

  getImageUrl(image: string): string {
    const baseUrl = 'http://localhost:8089/freelance/auth';
    return `${baseUrl}/${image}`;
  }

  desactivateUser(id: number) {
    Swal.fire({
      title: 'Êtes-vous sûr ?',
      text: 'Cette action est irréversible !',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Oui, Désactiver !',
    }).then((result) => {
      if (result.isConfirmed) {
        this.questionService.desactivateUser(id).subscribe(
          (response) => {
            Swal.fire({
              title: 'Désactivé !',
              text: 'cet compte utilisateur  a bien été desactivé.',
              icon: 'success',
            });
            this.loadUserListe();
          },
          (error) => {
            Swal.fire({
              title: 'Erreur',
              text: 'Une erreur est survenue lors de la desactivation.',
              icon: 'error',
            });
          }
        );
      }
    });
  }

  activateUser(id: number) {
    Swal.fire({
      title: 'Êtes-vous sûr ?',
      text: 'Cette action est irréversible !',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Oui, activer !',
    }).then((result) => {
      if (result.isConfirmed) {
        this.questionService.activateUser(id).subscribe(
          (response) => {
            Swal.fire({
              title: 'Désactivé !',
              text: 'cet compte utilisateur  a bien été activé.',
              icon: 'success',
            });
            this.loadUserListe();
          },
          (error) => {
            Swal.fire({
              title: 'Erreur',
              text: 'Une erreur est survenue lors d activation.',
              icon: 'error',
            });
          }
        );
      }
    });
  }
}
