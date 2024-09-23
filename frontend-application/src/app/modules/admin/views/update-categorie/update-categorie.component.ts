import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { QuestionService } from 'src/app/core/services/questions/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-categorie',
  templateUrl: './update-categorie.component.html',
  styleUrls: ['./update-categorie.component.css'],
})
export class UpdateCategorieComponent implements OnInit {
  id!: number;
  Category!: any;
  constructor(
    private route: ActivatedRoute,
    private questionService: QuestionService,
    private _router: Router
  ) {}
  categorieForm = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(3)]),
  });
  modifierCetgorie() {
    Swal.fire({
      title: 'Are you sure?',
      text: 'Do you want to modify this category?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, update it!',
    }).then((result) => {
      console.log(this.Category);
      if (result.isConfirmed) {
        this.questionService
          .modifierCategore(this.id, this.categorieForm.value)
          .subscribe(
            (response) => {
              Swal.fire('Updated!', 'Category has been updated.', 'success');
              this._router.navigate(['admin/liste-categorie']);
            },
            (error) => {
              Swal.fire(
                'Error!',
                'Something went wrong during the update.',
                'error'
              );
              console.error('Error during category update:', error);
            }
          );
      }
    });
  }

  getCategorieByid() {
    this.questionService.betCategoriebyId(this.id).subscribe(
      (response) => {
        this.categorieForm.patchValue(response);
      },
      (error) => {
        alert('somthing was warrning');
      }
    );
  }
  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.id = +params['id'];
      console.log(this.id);
      this.getCategorieByid();
    });
  }
}
