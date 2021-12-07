import { NgModule } from '@angular/core';
import { BrowserModule, } from '@angular/platform-browser';
import { NgSelectModule } from '@ng-select/ng-select';
import { FormsModule ,ReactiveFormsModule } from '@angular/forms';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatInputModule} from '@angular/material/input';
import {MatGridListModule} from '@angular/material/grid-list';
import { AppRoutingModule } from './app-routing.module';
import { CommonModule } from '@angular/common';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
import { ViewComplaintsComponent } from './components/department/view-complaints/view-complaints.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HomePageComponent } from './home-page/home-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { DepartmentDashboardComponent } from './components/department/department-dashboard/department-dashboard.component';
import { DepartmentHomeComponent } from './components/department/department-home/department-home.component';
import { DepartmentSidebarComponent } from './components/department/department-sidebar/department-sidebar.component';
import { DepartmentReportComponent } from './components/department/department-report/department-report.component'
import { MatNativeDateModule, MAT_DATE_LOCALE } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import {MatButtonModule} from '@angular/material/button';
import {DatePipe} from '@angular/common';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {ToastrModule}  from 'ngx-toastr';
import { AdminReportComponent } from './components/admin/admin-report/admin-report.component';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list'; 
import {MatCardModule} from '@angular/material/card';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatDialogModule} from '@angular/material/dialog';
import { LoginOtpComponent } from './login-otp/login-otp.component';
import { AdminHomeComponent } from './components/admin/admin-home/admin-home.component';
import { FooterComponent } from './footer/footer.component';
import { AddDepartmentComponent } from './components/admin/add-department/add-department.component';
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard.component';
import { AdminSidebarComponent } from './components/admin/admin-sidebar/admin-sidebar.component';
import { BlockUserComponent } from './components/admin/block-user/block-user.component';
import { FeedbackComponent } from './components/admin/feedback/feedback.component';
import { ViewCitizenComponent } from './components/admin/view-citizen/view-citizen.component';
import { ViewDepartmentComponent } from './components/admin/view-department/view-department.component';
import { ViewComplainComponent } from './components/admin/view-complain/view-complain.component';
import { ChangePasswordComponent } from './components/citizen/change-password/change-password.component';
import { CitizenDashboardComponent } from './components/citizen/citizen-dashboard/citizen-dashboard.component';
import { CitizenSidebarComponent } from './components/citizen/citizen-sidebar/citizen-sidebar.component';
import { CitizenHomeComponent } from './components/citizen/citizen-home/citizen-home.component';
import { MakeComplainComponent } from './components/citizen/make-complain/make-complain.component';
import { SendReminderComponent } from './components/citizen/send-reminder/send-reminder.component';
import { ViewPublicComplainsComponent } from './components/citizen/view-public-complains/view-public-complains.component';
import { RegisterationPageComponent } from './registeration-page/registeration-page.component';
import { NgxCaptchaModule } from 'ngx-captcha';
import { ViewComplainStatusComponent } from './components/citizen/view-complain-status/view-complain-status.component';
import { AddFeedbackComponent } from './components/department/add-feedback/add-feedback.component';
@NgModule({
  declarations: [
    AppComponent,
    ViewComplaintsComponent,
    NavbarComponent,
    HomePageComponent,
    LoginPageComponent,
    DepartmentDashboardComponent,
    DepartmentHomeComponent,
    DepartmentSidebarComponent,
    DepartmentReportComponent,
    AdminReportComponent,
    LoginOtpComponent,
    AdminHomeComponent,
    FooterComponent,
    AddDepartmentComponent,
    AdminDashboardComponent,
    AdminSidebarComponent,
    BlockUserComponent,
    FeedbackComponent,
    ViewCitizenComponent,
    ViewDepartmentComponent,
    ViewComplainComponent,
    ChangePasswordComponent,
    CitizenDashboardComponent,
    CitizenSidebarComponent,
    CitizenHomeComponent,
    MakeComplainComponent,
    SendReminderComponent,
    ViewComplaintsComponent,
    ViewPublicComplainsComponent,
    RegisterationPageComponent,
  ViewComplainStatusComponent,
  AddFeedbackComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    BrowserAnimationsModule,
    MatIconModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
    MatFormFieldModule,
    MatGridListModule,
    MatSelectModule,
    MatButtonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSidenavModule,
    MatListModule,
    MatCardModule,
    MatTooltipModule,
    MatDialogModule,
    ToastrModule.forRoot({}),
    NgxCaptchaModule
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent,DepartmentSidebarComponent]
})
export class AppModule { }
