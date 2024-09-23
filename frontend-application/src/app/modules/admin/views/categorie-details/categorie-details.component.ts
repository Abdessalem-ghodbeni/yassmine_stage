import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/core/services/questions/question.service';

@Component({
  selector: 'app-categorie-details',
  templateUrl: './categorie-details.component.html',
  styleUrls: ['./categorie-details.component.css'],
})
export class CategorieDetailsComponent implements OnInit {
  id!: number;
  Category!: any;
  constructor(
    private route: ActivatedRoute,
    private questionService: QuestionService
  ) {}
  getCategorieByid() {
    this.questionService.betCategoriebyId(this.id).subscribe(
      (response) => {
        this.Category = response as any;
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
