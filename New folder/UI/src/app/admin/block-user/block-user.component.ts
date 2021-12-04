import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/utility/admin/admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-block-user',
  templateUrl: './block-user.component.html',
  styleUrls: ['./block-user.component.scss']
})
export class BlockUserComponent implements OnInit {

  constructor(private adminService:AdminService,
    private router:Router
) { }

blockList:any={}

  ngOnInit(): void {
    this.adminService.getBlockList().subscribe(data=>{
      this.blockList=data.body;
    })
  }


  unblockUser(id:any){
    console.log(id);
    this.adminService.unblockUser(id).subscribe(data=>{
      location.reload();
    })
  }

}
