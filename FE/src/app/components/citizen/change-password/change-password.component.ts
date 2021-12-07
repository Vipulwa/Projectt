import { Component, OnInit } from '@angular/core';
import {
  NgForm,
  FormGroup,
  FormControl,
  Validators,
  RequiredValidator,
} from '@angular/forms';
import { CitizenService, User } from 'src/app/utility/citizen/citizen.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {

  constructor(private citizenService1:CitizenService) { }
  email:string='';
  password:string='';
 
newPassword:string='';
exform: any = FormGroup;
ngOnInit(): void {
  let emailPattern = '^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$';
  this.exform = new FormGroup({
    email: new FormControl(null, [
      Validators.required,
      Validators.pattern(emailPattern),
    ]),
    password: new FormControl(null, [
      Validators.required,
     
    ]),
    newPassword: new FormControl(null, Validators.required),
  });
}
  departmentDetails:any={}
//user: User = new User("","");
  changePassword( data: NgForm,newPassword:any): void {
    
    this.departmentDetails=data;
    console.log(this.departmentDetails.password)
    console.log(newPassword);
    this.citizenService1.changePassword(this.departmentDetails,newPassword)
        .subscribe( data => {
         
          alert("password changed  successfully.");
        });

  };

}
