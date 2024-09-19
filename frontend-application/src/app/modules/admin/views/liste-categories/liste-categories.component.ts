import { Component, OnInit } from '@angular/core';
import { QuestionService } from 'src/app/core/services/questions/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-liste-categories',
  templateUrl: './liste-categories.component.html',
  styleUrls: ['./liste-categories.component.css'],
})
export class ListeCategoriesComponent implements OnInit {
  listeCategorie: any[] = [];
  Category!: any;
  idcategorie!: number;
  constructor(private questionService: QuestionService) {}
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
  }

  selectediD(id: number) {
    this.idcategorie = id;
    console.log('dddd', this.idcategorie);
    this.getCategorieByid();
  }

  getCategorieByid() {
    this.questionService.betCategoriebyId(this.idcategorie).subscribe(
      (response) => {
        this.Category = response as any;
      },
      (error) => {
        alert('somthing was warrning');
      }
    );
  }

  supprimercategorie(id: number) {
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
        this.questionService.supprimerCategorie(id).subscribe(
          (response) => {
            Swal.fire({
              title: 'Supprimé !',
              text: 'La categorie a bien été supprimée.',
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
}
