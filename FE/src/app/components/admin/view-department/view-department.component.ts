import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/utility/admin/admin.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-view-department',
  templateUrl: './view-department.component.html',
  styleUrls: ['./view-department.component.scss']
})
export class ViewDepartmentComponent implements OnInit {

  constructor(private adminService:AdminService,
    private router:Router,
    private toastr:ToastrService
) { }

departmentList:any={}
displayedColumns: string[] = ['id', 'username','email','remove'];
ngOnInit(): void { 
this.adminService.getDepartmentList().subscribe(data=>{
console.log(data.body);
this.departmentList=data.body;

});

}

removeDepartment(username:any){
  this.adminService.removeDepartment(username).subscribe(data=>{
    
    location.reload();
    // this.toastr.success("Department Remove Successfully",'',{
    //   positionClass:'toast-top-center'
    // })
    
    this.router.navigate(['/admin-dash/view-dept']);
    

  })
}




}
