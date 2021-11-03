import { Component, OnInit } from '@angular/core';
import { FacturaService } from './services/factura.service';
import { ActivatedRoute } from '@angular/router';
import { Factura } from './models/factura';

@Component({
  selector: 'app-detalle-factura',
  templateUrl: './detalle-factura.component.html'
})
export class DetalleFacturaComponent implements OnInit {

  factura: Factura;
  titulo: string = 'Factura';

  constructor(private facturaService: FacturaService
    , private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      let id = +params.get('id'); //con el simbolo + se convierte en number
      this.facturaService.getFactura(id).subscribe(factura => this.factura = factura);
    })
  }
}
