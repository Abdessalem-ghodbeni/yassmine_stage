import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class QuestionService {
  constructor(private http: HttpClient) {}

  ajouterCategorie(categorie: any) {
    return this.http.post(
      `${environment.baseUrl}/api/categories/add`,
      categorie
    );
  }

  poserQuestion(question: any, idCategorie: any, iduser: any) {
    return this.http.post(
      `${environment.baseUrl}/api/questions/add/${idCategorie}/${iduser}`,
      question
    );
  }

  getAllCategori() {
    return this.http.get(`${environment.baseUrl}/api/categories/all`);
  }

  getAllQuestions() {
    return this.http.get(`${environment.baseUrl}/api/questions/all`);
  }

  getAllPersonnelQuestions(id: any) {
    return this.http.get(`${environment.baseUrl}/api/questions/all/${id}`);
  }

  ajouterResponse(question: any, iduser: any, idquestion: any) {
    return this.http.post(
      `${environment.baseUrl}/api/reponses/${idquestion}/${iduser}`,
      question
    );
  }
  validerReponse(idResponse: any) {
    return this.http
      .put(
        `${environment.baseUrl}/api/reponses/set-correct/${idResponse}`,
        null
      )
      .pipe(
        catchError((error) => {
          console.log('errrr', error);
          throw error;
        })
      );
  }

  supprimerQuestion(id: any) {
    return this.http
      .delete(`${environment.baseUrl}/api/questions/delete/${id}`)
      .pipe(
        catchError((error) => {
          console.log('errrr', error);
          throw error;
        })
      );
  }
  supprimerCategorie(id: any) {
    return this.http
      .delete(`${environment.baseUrl}/api/categories/delete/${id}`)
      .pipe(
        catchError((error) => {
          console.log('errrr', error);
          throw error;
        })
      );
  }
  betCategoriebyId(id: any) {
    return this.http
      .get(`${environment.baseUrl}/api/categories/cetegorieBY/${id}`)
      .pipe(
        catchError((error) => {
          console.log('errrr', error);
          throw error;
        })
      );
  }

  getAllUser() {
    return this.http.get(`${environment.baseUrl}/auth/allUser`);
  }

  desactivateUser(id_user: any) {
    return this.http
      .put(`${environment.baseUrl}/auth/desactivate/${id_user}`, null)
      .pipe(
        catchError((error) => {
          console.log('errrr', error);
          throw error;
        })
      );
  }

  activateUser(id_user: any) {
    return this.http
      .put(`${environment.baseUrl}/auth/activate/${id_user}`, null)
      .pipe(
        catchError((error) => {
          console.log('errrr', error);
          throw error;
        })
      );
  }

  // incrementer_dislike(idquestion: any) {
  //   return this.http
  //     .put(`${environment.baseUrl}/api/questions/${idquestion}/dislike`, null)
  //     .pipe(
  //       catchError((error) => {
  //         console.log('errrr', error);
  //         throw error;
  //       })
  //     );
  // }

  // Desincrementer_dislike(idquestion: any) {
  //   return this.http
  //     .put(
  //       `${environment.baseUrl}/api/questions/${idquestion}/dislike/decrement`,
  //       null
  //     )
  //     .pipe(
  //       catchError((error) => {
  //         console.log('errrr', error);
  //         throw error;
  //       })
  //     );
  // }

  // incrementer_liks(idquestion: any) {
  //   return this.http
  //     .put(`${environment.baseUrl}/api/questions/${idquestion}/likes`, null)
  //     .pipe(
  //       catchError((error) => {
  //         console.log('errrr', error);
  //         throw error;
  //       })
  //     );
  // }

  // deincrementer_liks(idquestion: any) {
  //   return this.http
  //     .put(
  //       `${environment.baseUrl}/api/questions/${idquestion}/like/decrement`,
  //       null
  //     )
  //     .pipe(
  //       catchError((error) => {
  //         console.log('errrr', error);
  //         throw error;
  //       })
  //     );
  // }

  incrementer_liks(idquestion: any, userId: any) {
    return this.http
      .put(
        `${environment.baseUrl}/api/questions/${idquestion}/like?userId=${userId}`,
        null
      )
      .pipe(
        catchError((error) => {
          console.log('errrr', error);
          throw error;
        })
      );
  }

  incrementer_dislike(idquestion: any, userId: any) {
    return this.http
      .put(
        `${environment.baseUrl}/api/questions/${idquestion}/dislike?userId=${userId}`,
        null
      )
      .pipe(
        catchError((error) => {
          console.log('errrr', error);
          throw error;
        })
      );
  }
}
