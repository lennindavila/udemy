import { Injectable } from '@angular/core';
import {formatDate} from '@angular/common';
import {DatePipe} from '@angular/common';
//import {registerLocaleData} from '@angular/common';   // en app module
//import localeES from '@angular/common/locales/es';    // en app module
import {Cliente} from './cliente';
import {Observable,of,throwError} from 'rxjs';
import {HttpClient , /*HttpHeaders,*/ HttpRequest, HttpEvent} from '@angular/common/http';/*HttpHeaders comentado por  que usamos auth.interceptor*/
import {map,catchError,tap} from 'rxjs/operators';
import swal from 'sweetalert2';
import {Router} from '@angular/router';
import {Region} from './region';
//import {AuthService} from'../usuarios/auth.service';/*AuthService comentado por  que usamos auth.interceptor*/
import {URL_BACKEND} from '../config/config';

@Injectable()
export class ClienteService {

  private urlEndPoint:string = URL_BACKEND + '/api/clientes';

  /*lo comentaremos por que usaremos token.interceptor*/
  //private httpHeaders = new HttpHeaders({'content-type':'application/json'})

  //comentamos authService del constructor por que usamos auth.interceptor
  constructor(private http: HttpClient
              ,private router: Router
            /*,private authService: AuthService*/) { }

  /*este metodo ya no se uasara por que se agrego un interceptor*/
  /*private agregarAuthorizationHeader(){
    let token = this.authService.token;
    if(token != null){
      return this.httpHeaders.append('Authorization','Bearer ' + token);
    }
    return this.httpHeaders;
  }*/

 //metodo para verificar la autorizacion
 //lo comentamos por que usaremos interceptor auth.interceptor
  /*private isNoAutorizado(e):boolean{
    if(e.status==401){
      //verificar si expiro el _token
      if(this.authService.isAuthenticated()){
        this.authService.logout();
      }

      this.router.navigate(['/login']);
      return true;
    }
    if(e.status==403){
      swal.fire('Acceso denegado',`Hola ${this.authService.usuario.username} no tienes acceso a este recurso!`,'warning')
      this.router.navigate(['/clientes']);
      return true;
    }
    return false;
  }*/

