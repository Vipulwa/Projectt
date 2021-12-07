import { Component, OnInit } from '@angular/core';
import {Chart,registerables } from 'chart.js';
import {DepartmentService} from 'src/app/utility/department.service'
import { IReport } from 'src/app/utility/IReport';
@Component({
  selector: 'app-department-home',
  templateUrl: './department-home.component.html',
  styleUrls: ['./department-home.component.scss']
})
export class DepartmentHomeComponent implements OnInit {

  constructor(private departmentService:DepartmentService) {
    Chart.register(...registerables);
    
   }
   user:any;
   user1:any;
userId:number;
// userId=sessionStorage.getItem("userId");
closed:number;
active:number;
reminder:number;
roundLabels=['CLOSED','ACTIVE'];
chart:any=[];
barChart:any=[];
months:Array<any>;
  ngOnInit(): void {
        this.user1=sessionStorage.getItem('userDetails');
        this.user=JSON.parse(this.user1);
        this.userId=this.user.id;
    this.departmentService.getReport(this.userId).subscribe(data=>{
      console.log(data)
      this.closed=data.result.closed;
      this.active=data.result.active;
      this.reminder=data.result.reminder;
      // doughnut chart
      this.chart=new Chart('canvas',{
        type: 'doughnut',
        data: {
          labels: this.roundLabels,
          datasets: [{
            label: 'My First Dataset',
            data: [this.closed,this.active],
            backgroundColor: [
              'rgb(106, 27, 154)',
              'rgb(11, 230, 193)'
            ],
            hoverOffset: 4
          }]
        }
      })

    // line chart




  }, error => {
        console.log(error);
  });

  this.departmentService.getMonthlyReport(this.userId).subscribe(data=>{
        console.log(data.result)
        var values = Object.values(data.result);
        const colors = [
          "rgb(133, 188, 129)",
          "rgb(150, 121, 219)",
          'rgb(237, 151, 253)'
        ]
        var monthData=[];
       var complaintData=[];
        
        for (var i = 0; i < values.length; i++) {
            let obj=JSON.stringify(values[i]) 
            let vx=JSON.parse(obj)
            let num=vx.month
            let complaint=vx.complaints
            complaintData.push(complaint)
            if(num===1)
            monthData.push("January")
            if(num===2)
            monthData.push("February")
            if(num===3)
            monthData.push("March")
            if(num===4)
            monthData.push("April")
            if(num===5)
            monthData.push("May")
            if(num===6)
            monthData.push("June")
            if(num===7)
            monthData.push("July")
            if(num===8)
            monthData.push("August")
            if(num===9)
            monthData.push("Septmber")
            if(num===10)
            monthData.push("October")
            if(num===11)
            monthData.push("November")
            if(num===12)
            monthData.push("December")
          }

          const chartData = {
            labels: monthData,
            datasets: [{
              label: 'My First Dataset',
              data: complaintData,
              backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(255, 159, 64, 0.2)',
                'rgba(255, 205, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(153, 102, 255, 0.5)',
                'rgba(201, 203, 207, 0.5)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(140, 203, 207, 0.2)',
                'rgba(99, 102, 255, 0.2)',
                'rgba(212, 203, 207, 1)',
                'rgba(40, 203, 207, 0.2)',
              ],
              borderColor: [
                'rgb(255, 99, 132)',
                'rgb(255, 159, 64)',
                'rgb(255, 205, 86)',
                'rgb(75, 192, 192)',
                'rgb(54, 162, 235)',
                'rgb(153, 102, 255)',
                'rgb(201, 203, 207)'
              ],
              borderWidth: 1
            }]
          }
    //       var monthWise=[];
    //       for (var i = 0; i < values.length; i++) {
    //       var departmentObject = this.perpareMonthWiseDetails(
    //         monthData[i],
    //         colors[i % colors.length],
    //         complaintData[i]
    //       )
    //       monthWise.push(departmentObject);
    //     }
      

    //     var chartData={
    //       lables:monthData,
    //       datasets:monthWise
    //       }
    //   console.log(chartData);
  
      this.barChart=new Chart('canvas1',{
        type: 'bar',
        data: chartData,
        options: {
  
            scales: {
              y: {
                beginAtZero: true
              }
            }
          }
      });
      }, error => {
        console.log(error);
    });
  }
  // perpareMonthWiseDetails(arg0: string, arg1: string, arg2: unknown) {
  //   return{
  //     label: arg0,
  //     backgroundColor: arg1,
  //     data: arg2,
      
  //   };
  // }
  

}
