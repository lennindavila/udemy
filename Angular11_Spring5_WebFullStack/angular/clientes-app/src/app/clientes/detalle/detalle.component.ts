import { Component, OnInit, Input } from '@angular/core';
import {Cliente} from '../cliente';
import {ClienteService} from '../cliente.service';
import {ModalService} from './modal.service'; //se agrega al convertir a modal
/*import {ActivatedRoute} from '@angular/router';*///comentado al convertir en modal
import swal from 'sweetalert2';
import {HttpEventType} from '@angular/common/http';

@Component({
  selector: 'detalle-cliente',
  templateUrl: './detalle.component.html',
  styleUrls: ['./detalle.component.css']
})
export class DetalleComponent implements OnInit {
  @Input() cliente: Cliente;
  titulo: String = "Detalle del Cliente";
  fotoSeleccionada: File;
  progreso: number = 0;
  //modalService:ModalService = new ModalService();

  constructor(private clienteService: ClienteService
              ,public modalService:ModalService
            /*,private activatedRoute: ActivatedRoute*/) { }

  ngOnInit(): void {
    //codigo comentado al pasar a modal y declarar @Input() cliente: Cliente;
    /*
    this.activatedRoute.paramMap.subscribe(params => {
      let id:number =+params.get('id');
      if(id){
        this.clienteService.getCliente(id).subscribe(cliente => {
          this.cliente = cliente;
        });
      }
    });
    */
  }

  seleccionarFoto(event){
    this.fotoSeleccionada = event.target.files[0];
    this.progreso = 0;
    console.log(this.fotoSeleccionada);
    if(this.fotoSeleccionada.type.indexOf('image') < 0){
      swal.fire('Error seleccionar imagen: ','El archivo debe ser del tipo imagen','error');
      this.fotoSeleccionada = null;
    }
  }
 //sin progressbar
  subirFoto_bk(){
    if(!this.fotoSeleccionada){
      swal.fire('Error Upload: ','Debe seleccionar una foto','error');
    }else{
      this.clienteService.subirFoto_bk(this.fotoSeleccionada, this.cliente.id).subscribe(
        cliente => {
          this.cliente = cliente;
          swal.fire('La foto se ha subido completamente!',`La foto se ha subido con exito ${cliente.foto}`,'success');
        }
      )
    }
  }
//con progressbar
  subirFoto(){
    if(!this.fotoSeleccionada){
      swal.fire('Error Upload: ','Debe seleccionar una foto','error');
    }else{
      this.clienteService.subirFoto(this.fotoSeleccionada, this.cliente.id).subscribe(
        event => {
          if(event.type === HttpEventType.UploadProgress){
            this.progreso = Math.round((event.loaded/event.total)*100);
          }else if(event.type == HttpEventType.Response){
            let response: any = event.body;
            this.cliente = response.cliente as Cliente;
            this.modalService.notificarUpload.emit(this.cliente);
            swal.fire('La foto se ha subido completamente!',response.mensaje,'success');
          }
        }
      )
    }
  }

  cerrarModal(){
    this.modalService.cerrarModal();
    this.fotoSeleccionada = null;
    this.progreso = 0;
  }

}
