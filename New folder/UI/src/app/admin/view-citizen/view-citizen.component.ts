import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/utility/admin/admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-citizen',
  templateUrl: './view-citizen.component.html',
  styleUrls: ['./view-citizen.component.scss']
})
export class ViewCitizenComponent implements OnInit {

  constructor(private adminService:AdminService,
    private router:Router
) { }

citizenList:any={}

  ngOnInit(): void { 
    this.adminService.getCitizenList().subscribe(data=>{
      console.log(data.body)
      this.citizenList=data.body;
    })
    
    }

}
