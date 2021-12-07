import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewComplaintsComponent } from './components/department/view-complaints/view-complaints.component';
import { DepartmentHomeComponent } from './components/department/department-home/department-home.component';
import { DepartmentReportComponent } from './components/department/department-report/department-report.component';
import { AdminReportComponent } from './components/admin/admin-report/admin-report.component';
import { LoginOtpComponent } from './login-otp/login-otp.component';


//admin
import { HomePageComponent } from './home-page/home-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard.component';
import { AddDepartmentComponent } from './components/admin/add-department/add-department.component';
import { ViewDepartmentComponent } from './components/admin/view-department/view-department.component';
import { AdminHomeComponent } from './components/admin/admin-home/admin-home.component';
import { ViewCitizenComponent } from './components/admin/view-citizen/view-citizen.component';
import { ViewComplainComponent } from './components/admin/view-complain/view-complain.component';
import { BlockUserComponent } from './components/admin/block-user/block-user.component';
import { FeedbackComponent } from './components/admin/feedback/feedback.component';

//citizen
import { RegisterationPageComponent } from './registeration-page/registeration-page.component';
import { CitizenDashboardComponent } from './components/citizen/citizen-dashboard/citizen-dashboard.component';
import { CitizenHomeComponent } from './components/citizen/citizen-home/citizen-home.component';
import { MakeComplainComponent } from './components/citizen/make-complain/make-complain.component';
import { ViewComplainStatusComponent } from './components/citizen/view-complain-status/view-complain-status.component';
import { SendReminderComponent } from './components/citizen/send-reminder/send-reminder.component';
import { ViewPublicComplainsComponent } from './components/citizen/view-public-complains/view-public-complains.component';
import { ChangePasswordComponent } from './components/citizen/change-password/change-password.component';
import { DepartmentDashboardComponent } from './components/department/department-dashboard/department-dashboard.component';
import { AddFeedbackComponent } from './components/department/add-feedback/add-feedback.component';


const routes: Routes = [
  {path:'department-dashboard',component:DepartmentDashboardComponent,
  children: [
    {path:'department-home',component:DepartmentHomeComponent},
    {path:'department-complaints-active/:status',component:ViewComplaintsComponent},
    {path:'department-report',component:DepartmentReportComponent},
    {path:'department-complaints-closed/:status',component:ViewComplaintsComponent},
    {path:'department-complaints-reminder/:status',component:ViewComplaintsComponent},
    {path:'department-change-password',component:ChangePasswordComponent},
    {path:'department-feedback',component:AddFeedbackComponent},
    
  ]
  },
  


//admin
  { path: '', component: HomePageComponent, pathMatch: 'full' },
  { path: 'login', component: LoginPageComponent, pathMatch: 'full' },
  {path:'login-with-otp',component:LoginOtpComponent},
  {
    path: 'register',
    component: RegisterationPageComponent,
    pathMatch: 'full',
  },

  {
    path: 'admin-dash',component: AdminDashboardComponent,
    children: [
      {path: 'admin-home',component: AdminHomeComponent, },
      {path: 'add-dept',component: AddDepartmentComponent, },
      {path: 'view-dept', component: ViewDepartmentComponent, },
      {path: 'view-citizen',component: ViewCitizenComponent,},
      {path: 'view-complaint', component: ViewComplainComponent,},
      { path: 'block-user', component: BlockUserComponent,},
      { path: 'feedback', component: FeedbackComponent,},
      {path:'admin-report',component:AdminReportComponent},
    ],
  },

  //citizen

  {
    path: 'citizen-dash',
    component: CitizenDashboardComponent,
    children: [
      {path: 'citizen-home',component: CitizenHomeComponent,},
      { path: 'make-complain', component: MakeComplainComponent,},
      {path: 'view-complain-status',component: ViewComplainStatusComponent,},
      {path: 'send-reminder',component: SendReminderComponent, },
      {path: 'view-public-complains', component: ViewPublicComplainsComponent, },
      { path: 'change-password', component: ChangePasswordComponent,},
      {path:'citizen-feedback',component:AddFeedbackComponent},
    ],
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes),],
  exports: [RouterModule]
})
export class AppRoutingModule { }
