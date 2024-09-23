import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutsAdminComponent } from './layouts-admin/layouts-admin.component';
import { AjouterCategorieComponent } from './views/ajouter-categorie/ajouter-categorie.component';
import { ListeCategoriesComponent } from './views/liste-categories/liste-categories.component';
import { ListeQuestionsComponent } from './views/liste-questions/liste-questions.component';
import { ListeUserComponent } from './views/liste-user/liste-user.component';
import { CategorieDetailsComponent } from './views/categorie-details/categorie-details.component';
import { UpdateCategorieComponent } from './views/update-categorie/update-categorie.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutsAdminComponent,
    children: [
      {
        path: 'ajouter-categorie',
        component: AjouterCategorieComponent,
      },
      {
        path: 'liste-categorie',
        component: ListeCategoriesComponent,
      },
      {
        path: 'liste-question',
        component: ListeQuestionsComponent,
      },
      {
        path: 'liste-user',
        component: ListeUserComponent,
      },
      {
        path: 'categorie-details/:id',
        component: CategorieDetailsComponent,
      },
      {
        path: 'categorie-edit/:id',
        component: UpdateCategorieComponent,
      },
    ],
  },
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {}