  getRegiones():Observable<Region[]>{
    //modo simple sin verificar autorizacion
    //return this.http.get<Region[]>(this.urlEndPoint + '/regiones');
    //verificando autorizacion sin interceptor
    //return this.http.get<Region[]>(this.urlEndPoint + '/regiones',{headers:this.agregarAuthorizationHeader()}).pipe(
    //aca usaremos interceptor token.inteceptor, asi que removemos el {headers:this.agregarAuthorizationHeader()}, lo comentaremos por que usaremos auth.interceptor
    /*return this.http.get<Region[]>(this.urlEndPoint + '/regiones').pipe(
      catchError(e => {
        this.isNoAutorizado(e);
        return throwError(e);
      })
    );*/

    return this.http.get<Region[]>(this.urlEndPoint + '/regiones')/*queda simple y limpio por usar interceptores*/
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

  /*
  create_con_any(cliente:Cliente):Observable<any>{
    return this.http.post<any>(this.urlEndPoint,cliente,{headers:this.httpHeaders}).pipe(
      catchError(e=> {
        console.error(e.error.mensaje);
        swal.fire(e.error.mensaje,e.error.error,'error');
        return throwError(e);
      })
    );
  }
  */

  create(cliente:Cliente):Observable<Cliente>{
    //se agrega authorization de token con el metodo this.agregarAuthorizationHeader() sin interceptor
    //return this.http.post(this.urlEndPoint,cliente,{headers:this.agregarAuthorizationHeader()}).pipe(
    //return this.http.post(this.urlEndPoint,cliente,{headers:this.httpHeaders}).pipe( /*version sin token*/
    //aca usaremos interceptor token.inteceptor, asi que removemos el {headers:this.agregarAuthorizationHeader()}
    return this.http.post(this.urlEndPoint,cliente).pipe(
      map((response: any) => response.cliente as Cliente),
      catchError(e=> {
        //comentamos xq usaremos auth.interceptor
        /*if(this.isNoAutorizado(e)){
          return throwError(e);
        }*/

        if(e.status==400){
            return throwError(e);
        }
        if(e.error.mensaje){
          console.error(e.error.mensaje);
        }

        //swal.fire(e.error.mensaje,e.error.error,'error');/*comentado, alert desde auth.interceptor*/
        return throwError(e);
      })
    );
  }

  getCliente(id):Observable<Cliente>{
    //se agrega authorization de token con el metodo this.agregarAuthorizationHeader() sin interceptor
    //return this.http.get<Cliente>(`${this.urlEndPoint}/${id}`,{headers: this.agregarAuthorizationHeader()}).pipe(
    //return this.http.get<Cliente>(`${this.urlEndPoint}/${id}`).pipe( /*version sin token*/
    //aca usaremos interceptor token.inteceptor, asi que removemos el {headers:this.agregarAuthorizationHeader()}
    return this.http.get<Cliente>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {

        //comentamos xq usaremos auth.interceptor
        /*if(this.isNoAutorizado(e)){
          return throwError(e);
        }*/

        if(e.status != 401 && e.error.mensaje){
          this.router.navigate(['/clientes']);
          console.error(e.error.mensaje);
        }
         //swal.fire('Error al editar',e.error.error,'error');/*comentado, alert desde auth.interceptor*/
         return throwError(e);
      })
    );
  }

  update(cliente:Cliente):Observable<any>{
    //se agrega authorization de token con el metodo this.agregarAuthorizationHeader() sin interceptor
    //return this.http.put<any>(`${this.urlEndPoint}/${cliente.id}`,cliente,{headers:this.agregarAuthorizationHeader()}).pipe(
    //return this.http.put<any>(`${this.urlEndPoint}/${cliente.id}`,cliente,{headers:this.httpHeaders}).pipe( /*version sin token*/
    //aca usaremos interceptor token.inteceptor, asi que removemos el {headers:this.agregarAuthorizationHeader()}
    return this.http.put<any>(`${this.urlEndPoint}/${cliente.id}`,cliente).pipe(
      catchError(e=> {

        //comentamos xq usaremos auth.interceptor
        /*if(this.isNoAutorizado(e)){
          return throwError(e);
        }*/

        if(e.status==400){
            return throwError(e);
        }

        if(e.error.mensaje){
          console.error(e.error.mensaje);
        }
        //swal.fire(e.error.mensaje,e.error.error,'error');/*comentado, alert desde auth.interceptor*/
        return throwError(e);
      })
    );
  }

  delete(id:number):Observable<Cliente>{
    //se agrega authorization de token con el metodo this.agregarAuthorizationHeader() sin interceptor
    //return this.http.delete<Cliente>(`${this.urlEndPoint}/${id}`,{headers:this.agregarAuthorizationHeader()}).pipe(
    //return this.http.delete<Cliente>(`${this.urlEndPoint}/${id}`,{headers:this.httpHeaders}).pipe( /*version sin token*/
    //aca usaremos interceptor token.inteceptor, asi que removemos el {headers:this.agregarAuthorizationHeader()}
    return this.http.delete<Cliente>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e=> {

        //comentamos xq usaremos auth.interceptor
        /*if(this.isNoAutorizado(e)){
          return throwError(e);
        }*/

        if(e.error.mensaje){
          console.error(e.error.mensaje);
        }

        //swal.fire(e.error.mensaje,e.error.error,'error');/*comentado, alert desde auth.interceptor*/
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
    /*se implementa auth de token sin interceptor*/
    /*let httpHeaders = new HttpHeaders();
    let token = this.authService.token;
    if(token!=null){
      httpHeaders = httpHeaders.append('Authorization','Bearer ' + token);
    }*/

    const req = new HttpRequest('POST',`${this.urlEndPoint}/upload`,formData,{
      reportProgress:true
      /*,headers: httpHeaders*/ /*se agrego el header con el token*/ /*se comento ya que se implemento interceptor*/
    });

    //modo sin verificar auth
    //return this.http.request(req);

    //verificando auth, lo comentaremos por que usaremos auth.interceptor
    /*return this.http.request(req).pipe(
        catchError(e => {
          this.isNoAutorizado(e);
          return throwError(e);
        })
      );*/

      return this.http.request(req);/*quedo limpio y simple por usar auth.interceptor*/
  }

}
