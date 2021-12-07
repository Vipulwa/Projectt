import { Component, OnInit } from '@angular/core';
import { NgForm,  FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Complaint, CitizenService } from 'src/app/utility/citizen/citizen.service';


@Component({
  selector: 'app-make-complain',
  templateUrl: './make-complain.component.html',
  styleUrls: ['./make-complain.component.scss']
})
export class MakeComplainComponent implements OnInit {

  user:any;
  user1:any;
  newComplaint:any={};
  departmentName:any;
complain: Complaint = new Complaint("","","",1);
 departments :any;

complainDetails:any={}
  constructor(private citizenService:CitizenService) { }

 

  

  getAll(): void {
    this.citizenService.findAll()
      .subscribe(
        data => {
          console.log(data);
          this.departments = data;
         
        },
        error => {
          console.log(error);
        });
  }


  registerComplain(data:NgForm): void {
   
    console.log(this.complain)
    this.newComplaint=data;
    console.log(this.newComplaint.feedback)
    let complainObject :any = {};
    complainObject['departmentName']=this.departmentName;
    complainObject['complain'] = this.newComplaint.complain;
    complainObject['subject'] = this.newComplaint.subject;
    complainObject['userId'] = this.user.id;
    console.log(complainObject)
    this.citizenService.registerComplain(complainObject)
        .subscribe( data => {
          this.exform.reset();
          alert("Complaint registered successfully.");
        });

  };
  exform: any = FormGroup;
  ngOnInit(): void {
    this.getAll();
    this.user1=sessionStorage.getItem('userDetails');
    this.user=JSON.parse(this.user1);
    this.exform = new FormGroup({
      departmentName: new FormControl(null, Validators.required), 
      complain: new FormControl(null, Validators.required),  
      subject: new FormControl (null, Validators.required),
   
     
    });
  }

}
