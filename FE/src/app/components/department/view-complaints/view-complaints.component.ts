import { Component, OnInit,ViewChild } from '@angular/core';
import { IComplaint } from 'src/app/utility/IComplaint';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import {FormGroup, FormControl,Validators} from '@angular/forms';
import {DepartmentService} from '../../../utility/department.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-complaints',
  templateUrl: './view-complaints.component.html',
  styleUrls: ['./view-complaints.component.scss']
})
export class ViewComplaintsComponent implements OnInit {

  complaintList:any;
  dataSource:any;
  displayedColumns: string[] = ['id', 'subject', 'status', 'createDate','closeDate','userId','details','comments','ratings'];
  // userId=sessionStorage.getItem("userId");
  displayComments:string[]=['id','comment','username']

  status:string=""
  departmentNames:any
  departmentTransfer:string=""
  errorMsg:any;
  modalId:number=0;
  modalComplaint:string="";
  modalSubject:string="";
  modalStatus:string="";
  modalUserName:string;
  animalControl = new FormControl('', Validators.required);
  comments:any;
  ratings:any;
  user:any;
  user1:any;
  userId:number;
    constructor(private departmentService:DepartmentService,
      private _activeRoute: ActivatedRoute,private _router:Router,
      private toastr:ToastrService) { 

      
    }
    @ViewChild(MatPaginator) paginator: MatPaginator ;
    range = new FormGroup({
      start: new FormControl(),
      end: new FormControl()
    });
    
    ngOnInit(): void {
      
       this.user1=sessionStorage.getItem('userDetails');
               this.user=JSON.parse(this.user1);
               this.userId=this.user.id;
      this.getStatus();
      this.getComplaints();
      this.getDepartmentNames();

    }
    getComplaints(){
      this.departmentService.getComplaint(this.userId,this.status).subscribe( data=> {
          console.log(data)
          this.dataSource= data.result
          this.complaintList = new MatTableDataSource<any>(this.dataSource);
          this.complaintList.paginator = this.paginator;
      }, error => {
            console.log(error);
            this.errorMsg= error  
      });
    }
    getDepartmentNames(){
      this.departmentService.getDepartmentList(this.userId).subscribe(data=>{
        console.log(data)
        this.departmentNames= data.result;
    }, error => {
          console.log(error);
          this.errorMsg= error  
    });
    }
    getStatus(): void {
      this.status = String(this._activeRoute.snapshot.paramMap.get('status'));   
    }
    resolve(complaintId:number){
      this.departmentService.resolveComplaint(complaintId,this.userId).subscribe(data=>{
        this._router.navigateByUrl('/department-home');
        }, error => {
              console.log(error);
              this.errorMsg= error  
      });
    }

    transfer(complaintId:number){
  if(this.departmentTransfer===''){
    this.toastr.warning("please select department",'',{
      positionClass:'toast-top-center'
    })
  }else{
    this.departmentService.transferComplaint(complaintId,this.userId,this.departmentTransfer).subscribe(data=>{
      console.log(complaintId)
      this.toastr.success(data.result,'',{
        positionClass:'toast-top-center'
      })
      this._router.navigateByUrl('/department-home');
      }, error => {
            console.log(error);
            this.errorMsg= error  
    });
  }
      
    }
  
    setModal(complaint:any){
      this.modalComplaint=complaint.complain;
      this.modalId=complaint.id;
      this.modalSubject=complaint.subject;
      this.modalStatus=complaint.status;
      this.modalUserName=complaint.username;
      this.getComments(this.modalId);
      this.getRatings(this.modalId);
    }
    
    getComments(complaintId:number){
      this.departmentService.getComments(complaintId).subscribe(data=>{
        this.comments=data.result;
        console.log(data.result)
        }, error => {
              console.log(error);
              this.errorMsg= error  
      });
    }

    getRatings(complaintId:number){
      this.departmentService.getRatings(complaintId).subscribe(data=>{
        this.ratings=data.result;
        console.log(data.result)
        }, error => {
              console.log(error);
              this.errorMsg= error  
      });
    }
    
}
