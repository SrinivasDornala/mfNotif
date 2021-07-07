import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';
import {FormControl, FormGroup} from '@angular/forms';
import { FormsModule } from '@angular/forms';
import {UserService} from '../user.service'
import {User} from '../model/User';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User ;
  id : string;
  constructor(private userService: UserService) {
    this.user = new User();
    this.id ='';
   }

  ngOnInit(): void {
  }
  onClickSubmit(data:any) {
    this.userService.getUser(data.id)
      .subscribe((d:User) => { this.user=d;});
  }
}
