import { Component, OnInit } from '@angular/core';
import {
  NgForm,
  FormGroup,
  FormControl,
  Validators,
  RequiredValidator,
} from '@angular/forms';
import { Route } from '@angular/compiler/src/core';
import { UserService } from 'src/app/utility/user/user.service';

@Component({
  selector: 'app-add-feedback',
  templateUrl: './add-feedback.component.html',
  styleUrls: ['./add-feedback.component.scss']
})
export class AddFeedbackComponent implements OnInit {

  constructor(private userService:UserService) { }

  feedbackObject:any={}
  feedback:string=''
  suggestion:string=''

  exform: any = FormGroup;
  ngOnInit(): void {
    this.user1=sessionStorage.getItem('userDetails');
    this.user=JSON.parse(this.user1);
    let emailPattern = '^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$';
    this.exform = new FormGroup({
      
      feedback: new FormControl(null, Validators.required),
      suggestion: new FormControl(null, Validators.required),
    });
  }
  user:any;
  user1:any;

  
  addFeedback(data: NgForm){

    this.feedbackObject=data;
    console.log(this.feedbackObject.feedback)
    let userObject :any = {};
    userObject['username']=this.user.username;
    userObject['feedback'] = this.feedbackObject.feedback;
    userObject['suggestion'] = this.feedbackObject.suggestion;

    this.userService.addFeedback(userObject).subscribe(data=>{
      window.location.reload();
      alert("Added Successfully")
    })

  }

}
