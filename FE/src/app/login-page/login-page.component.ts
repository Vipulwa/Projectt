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
import { ToastrService } from 'ngx-toastr';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
})
export class LoginPageComponent implements OnInit {
  constructor(private userService:UserService,private router:Router,private toastr:ToastrService) {}

  email:string=''
  password:string=''
  user1:any={}
  user:any={}
  rememberme:boolean=false
  siteKey:string="6Lf6iHYdAAAAAN-IDGFeC0SlqrWOJzcF9908tpxS";
  getData(data: NgForm) {
    
    
    this.userService.login(data).subscribe(data=>{
      
      if(this.rememberme){
                localStorage['email']=data.result.body.email
                localStorage['password']=data.result.body.password
              }

      if(data.result.body.role=="ADMIN"){
        sessionStorage.setItem("userDetails",JSON.stringify(data.result.body));
        this.user1=sessionStorage.getItem('userDetails')
        this.router.navigate(['admin-dash/admin-home']);
        this.toastr.success("Login Successfully",'',{
          positionClass:'toast-top-center'
        })
        this.user1=sessionStorage.getItem('userDetails')
        // console.log(JSON.stringify(this.user.username))
        
      }
      else if(data.result.body.role=="DEPARTMENT")
      {

        sessionStorage.setItem("userDetails",JSON.stringify(data.result.body));
        this.user1=sessionStorage.getItem('userDetails')
        this.router.navigate(['admin-dash/admin-home']);
        this.toastr.success("Login Successfully",'',{
          positionClass:'toast-top-center'
        })
        this.router.navigate(['department-dashboard/department-home']);
      }
      else if(data.result.body.role=="CITIZEN"){
        sessionStorage.setItem("userDetails",JSON.stringify(data.result.body));
        this.user1=sessionStorage.getItem('userDetails')
        this.router.navigate(['admin-dash/admin-home']);
        this.toastr.success("Login Successfully",'',{
          positionClass:'toast-top-center'
        })
        this.router.navigate(['citizen-dash/citizen-home']);
      }
      else{
       
        this.toastr.error(data.result.body,'',{
          positionClass:'toast-top-center'
        })

        this.router.navigate(['/login'])
        window.setTimeout(function(){location.reload()},3000)
        // location.reload();
        
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
  rememberMe(event:any){
        if(event.target.checked){
          this.rememberme=true;
        }
        else{
          this.rememberme=false;
        }
        
      }
}



