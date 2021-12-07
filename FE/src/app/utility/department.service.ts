import { Injectable } from '@angular/core';
import {HttpClient,HttpParams} from '@angular/common/http';
import {IComplaint} from './IComplaint'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  baseUrl:string="http://localhost:8081/department"
  constructor(private htttpClient:HttpClient) { }

  getComplaint(userId:number,status:string):Observable<IComplaint>{
    return this.htttpClient.get<IComplaint>(this.baseUrl+"/complaints", {
      params: {
        userId: userId,
        status: status
      }})
  }

  resolveComplaint(complaintId:number,userId:number):Observable<IComplaint>{
    return this.htttpClient.put<IComplaint>(this.baseUrl+"/resolve",{}, {
      params: {
        complaintId: complaintId,
        userId: userId
      }})
  }

  getDepartmentList(userId:number):Observable<IComplaint>{
    
    return this.htttpClient.get<IComplaint>(this.baseUrl+"/departmentNames", {
      params: {
        userId: userId
      }})
  }

  transferComplaint(complaintId:number,userId:number,departmentName:string):Observable<IComplaint>{
    
    const params = new HttpParams()
   .set('complaintId', complaintId)
   .set('userId', userId)
   .set('departmentName',departmentName);
    return this.htttpClient.put<IComplaint>(this.baseUrl+"/transfer",{}, {params})
  }

  report(userId:number,status:string,from:string,to:string):Observable<IComplaint>{
    return this.htttpClient.get<IComplaint>(this.baseUrl+"/complaintsDates",{
      params: {
        userId: userId,
        status:status,
        from:from,
        to:to
      }
    })
  }
  getReport(userId:number):Observable<IComplaint>{
    return this.htttpClient.get<IComplaint>(this.baseUrl+"/report", {
      params: {
        userId: userId
      }})
  }

  getComments(complaintId:number):Observable<IComplaint>{
    return this.htttpClient.get<IComplaint>("http://localhost:8081/citizen/comments", {
      params: {
        complaintId: complaintId
      }})
  }

  getRatings(complaintId:number):Observable<IComplaint>{
    return this.htttpClient.get<IComplaint>("http://localhost:8081/citizen/ratings", {
      params: {
        complaintId: complaintId
      }})
  }

  getMonthlyReport(userId:number):Observable<IComplaint>{
    return this.htttpClient.get<IComplaint>(this.baseUrl+"/monthlyReport", {
      params: {
        userId: userId
      }})
  }
  
}
