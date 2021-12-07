
import { IUser } from '../IUser';
import { IComplaint } from '../IComplaint';
import { HttpClient,HttpHeaders,HttpParams} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IResponse } from '../IResponse';


export class Complaint {
  constructor(
   
    public departmentName: string,
    public  complain: string,
    public  subject: string,
 
   
    public userId:number
  ){
     
      
  }
 
}
export class Comments {
  constructor(
   
      
    public status: string,
    public message: string,
    public result: any[]
  ){
     
      
  }
 
}
export class Comment {
  constructor(
   
      
    public comment: string,
 
  ){
     
      
  }
 
}
export class Like {
  constructor(
   
    public status: string,
    public message: string,
    public result: number
  ){
     
      
  }
 
}
export class Dislike {
  constructor(
   
    public status: string,
    public message: string,
    public result: number
  ){
     
      
  }
 
}
export class User {
  constructor(
   
  
    public  email: string,
    public  password: string,
 
    
  ){
     
      
  }
 
}
export class Citizen {
  constructor(
   
  
    public  email: string,
    public  password: string,
    public username:string,
 
    
  ){
     
      
  }
 
}
export class Department {
  constructor(
   
  
    public  departmentName: string,
   
  ){
     
      
  }
 
}
let status1 = {status: ""};



@Injectable({
  providedIn: 'root'
})

export class CitizenService {
private baseURL="http://localhost:8081/citizen";
  constructor(private httpClient:HttpClient) { 

  }
   
  public registerComplain(complain: any) {
    return this.httpClient.post<Complaint>(this.baseURL+"/addComplaint", complain);
  }
  public addUser(user: any) {
    return this.httpClient.post<Citizen>(this.baseURL+"/addUser", user);
  }


  public sendReminder(complainId: number,user:any):Observable<any> {
    return this.httpClient.post(this.baseURL+"/sendReminder/"+complainId,user);
  }

 
   public findAll(): Observable<any>  {
   return this.httpClient.get(this.baseURL+"/getAllDepartments")
   }
  
   getAll(userId:number): Observable<IResponse> {
    return this.httpClient.get<IResponse>(this.baseURL+"/complaints/"+userId+"?status=ALL");
  }
  
  getActiveComplaints(userId:number): Observable<any> {
    return this.httpClient.get<any>(this.baseURL+"/complaints/"+userId+"?status=ACTIVE");
  }
  
  public changePassword(user: any,newPassword:string) {
    return this.httpClient.post<User>(this.baseURL+"/changePassword?newPassword="+newPassword, user);
  }

  getAllComplaints(): Observable<IResponse> {
    return this.httpClient.get<IResponse>(this.baseURL+"/getAllComplaints");
  }
  
  public like(userId: any,complaintId:any,user:any) {
    return this.httpClient.post<IComplaint>(this.baseURL+"/like",{}, {
      params:{
        userId:userId,
        complaintId:complaintId,
        reaction:'LIKE'
      }
    })
  }
  
  public dislike(userId: any,complaintId:any) {
    return this.httpClient.post<IComplaint>(this.baseURL+"/like",{}, {
      params:{
        userId:userId,
        complaintId:complaintId,
        reaction:'DISLIKE'
      }
    })
  }


  public getLikes(complaintId:number):Observable<IResponse> {
    return this.httpClient.get<IComplaint>(this.baseURL+"/likes",{
      params: {
        complaintId: complaintId,
      }
    })
  }


    public getDislikes(complaintId:any): Observable<IResponse>  {
      return this.httpClient.get<IResponse>(this.baseURL+"/dislikes",{
        params: {
          complaintId: complaintId,
        }
      })
      }

      
    public comment(userId: number,complaintId:number,comment:string): Observable<IResponse>  {
        return this.httpClient.post<IResponse>(this.baseURL+"/comment",{}, {
          params:{
            userId:userId,
            complaintId:complaintId,
            comment:comment
          }
        })
      }

      getComments(complaintId:number):Observable<IComplaint>{
        return this.httpClient.get<IComplaint>("http://localhost:8081/citizen/comments", {
          params: {
            complaintId: complaintId
          }})
      }
  
      public giveRating(userId: number,complaintId:number,rating:number): Observable<IResponse>  {
        return this.httpClient.post<IResponse>(this.baseURL+"/rating",{}, {
          params:{
            userId:userId,
            complaintId:complaintId,
            rating:rating
          }
        })
      }
      
      getRatings(complaintId:number):Observable<IComplaint>{
        return this.httpClient.get<IComplaint>("http://localhost:8081/citizen/ratings", {
          params: {
            complaintId: complaintId
          }})
      }
        loginWithOtp(email : string):Observable<IComplaint>{
          return this.httpClient.get<IComplaint>(this.baseURL+"/loginWithOtp",{
            params: {
              email: email,
            }
          })
        }
      
        otpForLoginWithOtp(user : IUser):Observable<IComplaint>{
          const httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json'}) };
          return this.httpClient.put<IComplaint>(this.baseURL + '/otpForLoginWithOtp/',
          user, httpOptions);
        }

        getLiked(complaintId:number,userId:number):Observable<IComplaint>{
          return this.httpClient.get<IComplaint>("http://localhost:8081/citizen/liked", {
            params: {
              complaintId: complaintId,
              userId:userId
            }})
        }

        getDisLiked(complaintId:number,userId:number):Observable<IComplaint>{
          return this.httpClient.get<IComplaint>("http://localhost:8081/citizen/disliked", {
            params: {
              complaintId: complaintId,
              userId:userId
            }})
        }
  
}


