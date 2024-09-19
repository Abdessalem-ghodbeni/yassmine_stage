import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutUserComponent } from './layout-user/layout-user.component';
import { ProfileUserComponent } from './views/profile-user/profile-user.component';
import { PoserQuestionComponent } from './views/poser-question/poser-question.component';
import { ListeQuestionsComponent } from './views/liste-questions/liste-questions.component';
import { ListePersonnalQuestionsComponent } from './views/liste-personnal-questions/liste-personnal-questions.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutUserComponent,
    children: [
      {
        path: 'poser-question',
        component: PoserQuestionComponent,
      },
      {
        path: 'liste-question',
        component: ListeQuestionsComponent,
      },

      {
        path: 'personnal-question',
        component: ListePersonnalQuestionsComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserRoutingModule {}
