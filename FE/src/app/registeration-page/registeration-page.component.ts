import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators ,} from '@angular/forms';
import { Citizen, CitizenService } from '../utility/citizen/citizen.service';

@Component({
  selector: 'app-registeration-page',
  templateUrl: './registeration-page.component.html',
  styleUrls: ['./registeration-page.component.scss'],
})
export class RegisterationPageComponent implements OnInit {
  username:string=''
  email:string=''
  password:string=''
  confirmpasswordf=''
  constructor(private citizenService1:CitizenService) {}
 

  
 
userDetails:any={}

  addUser(data2: NgForm) {
  this.userDetails=data2;
    this.citizenService1.addUser(this.userDetails).subscribe( data => {
      
      this.exform.reset();
          alert("User registered successfully.");
        });
  
  };

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
        Validators.minLength(5),
        Validators.maxLength(12),
      ]),
      username: new FormControl(null, Validators.required),
      confirmpasswordf: new FormControl(null, [Validators.required]),
    });
  }

}
