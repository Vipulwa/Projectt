import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-practice',
  templateUrl: './practice.component.html',
  styleUrls: ['./practice.component.scss'],
})
export class PracticeComponent implements OnInit {
  constructor() {}

  getCookie(data: NgForm) {
    console.log(data);
    let myData = data;
    console.log(myData);
    localStorage.setItem('dataSource', JSON.stringify(myData));
    console.log(localStorage.getItem('dataSource'));
  }
  ngOnInit(): void {}
}
