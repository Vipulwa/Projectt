import { Component, OnInit } from '@angular/core';
import {
  NgForm,
  FormGroup,
  FormControl,
  Validators,
  RequiredValidator,
} from '@angular/forms';
import { UserService } from '../utility/user/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
})
export class LoginPageComponent implements OnInit {
  constructor(private userService:UserService,private router:Router) {}

  email:string=''
  password:string=''
  user:any={}
  siteKey:string="6Lf6iHYdAAAAAN-IDGFeC0SlqrWOJzcF9908tpxS";
  getData(data: NgForm) {
    console.log(data);
    this.userService.login(data).subscribe(data=>{
      console.log(data.result.body);
      if(data.result.body.role=="ADMIN"){
        
        this.router.navigate(['admin-dash']);
      }
      else if(data.result.body.role=="DEPARTMENT")
      {
        this.router.navigate(['']);
      }
      else{
        this.router.navigate(['']);
      }
    })
    

    this.exform.reset();
  }
  exform: any = FormGroup;
  ngOnInit(): void {
    this.exform = new FormGroup({
      email: new FormControl(null, [Validators.required]),
      password: new FormControl(null, [Validators.required]),
      recaptcha: new FormControl(null,[Validators.required])
    });
  }
}
