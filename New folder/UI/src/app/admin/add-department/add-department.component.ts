import { Component, OnInit } from '@angular/core';
import {
  NgForm,
  FormGroup,
  FormControl,
  Validators,
  RequiredValidator,
} from '@angular/forms';
import { AdminService } from '../../utility/admin/admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-department',
  templateUrl: './add-department.component.html',
  styleUrls: ['./add-department.component.scss'],
})
export class AddDepartmentComponent implements OnInit {
  
  email:string=''
  password:string=''
  username:string=''

  constructor(private adminService:AdminService) {}



  departmentDetails:any={}
  // addDept(data: NgForm) {
  //   console.log(data);
    
  // }

  addDept(data: NgForm) {
    this.departmentDetails=data;
    console.log(this.departmentDetails)
    this.adminService.addDepartment(this.departmentDetails).subscribe(data=>{
      console.log(data.body);
      // this.router.navigate(['admin-dash']);
      this.exform.reset();
    });
  }


  exform: any = FormGroup;
  ngOnInit(): void {
    let emailPattern = '^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$';
    this.exform = new FormGroup({
      email: new FormControl(null, [
        Validators.required,
        Validators.pattern(emailPattern),
      ]),
      password: new FormControl(null, [
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(12),
      ]),
      username: new FormControl(null, Validators.required),
    });
  }
}
