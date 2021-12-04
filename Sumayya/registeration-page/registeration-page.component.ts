import { Component, OnInit } from '@angular/core';
import { NgForm, FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';



@Component({
  selector: 'app-registeration-page',
  templateUrl: './registeration-page.component.html',
  styleUrls: ['./registeration-page.component.scss'],
})
export class RegisterationPageComponent implements OnInit {
  
 
  constructor() {}

  getData(data: NgForm) {
    console.log(data);
    this.exform.reset();
  }
  exform: any = FormGroup;
 
  
 
  ngOnInit(): void 
  {
    let emailPattern = '^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$';
    this.exform = new FormGroup({
      namef: new FormControl(null, Validators.required),
      emailf: new FormControl(null, [
        Validators.required,
        Validators.pattern(emailPattern),
      ]),
      passwordf: new FormControl(null, [
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(12),
       
      ]),
      confirmpasswordf: new FormControl(null, [Validators.required]),
    });
  }

  // checkPasswords: this.exform = (
//   group: AbstractControl
// ): ValidationErrors | null => {
//   let pass = this.exform.value.passwordf;
//   let confirmPass = this.exform.confirmpasswordf;
//   return pass === confirmPass ? null : { notSame: true };
// };

//       {
//         validators: matchUp('passwordf', 'confirmpasswordf')
//       }
//     );
//   }

//   matchUp(pass: string, cpass: string) {
//     return (form :FormGroup) => {
//       const control = form.controls[pass];
//       const matchControl = form.controls[cpass];
//       if(control.errors && matchControl.errors.matchUp{
//         return
//       }
// if(control.value !== matchControl.value){
//   matchControl.setErrors(this.matchUp:true)
// }
// else{
//   matchControl.setErrors(null)
// }
//     }


 
  
  


  
  
}
