import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NavbarComponent } from './navbar/navbar.component';
import { HomePageComponent } from './home-page/home-page.component';
import { FooterComponent } from './footer/footer.component';
import { RegisterationPageComponent } from './registeration-page/registeration-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminDashboardComponent } from './admin/admin-dashboard/admin-dashboard.component';
import { AdminHomeComponent } from './admin/admin-home/admin-home.component';
import { AdminSidebarComponent } from './admin/admin-sidebar/admin-sidebar.component';
import { AddDepartmentComponent } from './admin/add-department/add-department.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
// import { RemoveDepartmentComponent } from './admin/remove-department/remove-department.component';
import { ViewDepartmentComponent } from './admin/view-department/view-department.component';
import { ViewCitizenComponent } from './admin/view-citizen/view-citizen.component';
import { ViewComplainComponent } from './admin/view-complain/view-complain.component';
import { PracticeComponent } from './practice/practice.component';
import { HttpClientModule } from '@angular/common/http';
import { BlockUserComponent } from './admin/block-user/block-user.component';
import { NgxCaptchaModule } from 'ngx-captcha';
import { FeedbackComponent } from './admin/feedback/feedback.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomePageComponent,
    FooterComponent,
    RegisterationPageComponent,
    LoginPageComponent,
    AdminDashboardComponent,
    AdminHomeComponent,
    AdminSidebarComponent,
    AddDepartmentComponent,
    ViewDepartmentComponent,
    ViewCitizenComponent,
    ViewComplainComponent,
    PracticeComponent,
    BlockUserComponent,
    FeedbackComponent,
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
    NgxCaptchaModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
