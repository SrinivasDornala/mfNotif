import { Component, OnInit,Inject, Input } from '@angular/core';
import {OrderService} from '../order.service'
import {Order} from '../model/order';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  @Input() id: number;
  enablesms: boolean[] =[];
  enableemail: boolean[] =[];
  enablewhatsapp: boolean[] =[];
  selectorRow :any[];

  headers = ["Order Id", "Order Message", "Notify", "SMS Notifications", "Email Notifications", "WhatsApp Notifications"];
  head = ["Id", "Method", "Order status", "Subject", "Text", "To", "Type"];
  rows: Order[] = [];
  constructor(private order: OrderService) {
      this.id=-1;
      this.selectorRow= [];
  }

  ngOnInit(): void {
      this.order.getOrders(this.id)
        .subscribe(data => {
          data.forEach(d => {
            this.rows.push(d);
            if(d.notify =='Y'){
               this.enablesms[d.id]= true;
               this.enableemail[d.id]=true;
               this.enablewhatsapp[d.id]=true;
            }
          });
        } );
  }

  enableSmsNotification(id :number)  {
    this.order.enableNotification(id, 'sms')
      .subscribe(notification => {
        this.rows.forEach(order => {
          if(order.id == id){
           // order.method = order.method! + '| sms';
           this.enablesms[id]= true;
           order.notify = 'Y';
          }
        })
      });
  }
  enableEmailNotification(id :number)  {
      this.order.enableNotification(id, 'email')
        .subscribe(notification => {
          this.rows.forEach(order => {
            if(order.id == id){
              //order.method = order.method + '| email';
              this.enableemail[id]=true;
              order.notify = 'Y';
            }
          })
        });
    }
    enableWhatsAppNotification(id :number)  {
      this.order.enableNotification(id, 'whatsApp')
        .subscribe(notification => {
          this.rows.forEach(order => {
            if(order.id == id){
             // order.method = order.method + '| whatsApp';
             this.enablewhatsapp[id]=true;
             order.notify = 'Y';
            }
          })
        });
    }
    selectRow(order : Order){
        this.order.selectRow(order).subscribe(data => {
          this.selectorRow=data;
           console.log(this.selectorRow);
            console.log(data);
        });

    }
}
