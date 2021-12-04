import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { IResponse } from '../IResponse';

@Injectable({
  providedIn: 'root'
})

export class AdminService {

  constructor(private httpClient:HttpClient) { }
  url:string="http://localhost:8081/admin";

  addDepartment(departmentDetails:any):Observable<any>{
    console.log("INSIDE")
    console.log(departmentDetails)
    return this.httpClient.post<any>(this.url+"/add", departmentDetails)

  }

  getDepartmentList():Observable<any>{
    return this.httpClient.get<any>(this.url+"/departmentList")
  }

  removeDepartment(departmentName:any):Observable<IResponse>{
    return this.httpClient.delete<IResponse>(this.url+"/delete/"+departmentName)
 }

 getCitizenList(){
  return this.httpClient.get<any>(this.url+"/citizenList")
}

getBlockList(){
  return this.httpClient.get<any>(this.url+"/getBlockList")
}

unblockUser(id:any):Observable<IResponse>{
  return this.httpClient.get<IResponse>(this.url+"/unBlock/"+id);
}


}
