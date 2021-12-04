import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/utility/admin/admin.service';

@Component({
  selector: 'app-view-department',
  templateUrl: './view-department.component.html',
  styleUrls: ['./view-department.component.scss']
})
export class ViewDepartmentComponent implements OnInit {

  constructor(private adminService:AdminService,
    private router:Router
) { }

departmentList:any={}

ngOnInit(): void { 
this.adminService.getDepartmentList().subscribe(data=>{
console.log(data.body);
this.departmentList=data.body;
});

}

removeDepartment(username:any){
  this.adminService.removeDepartment(username).subscribe(data=>{
    location.reload();
    this.router.navigate(['/admin-dash/view-dept']);

  })
}




}
