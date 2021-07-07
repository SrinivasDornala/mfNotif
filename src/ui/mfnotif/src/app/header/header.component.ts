import { Component, OnInit,Inject, Input } from '@angular/core';
import {User} from '../model/User';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @Input() user: User;
  constructor() {
   this.user= new User();
   }

  ngOnInit(): void {
  }

}
