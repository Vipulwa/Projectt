import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './home-page/home-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterationPageComponent } from './registeration-page/registeration-page.component';
import { AdminDashboardComponent } from './admin/admin-dashboard/admin-dashboard.component';
import { AddDepartmentComponent } from './admin/add-department/add-department.component';
// import { RemoveDepartmentComponent } from './admin/remove-department/remove-department.component';
import { ViewDepartmentComponent } from './admin/view-department/view-department.component';
import { AdminHomeComponent } from './admin/admin-home/admin-home.component';
import { ViewCitizenComponent } from './admin/view-citizen/view-citizen.component';
import { ViewComplainComponent } from './admin/view-complain/view-complain.component';
import { BlockUserComponent } from './admin/block-user/block-user.component';
import { FeedbackComponent } from './admin/feedback/feedback.component';

const routes: Routes = [
  { path: '', component: HomePageComponent, pathMatch: 'full' },
  { path: 'login', component: LoginPageComponent, pathMatch: 'full' },
  {
    path: 'register',
    component: RegisterationPageComponent,
    pathMatch: 'full',
  },

  {
    path: 'admin-dash',
    component: AdminDashboardComponent,
    children: [
      {
        path: 'admin-home',
        component: AdminHomeComponent,
      },
      {
        path: 'add-dept',
        component: AddDepartmentComponent,
      },
      {
        path: 'view-dept',
        component: ViewDepartmentComponent,
      },
      {
        path: 'view-citizen',
        component: ViewCitizenComponent,
      },
      {
        path: 'view-complaint',
        component: ViewComplainComponent,
      },
      {
        path: 'block-user',
        component: BlockUserComponent,
      },
      {
        path: 'feedback',
        component: FeedbackComponent,
      }
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
