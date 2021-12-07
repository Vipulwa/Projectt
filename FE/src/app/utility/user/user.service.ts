import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IUser } from '../IUser';
import { IResponse } from '../IResponse';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient:HttpClient) { }

  url:string="http://localhost:8081/user"
  login(data:any):Observable<IResponse>{
    console.log(data.email)
    console.log(data.password)
    
    let userObject :any = {};
    userObject['email'] = data.email;
    userObject['password'] = data.password;
    let response : any = {};


     return this.httpClient.post<any>(this.url+"/login", userObject)
    
}

getFeedback():Observable<any>{
  return this.httpClient.get<any>(this.url+"/feedback/get");
}
addFeedback(feedback:any):Observable<any>{
  return this.httpClient.post(this.url+"/feedback/add",feedback);
}

}
