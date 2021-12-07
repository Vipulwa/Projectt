import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/utility/admin/admin.service';
import { Router } from '@angular/router';
import { MatTableDataSource} from '@angular/material/table'

@Component({
  selector: 'app-view-citizen',
  templateUrl: './view-citizen.component.html',
  styleUrls: ['./view-citizen.component.scss']
})
export class ViewCitizenComponent implements OnInit {

  constructor(private adminService:AdminService,
    private router:Router
) { }
dataSource:any;
citizenList:any={}
displayedColumns: string[] = ['id', 'username','email'];

  ngOnInit(): void { 
    this.adminService.getCitizenList().subscribe(data=>{
      console.log(data.body)
      this.dataSource=data.body
      
      this.citizenList=data.body;
    })
    
    }

}
