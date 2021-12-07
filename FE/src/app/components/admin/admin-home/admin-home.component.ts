import { Component, OnInit } from '@angular/core';
import {Chart,registerables } from 'chart.js';
import { AdminService } from 'src/app/utility/admin/admin.service';
import { IReport } from 'src/app/utility/IReport';
@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.scss']
})
export class AdminHomeComponent implements OnInit {

  constructor(private adminService:AdminService) { 
    Chart.register(...registerables);
  }
  closed:number;
  active:number;
  reminder:number;
  roundLabels=['CLOSED','ACTIVE'];
  chart:any=[];
map=new Map();
newMap=new Map();

departmentNames:string[];
dataset=[];
  ngOnInit(): void {

  this.adminService.reportByDepartmentName().subscribe(data=>{
      console.log(data)
      this.map=data.result;
      var keyNames = Object.keys(data.result);
      console.log("key"+keyNames)
      
      var values=Object.values(data.result)
      var datas=Object.values(values);
      console.log(datas)
      const colors = [
        "rgb(133, 188, 129)",
        "rgb(150, 121, 219)",
        'rgb(237, 151, 253)'
      ]
      var departmentData=[];
      for (var i = 0; i < values.length; i++) {
      var departmentObject = this.prepareDepartmentDetails(
          keyNames[i],
          colors[i % colors.length],
          datas[i]
      )
     
      departmentData.push(departmentObject);
      }

      var chartData={
        lables:keyNames,
        datasets:departmentData
        }
    console.log(chartData);
    this.chart=new Chart('canvas',{
      type: 'bar',
      data: chartData,
      options: {

          scales: {
            y: {
              beginAtZero: false
            }
          }
        }
    });

      }, error => {
          console.log(error);
      });
  }
  prepareDepartmentDetails(arg0: string, arg1: string, arg2: unknown) {
    return{
      label: arg0,
      data: arg2,
      backgroundColor: arg1,
    };
  }

  
    
}


