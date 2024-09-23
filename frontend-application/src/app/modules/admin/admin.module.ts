import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { LayoutsAdminComponent } from './layouts-admin/layouts-admin.component';
import { AjouterCategorieComponent } from './views/ajouter-categorie/ajouter-categorie.component';
import { ListeCategoriesComponent } from './views/liste-categories/liste-categories.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ListeQuestionsComponent } from './views/liste-questions/liste-questions.component';
import { ListeUserComponent } from './views/liste-user/liste-user.component';
import { CategorieDetailsComponent } from './views/categorie-details/categorie-details.component';
import { UpdateCategorieComponent } from './views/update-categorie/update-categorie.component';

@NgModule({
  declarations: [
    LayoutsAdminComponent,
    AjouterCategorieComponent,
    ListeCategoriesComponent,
    ListeQuestionsComponent,
    ListeUserComponent,
    CategorieDetailsComponent,
    UpdateCategorieComponent,
  ],
  imports: [CommonModule, AdminRoutingModule, ReactiveFormsModule],
})
export class AdminModule {}
