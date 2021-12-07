import { Component, OnInit,ViewChild } from '@angular/core';
import { CitizenService, Comments, Dislike, Like } from 'src/app/utility/citizen/citizen.service';
import { NgForm,  FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ConditionalExpr } from '@angular/compiler';

@Component({
  selector: 'app-view-complain-status',
  templateUrl: './view-complain-status.component.html',
  styleUrls: ['./view-complain-status.component.scss']
})
export class ViewComplainStatusComponent implements OnInit {
 
  
  
  complaintList:any;
  dataSource:any;
  displayedColumns: string[] = ['id', 'subject', 'status', 'createDate','closeDate','userId','details','comments','ratings'];
  // userId=sessionStorage.getItem("userId");
  displayComments:string[]=['id','comment','username']
  userId:number;
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
  user1:any;
  user:any;
  liked:boolean;
  disliked:boolean;
  likeCount:number;
  dislikeCount:number;
  userComment:string='';
  starRating:number=0;
    constructor(private citizenService:CitizenService,
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
      console.log(this.user.id)
      console.log(typeof(this.userId))
      this.getAllComplaints(this.userId);
     
    }

    getAllComplaints(id:number): void {
        console.log(id)
          this.citizenService.getAll(id).subscribe(data => {
            this.dataSource= data.result
            this.complaintList = new MatTableDataSource<any>(this.dataSource);
            this.complaintList.paginator = this.paginator;
              },
              error => {
                console.log(error);
              });
        }
    
    setModal(complaint:any){
      this.modalComplaint=complaint.complain;
      this.modalId=complaint.id;
      this.modalSubject=complaint.subject;
      this.modalStatus=complaint.status;
      this.modalUserName=complaint.username;
      this.getComments(this.modalId);
      this.getRatings(this.modalId);
      this.getLiked(this.modalId);
      this.getDisLiked(this.modalId);
      this.getLikeCount(this.modalId);
      this.getDisLikeCount(this.modalId);
      this.getComments(this.modalId);
    }
    
    getLiked(complainId:number){
      this.citizenService.getLiked(complainId,this.user.id).subscribe(data=>{
        this.liked=data.result;
        console.log(data.result)
        }, error => {
              console.log(error);
              this.errorMsg= error  
      });
    }
    getDisLiked(complainId:number){
      this.citizenService.getDisLiked(complainId,this.user.id).subscribe(data=>{
        this.disliked=data.result;
        console.log(data.result)
        }, error => {
              console.log(error);
              this.errorMsg= error  
      });
    }
    getComments(complaintId:number){
      this.citizenService.getComments(complaintId).subscribe(data=>{
        this.comments=data.result;
        console.log(data.result)
        }, error => {
              console.log(error);
              this.errorMsg= error  
      });
    }

    getRatings(complaintId:number){
      this.citizenService.getRatings(complaintId).subscribe(data=>{
        this.ratings=data.result;
        console.log(data.result)
        }, error => {
              console.log(error);
              this.errorMsg= error  
      });
    }

    like(complaintId:number){
      this.citizenService.like(this.user.id,complaintId,"").subscribe(data=>{
        this.toastr.success(data.result,'',{
          positionClass:'toast-top-center'
        })
        console.log(data.result)
        }, error => {
              console.log(error);
              this.errorMsg= error  
      });
    }

    dislike(complaintId:number){
      this.citizenService.dislike(this.user.id,complaintId,).subscribe(data=>{
        this.toastr.success(data.result,'',{
          positionClass:'toast-top-center'
        })
        console.log(data.result)
        }, error => {
              console.log(error);
              this.errorMsg= error  
      });
    }
    getLikeCount(complaintId:number){
      this.citizenService.getLikes(complaintId).subscribe(data=>{
        this.likeCount=data.result;
        console.log(data.result)
        }, error => {
              console.log(error);
              this.errorMsg= error  
      });
    }

    getDisLikeCount(complaintId:number){
      this.citizenService.getDislikes(complaintId).subscribe(data=>{
        this.dislikeCount=data.result;
        console.log(data.result)
        }, error => {
              console.log(error);
              this.errorMsg= error  
      });
    }
    makeComment(complaintId:number): void {
      console.log(typeof(complaintId))
      console.log(typeof(this.user.id))

      if(this.userComment===undefined || this.userComment===''){
        this.toastr.warning("cannot be empty",'',{
          positionClass:'toast-top-center'
        })
      }else{
        this.citizenService.comment(this.user.id,complaintId,this.userComment).subscribe(data=>{
          this.getComments(complaintId);
          this.userComment='';
          }, error => {
                console.log(error);
                this.errorMsg= error  
        });
      }
    }

      giveRating(complaintId:number): void {
 
          this.citizenService.giveRating(this.user.id,complaintId,this.starRating).subscribe(data=>{
            this.getRatings(complaintId);
            }, error => {
                  console.log(error);
                  this.errorMsg= error  
          });
        }
      
  
  }





