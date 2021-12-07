import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JsonPipe } from '@angular/common';
// import { BackButtonDisableModule } from 'angular-disable-browser-back-button';
import { BrowserModule } from '@angular/platform-browser';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit {
  constructor(private router:Router) {}
  ngOnInit(): void { 
    window.history.forward();
  }

  location:any
  user:any
  currentUser:any
  loggedIn():any {
    this.user=sessionStorage.getItem("userDetails")
    // this.currentUser=this.user.username
    if(this.user!=null){
      // console.log(this.user)
      return true;
    }
  }



  onLogout() {
    console.log((sessionStorage.getItem("userDetails")))
    sessionStorage.clear();
    console.log(sessionStorage.getItem("userDetails"))
    window.history.forward();
    this.router.navigate(['']);
   localStorage.clear();
    

  }

  

}
