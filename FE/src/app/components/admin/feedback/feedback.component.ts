import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/utility/admin/admin.service';
import { UserService } from 'src/app/utility/user/user.service';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.scss']
})
export class FeedbackComponent implements OnInit {

  constructor(private userService:UserService) { }
  feedbackList:any=[]


  ngOnInit(): void {

    this.userService.getFeedback().subscribe(data=>{
      console.log(data.body)
      this.feedbackList=data.body;
      
    })
  }

}
