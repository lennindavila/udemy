import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ClienteService } from '../clientes/cliente.service';
import { Factura } from './models/factura';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, flatMap } from 'rxjs/operators';
import { FacturaService } from './services/factura.service';
import { Producto } from './models/producto';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { ItemFactura } from './models/item-factura';
import swal from 'sweetalert2';

@Component({
  selector: 'app-facturas',
  templateUrl: './facturas.component.html'
})
export class FacturasComponent implements OnInit {

  titulo: string = "Nueva Factura";
  factura: Factura = new Factura();
  autocompleteControl = new FormControl();
  //productos: string[] = ['Mesa', 'Tablet', 'Sony', 'Samsung', 'Tv LG', 'Bicicleta'];
  productosFiltrados: Observable<Producto[]>;

  constructor(private clienteService: ClienteService
    , private facturaService: FacturaService
    , private router: Router
    , private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      let clienteId = +params.get('clienteId');
      this.clienteService.getCliente(clienteId).subscribe(cliente => this.factura.cliente = cliente);
    });

    this.productosFiltrados = this.autocompleteControl.valueChanges
      .pipe(
        map(value => typeof value === 'string' ? value : value.nombre),
        flatMap(value => value ? this._filter(value) : [])
      );
  }

  private _filter(value: string): Observable<Producto[]> {
    const filterValue = value.toLowerCase();
    return this.facturaService.filtrarProductos(filterValue);
  }
  //(producto?: Producto, el simbolo ? indica que es parametro opcional
  mostrarNombre(producto?: Producto): string | undefined {
    return producto ? producto.nombre : undefined;
  }

  seleccionarProducto(event: MatAutocompleteSelectedEvent) {
    let producto = event.option.value as Producto;
    console.log(producto);

    if (this.existeItem(producto.id)) {
      this.incrementaCantidad(producto.id);
    } else {
      let nuevoItem = new ItemFactura();
      nuevoItem.producto = producto;
      this.factura.items.push(nuevoItem);
    }

    this.autocompleteControl.setValue('');//limpiamos el autocomplete
    event.option.focus();
    event.option.deselect();
  }

  actualizarCantidad(id: number, event: any): void {
    let cantidad: number = event.target.value as number;

    if (cantidad == 0) {
      return this.eliminarItemFactura(id);
    }

    this.factura.items = this.factura.items.map((item: ItemFactura) => {
      if (id === item.producto.id) {
        item.cantidad = cantidad;
      }
      return item;
    });
  }

  existeItem(id: number): boolean {
    let existe = false;
    this.factura.items.forEach((item: ItemFactura) => {
      if (id === item.producto.id) {
        existe = true;
      }
    });
    return existe;
  }

  incrementaCantidad(id: number): void {
    this.factura.items = this.factura.items.map((item: ItemFactura) => {
      if (id === item.producto.id) {
        item.cantidad = item.cantidad + 1; // otra manera es ++item.cantidad
      }
      return item;
    });
  }

  eliminarItemFactura(id: number): void {
    this.factura.items = this.factura.items.filter((item: ItemFactura) => {
      return id !== item.producto.id
    });
    // aqui es otra manera, este no usa llaves en el => {} por lo tanto no necesita el "return" dentro de las llaves como el caso de lineas arriba
    /*
    this.factura.items = this.factura.items.filter((item: ItemFactura) =>
      id !== item.producto.id
    );
    */
  }

  /*Para validacion Modo 1*/
  create_(): void {
    console.log(this.factura);
    this.facturaService.create(this.factura).subscribe(factura => {
      swal.fire(this.titulo, `Factura ${factura.descripcion} creada con exito`, "success");
      this.router.navigate(['/facturas',factura.id]);
    });
  }

  /*Para validacion Modo 2*/
  create(facturaForm): void {
    console.log(this.factura);
    if (this.factura.items.length == 0) {
      this.autocompleteControl.setErrors({ 'invalid': true });
    }

    if (facturaForm.form.valid && this.factura.items.length > 0) {
      this.facturaService.create(this.factura).subscribe(factura => {
        swal.fire(this.titulo, `Factura ${factura.descripcion} creada con exito`, "success");
        this.router.navigate(['/facturas',factura.id]);
      });
    }
  }
}
