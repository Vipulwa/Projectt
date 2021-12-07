import { Component, OnInit,AfterViewInit,ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {AdminService} from 'src/app/utility//admin/admin.service';
import {FormGroup, FormControl} from '@angular/forms';
import { IComplaint } from 'src/app/utility/IComplaint';
@Component({
  selector: 'app-admin-report',
  templateUrl: './admin-report.component.html',
  styleUrls: ['./admin-report.component.scss']
})
export class AdminReportComponent implements OnInit {

  constructor(private adminService:AdminService) { }

  displayedColumns: string[] = ['id', 'subject', 'status', 'createDate','closeDate','userId'];
statusList=['ALL','ACTIVE','CLOSED','REMINDER']
complaintList:any;
dataSource:any;
errorMsg:string=''
selectedStatus:string='';
selectedDepartment:string;
modalId:number=0;
modalComplaint:string="";
modalSubject:string="";
modalStatus:string="";
// departmentId=sessionStorage.getItem("departmentId");
departmentList:any;
@ViewChild(MatPaginator) paginator: MatPaginator 

  ngAfterViewInit() {
    
  }
  
  ngOnInit(): void {
      this.adminService.getDepartmentnameList().subscribe(data=>{
        console.log(data)
        this.departmentList= data.result;
      }, error => {
            console.log(error);
            this.errorMsg= error  
      });
  }
  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl()
  });

  report(){
    const from=JSON.stringify(this.range.value.start).substring(1, 25);
    const to=JSON.stringify(this.range.value.end).substring(1, 25);
    this.adminService.report(this.selectedDepartment,this.selectedStatus,from,to).subscribe( data=> {
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
