import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { CitizenService } from 'src/app/utility/citizen/citizen.service';

@Component({
  selector: 'app-send-reminder',
  templateUrl: './send-reminder.component.html',
  styleUrls: ['./send-reminder.component.scss']
})
export class SendReminderComponent implements OnInit {

  user:any;
  user1:any;
  constructor(private citizenService1:CitizenService) { }

  ngOnInit(): void {
    this.user1=sessionStorage.getItem('userDetails');
    this.user=JSON.parse(this.user1);
    this.getActiveComplaints();
    
  }
  
complaints:any;
  getActiveComplaints(): void {
   
    this.citizenService1.getActiveComplaints(this.user.id)
      .subscribe(
        data => {
          this.complaints = data.result;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  
 sendReminder(id:number,date:Date): void {
  console.log(id);
 let datet=new Date();
 const date1 = formatDate(datet, 'yyyy-MM-dd', 'en-US');
 const date2 = formatDate(date, 'yyyy-MM-dd', 'en-US');


  if(date1 == date2)
  {
    alert("You can send the reminder only on next day");
  }
 else{
  this.citizenService1.sendReminder(id,"")
  .subscribe( data => {
    
    alert("Reminder sent");
  });
  };
 }
    
}
