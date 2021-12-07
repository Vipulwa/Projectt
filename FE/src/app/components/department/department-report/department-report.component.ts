import { Component, OnInit,AfterViewInit,ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {DepartmentService} from 'src/app/utility/department.service';
import {FormGroup, FormControl} from '@angular/forms';
import { IComplaint } from 'src/app/utility/IComplaint';

@Component({
  selector: 'app-department-report',
  templateUrl: './department-report.component.html',
  styleUrls: ['./department-report.component.scss']
})
export class DepartmentReportComponent implements OnInit  {


  constructor(private departmentService:DepartmentService) {}

displayedColumns: string[] = ['id', 'subject', 'status', 'createDate','closeDate','userId'];
statusList=['ALL','ACTIVE','CLOSED','REMINDER']
complaintList:any;
dataSource:any;
errorMsg:string=''
selectedStatus:string='';
modalId:number=0;
modalComplaint:string="";
modalSubject:string="";
modalStatus:string="";
// userId=sessionStorage.getItem("userId");
user:any;
user1:any;
userId:number;

@ViewChild(MatPaginator) paginator: MatPaginator 


  
  ngOnInit(): void {

  this.user1=sessionStorage.getItem('userDetails');
         this.user=JSON.parse(this.user1);
         this.userId=this.user.id;
  }
  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl()
  });

  report(){
    const from=JSON.stringify(this.range.value.start).substring(1, 25);
    const to=JSON.stringify(this.range.value.end).substring(1, 25);
    this.departmentService.report(this.userId,this.selectedStatus,from,to).subscribe( data=> {
      console.log(data)
      this.dataSource= data.result
     this.complaintList = new MatTableDataSource<any>(this.dataSource);
     this.complaintList.paginator = this.paginator;
  }, error => {
        console.log(error);
        this.errorMsg= error  
  });
  }
  setModal(complaint:any){
    this.modalComplaint=complaint.complain;
    this.modalId=complaint.id;
    this.modalSubject=complaint.subject;
    this.modalStatus=complaint.status;
  }
  reset(){
    this.complaintList=null
  }
}
