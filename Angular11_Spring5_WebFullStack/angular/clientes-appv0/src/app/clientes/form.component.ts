import { Component, OnInit } from '@angular/core';
import { Cliente } from './cliente'
import { ClienteService } from './cliente.service'
import { Router, ActivatedRoute } from '@angular/router'
import swal from 'sweetalert2';
import { Region } from './region';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {
  public cliente: Cliente = new Cliente()
  regiones: Region[];
  titulo: string = "Crear Cliente"

  errores: string[]

  constructor(private clienteService: ClienteService,
    private router: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarCliente()
    this.clienteService.getRegiones().subscribe(regiones => this.regiones = regiones);
  }

  cargarCliente(): void {
    this.activatedRoute.params.subscribe(params => {
      //this.activatedRoute.paramMap.subscribe(params=>{
      let id = params['id']
      if (id) {
        this.clienteService.getCliente(id).subscribe((cliente) => this.cliente = cliente)
      }
    });
  }

/*
  create_con_any(): void {
    this.clienteService.create_con_any(this.cliente).subscribe(
      json => {
        this.router.navigate(['/clientes'])
        swal.fire('Nuevo cliente', `${json.mensaje}: ${json.cliente.nombre}`, 'success')
      }
    );
  }
  */

  create(): void {
    console.log(this.cliente);
    this.clienteService.create(this.cliente).subscribe(
      cliente => {
        this.router.navigate(['/clientes'])
        swal.fire('Nuevo cliente', `El cliente ${cliente.nombre} ha sido creado con exito`, 'success')
      },
      err => {
        this.errores = err.error.errors as string[];
        console.error('Codigo de error desde el backend: ' + err.status);
        console.error(err.error.errors);
      }
    );
  }

  update(): void {
    console.log(this.cliente);
    this.cliente.facturas = null; // soluciona problema de recursion infinita desde frontend, aunque ya se soluciono en backend
    this.clienteService.update(this.cliente).subscribe(
      json => {
        this.router.navigate(['/clientes'])
        swal.fire('Cliente Actualizado', `${json.mensaje}: ${json.cliente.nombre}`, 'success')
      },
      err => {
        this.errores = err.error.errors as string[];
        console.error('Codigo de error desde el backend: ' + err.status);
        console.error(err.error.errors);
      }
    );
  }

  compararRegion(o1: Region, o2: Region): boolean {
    if (o1 === undefined && o2 === undefined) {
      return true;
    }

    //La primera opcion da error, la 2da y 3ra funcionan del mismo modo
    //return o1 === null || o2===null?false:o1.id===o2.id; //esta es muy estricta
    return o1 === null || o2 === null || o1 === undefined || o2 === undefined ? false : o1.id === o2.id; //estricta pero valida mas casos (undefined)
    //return o1 == null || o2 == null ? false: o1.id === o2.id; //esta es menos estricta
  }

}
