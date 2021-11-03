import { Injectable } from '@angular/core';
import {formatDate} from '@angular/common';
import {DatePipe} from '@angular/common';
//import {registerLocaleData} from '@angular/common';   // en app module
//import localeES from '@angular/common/locales/es';    // en app module
import {Cliente} from './cliente';
import {Observable,of,throwError} from 'rxjs';
import {HttpClient,HttpHeaders, HttpRequest, HttpEvent} from '@angular/common/http';
import {map,catchError,tap} from 'rxjs/operators';
import swal from 'sweetalert2';
import {Router} from '@angular/router';
import {Region} from './region';

@Injectable()
export class ClienteService {

  private urlEndPoint:string = 'http://10.0.0.207:8080/api/clientes';
  private httpHeaders = new HttpHeaders({'content-type':'application/json'})

  constructor(private http: HttpClient, private router: Router) { }

 //metodo para verificar la autorizacion
  private isNoAutorizado(e):boolean{
    if(e.status==401 || e.status==403){
      this.router.navigate(['/login']);
      return true;
    }
    return false;
  }

  getRegiones():Observable<Region[]>{
    //modo simple sin verificar autorizacion
    //return this.http.get<Region[]>(this.urlEndPoint + '/regiones');
    //verificando autorizacion
    return this.http.get<Region[]>(this.urlEndPoint + '/regiones').pipe(
      catchError(e => {
        this.isNoAutorizado(e);
        return throwError(e);
      })
    );
  }

  //getClientes con paginacion
  getClientes(page:number):Observable<any>{
  //Forma 1
    return this.http.get(this.urlEndPoint + '/page/' + page).pipe(
      tap((response: any) => {
        console.log('ClienteService: tap 1');
        (response.content as Cliente[]).forEach(cliente => {
          console.log(cliente.nombre);
        })
      }),
      map( (response:any) => {
        (response.content as Cliente[]).map(cliente => {
          cliente.nombre = cliente.nombre.toUpperCase();
          //Formateando fecha, se puede usar DatePipe o formatDate
          //con formatDate
          //hay diferentes formatos de fecha
          //'dd/MM/yyyy' si se quiere mostrar el nombre de dia se antepone E 3 veces o 4 antes de dd, quedaria 'EEEE dd'
          //para mes seria 3 M o 4, es decir MMM o MMMM con ello mostraria el nombre del mes
          //el locate por default es en-US
          //cliente.createAt = formatDate(cliente.createAt,'EEEE dd, MMMM ,yyyy','en-US');
          //con DatePipe
          //registerLocaleData(localeES,'es') //lo mandamos al appmodule para que sea a nivel global de la app
          //let datePipe = new DatePipe('es');
          //cliente.createAt = datePipe.transform(cliente.createAt,'EEEE dd , MMMM yyyy');
          return cliente;
        });
        return response;
      } ),
      tap(response => {
        console.log('ClienteService: tap 2');
        (response.content as Cliente[]).forEach(cliente => {
          console.log(cliente.nombre);
        })
      })
    );
  //Forma 2
   //return this.http.get<Cliente[]>(this.urlEndPoint);
  }

  //getClientes sin paginacion
  getClientes_bak():Observable<Cliente[]>{
  //Forma 1
    return this.http.get(this.urlEndPoint).pipe(
      tap(response => {
        console.log('ClienteService: tap 1');
        let clientes = response as Cliente[];
        clientes.forEach(cliente => {
          console.log(cliente.nombre);
        })
      }),
      map( response => {
        let clientes = response as Cliente[];
        return clientes.map(cliente => {
          cliente.nombre = cliente.nombre.toUpperCase();
          //Formateando fecha, se puede usar DatePipe o formatDate
          //con formatDate
          //hay diferentes formatos de fecha
          //'dd/MM/yyyy' si se quiere mostrar el nombre de dia se antepone E 3 veces o 4 antes de dd, quedaria 'EEEE dd'
          //para mes seria 3 M o 4, es decir MMM o MMMM con ello mostraria el nombre del mes
          //el locate por default es en-US
          //cliente.createAt = formatDate(cliente.createAt,'EEEE dd, MMMM ,yyyy','en-US');
          //con DatePipe
          //registerLocaleData(localeES,'es') //lo mandamos al appmodule para que sea a nivel global de la app
          //let datePipe = new DatePipe('es');
          //cliente.createAt = datePipe.transform(cliente.createAt,'EEEE dd , MMMM yyyy');
          return cliente;
        })
      } ),
      tap(response => {
        console.log('ClienteService: tap 2');
        response.forEach(cliente => {
          console.log(cliente.nombre);
        })
      })
    );
  //Forma 2
   //return this.http.get<Cliente[]>(this.urlEndPoint);
  }

