import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Factura } from '../models/factura';
import { Producto } from '../models/producto';
import {URL_BACKEND} from '../../config/config';

@Injectable({
  providedIn: 'root'
})
export class FacturaService {

  private urlEndPoint: string = URL_BACKEND + '/api/facturas';

  constructor(private httpClient: HttpClient) { }

  getFactura(id: number): Observable<Factura> {
    return this.httpClient.get<Factura>(`${this.urlEndPoint}/${id}`);
  }

  delete(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.urlEndPoint}/${id}`);
  }

  filtrarProductos(term: string): Observable<Producto[]> {
    return this.httpClient.get<Producto[]>(`${this.urlEndPoint}/filtrar-productos/${term}`)
  }

  create(factura: Factura): Observable<Factura> {
    return this.httpClient.post<Factura>(this.urlEndPoint, factura);
  }
}
