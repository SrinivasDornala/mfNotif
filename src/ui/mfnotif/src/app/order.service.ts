import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import {Order} from './model/order';
@Injectable({
  providedIn: 'root'
})
export class OrderService {

 constructor(private http: HttpClient) { }
  getOrders(id: number ): Observable<Order[]> {
      return this.http.get<Order[]>("http://localhost:8084/orders/"+parseInt(''+id));
    }
    getAll(): Observable<Order[]> {
        return this.http.get<Order[]>("http://localhost:8084/orders/");
    }
    update(id: string, order :any): Observable<any> {
          return this.http.post("http://localhost:8084/order/"+id, order);
     }
    enableNotification(id: number, method: string): Observable<any>{
        console.log(id + ' ' + method);
        const headers = { 'content-type': 'application/json'}
        return this.http.post<any>("http://localhost:8084/notice/"+id+'/'+method,'',{'headers':headers});
    }
    selectRow(order : Order): Observable<any[]>{
      console.log(order);
      return this.http.get<any[]>("http://localhost:8084/notify/"+order.id);
    }
}
