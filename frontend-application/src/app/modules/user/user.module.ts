import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { LayoutUserComponent } from './layout-user/layout-user.component';
import { ProfileUserComponent } from './views/profile-user/profile-user.component';
import { PoserQuestionComponent } from './views/poser-question/poser-question.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ListeQuestionsComponent } from './views/liste-questions/liste-questions.component';
import { ListePersonnalQuestionsComponent } from './views/liste-personnal-questions/liste-personnal-questions.component';

@NgModule({
  declarations: [
    LayoutUserComponent,
    ProfileUserComponent,
    PoserQuestionComponent,
    ListeQuestionsComponent,
    ListePersonnalQuestionsComponent,
  ],
  imports: [CommonModule, UserRoutingModule, ReactiveFormsModule],
})
export class UserModule {}