  create_con_any(cliente:Cliente):Observable<any>{
    return this.http.post<any>(this.urlEndPoint,cliente,{headers:this.httpHeaders}).pipe(
      catchError(e=> {
        console.error(e.error.mensaje);
        swal.fire(e.error.mensaje,e.error.error,'error');
        return throwError(e);
      })
    );
  }

  create(cliente:Cliente):Observable<Cliente>{
    return this.http.post(this.urlEndPoint,cliente,{headers:this.httpHeaders}).pipe(
      map((response: any) => response.cliente as Cliente),
      catchError(e=> {

        if(this.isNoAutorizado(e)){
          return throwError(e);
        }

        if(e.status==400){
            return throwError(e);
        }

        console.error(e.error.mensaje);
        swal.fire(e.error.mensaje,e.error.error,'error');
        return throwError(e);
      })
    );
  }

  getCliente(id):Observable<Cliente>{
    return this.http.get<Cliente>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {

        if(this.isNoAutorizado(e)){
          return throwError(e);
        }

        this.router.navigate(['/clientes']);
        console.error(e.error.mensaje);
         swal.fire('Error al editar',e.error.error,'error');
         return throwError(e);
      })
    );
  }

  update(cliente:Cliente):Observable<any>{
    return this.http.put<any>(`${this.urlEndPoint}/${cliente.id}`,cliente,{headers:this.httpHeaders}).pipe(
      catchError(e=> {

        if(this.isNoAutorizado(e)){
          return throwError(e);
        }

        if(e.status==400){
            return throwError(e);
        }

        console.error(e.error.mensaje);
        swal.fire(e.error.mensaje,e.error.error,'error');
        return throwError(e);
      })
    );
  }

  delete(id:number):Observable<Cliente>{
    return this.http.delete<Cliente>(`${this.urlEndPoint}/${id}`,{headers:this.httpHeaders}).pipe(
      catchError(e=> {

        if(this.isNoAutorizado(e)){
          return throwError(e);
        }

        console.error(e.error.mensaje);
        swal.fire(e.error.mensaje,e.error.error,'error');
        return throwError(e);
      })
    );
  }

  //sin barra de progreso
  subirFoto_bk(archivo: File, id): Observable<Cliente>{
    let formData = new FormData();
    formData.append("archivo",archivo);
    formData.append("id",id);
    return this.http.post(`${this.urlEndPoint}/upload`,formData).pipe(
      map( (response:any) => response.cliente as Cliente),
      catchError(e=> {
        console.error(e.error.mensaje);
        swal.fire(e.error.mensaje,e.error.error,'error');
        return throwError(e);
      })
    )
  }

  //con barra de progreso
  subirFoto(archivo: File, id): Observable<HttpEvent<{}>>{
    let formData = new FormData();
    formData.append("archivo",archivo);
    formData.append("id",id);

    const req = new HttpRequest('POST',`${this.urlEndPoint}/upload`,formData,{reportProgress:true});
    //modo sin verificar auth
    //return this.http.request(req);

    //verificando auth
    return this.http.request(req).pipe(
        catchError(e => {
          this.isNoAutorizado(e);
          return throwError(e);
        })
      );
  }

}
