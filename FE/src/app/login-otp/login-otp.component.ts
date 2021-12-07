import { Component, OnInit } from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { IUser } from 'src/app/utility/IUser';
import { CitizenService } from 'src/app/utility/citizen/citizen.service';
// import { NotificationService } from 'src/app/utility/notification.service';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-login-otp',
  templateUrl: './login-otp.component.html',
  styleUrls: ['./login-otp.component.scss']
})
export class LoginOtpComponent implements OnInit {

  user!: IUser;
  user1:any
  checkUser!: IUser;

  showOtpArea :string = "";

  userNotFound : string = "";

  inactiveAccount : string = "";

  enterdOtp!: string;

  invalidOtp : string = "";

  showLoginForm : string = "show now";

  constructor(private citizenService:CitizenService , private router : Router ,
    //  private _notificationService :  NotificationService
    private toastr:ToastrService
     ) { }

  ngOnInit(): void {
    //this.user = JSON.parse(sessionStorage['user']);
    //console.log(this.user);
   // this._notificationService.sendNotification(this.user);
  }

  loginForm= new FormGroup({
    email : new FormControl('',[Validators.required])
  });

  otpForm= new FormGroup({
    otp : new FormControl('',[Validators.required])
  });

  

  login(loginForm : FormGroup)
  {
    this.checkUser = this.loginForm.value;
    console.log(this.checkUser);

      this.citizenService.loginWithOtp(this.checkUser.email).subscribe(
        data => {
          console.log(data);
          this.user = data.result;
          if(this.user == null)
          {
              this.userNotFound = "Invalid Username";
          }
          else
          {
              this.userNotFound = "";
              if(this.user.status === "LOCKED")
              {
                 this.inactiveAccount = "Your account is not activated yet , Login with Otp is not yet available for you";
              }
              else
              {
                  this.showLoginForm = "";
                  this.showOtpArea = "show Now";
                  this.citizenService.otpForLoginWithOtp(this.user).subscribe(
                    data => {
                      console.log(data);
                      this.user = data.result;
                    }
                  )
              }
          }
        }
      )
  }

  otp(otpForm : FormGroup)
  { 
    this.enterdOtp  = this.otpForm.controls['otp'].value;

    if(this.enterdOtp == this.user.otp)
    {
      if( this.user.role=="ADMIN"){
        sessionStorage.setItem("userDetails",JSON.stringify( this.user));
        this.router.navigate(['admin-dash/admin-home']);
        this.toastr.success("Login Successfully",'',{
          positionClass:'toast-top-center'
        })
        this.user1=sessionStorage.getItem('userDetails')
        console.log('...')
        console.log(sessionStorage.getItem('userDetails'))
        console.log("...")
        console.log(this.user1)
        // console.log(JSON.stringify(this.user.username))
        
      }
      else if(this.user.role=="DEPARTMENT")
      {
        sessionStorage.setItem("userDetails",JSON.stringify(this.user.role));
        this.router.navigate(['department-dashboard/department-home']);
      }
      else if(this.user.role=="CITIZEN"){
        sessionStorage.setItem("userDetails",JSON.stringify(this.user.role));
        this.router.navigate(['citizen-dash/citizen-home']);
      }
      else{
        this.router.navigate(['/login'])
        location.reload();
        
      }
   
      // sessionStorage['user' ] = JSON.stringify( this.user );
    }
    else
    {
      this.invalidOtp = "Invalid OTP";
    }

  }


}
