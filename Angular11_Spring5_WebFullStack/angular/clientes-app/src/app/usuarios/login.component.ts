import { Component, OnInit } from '@angular/core';
import { Usuario } from "./usuario";
import swal from 'sweetalert2';
import {AuthService} from './auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  titulo: String = 'Por favor Sign In!';
  usuario: Usuario;

  constructor(private authService: AuthService, private router: Router) {
    this.usuario = new Usuario();
  }

  ngOnInit(): void {
    console.log("ngOnInit()");
    if(this.authService.isAuthenticated()){
      swal.fire('Login',`Hola ${this.authService.usuario.username} ya estas autenticado!`,'info');
      this.router.navigate(['/clientes']);
    }
    console.log("ngOnInit() else");
  }

  login(): void {
    console.log(this.usuario);
    if (this.usuario.username == null || this.usuario.password == null) {
      swal.fire('Error Login', 'Username o password vacias!', 'error');
      return;
    }

    this.authService.login(this.usuario).subscribe(response => {
      console.log(response);
      //let payload = JSON.parse(atob(response.access_token.split(".")[1]));
      //console.log(payload);

      //guardar en sessionStorage
      this.authService.guardarUsuario(response.access_token);
      this.authService.guardarToken(response.access_token);
      let usuario = this.authService.usuario;

      this.router.navigate(['/clientes']);
      swal.fire('Login',`Hola ${usuario.username}, has iniciado sesion con exito`,'success');
    },err =>{
      if(err.status == 400){
        swal.fire('Error Login','Usuario o clave incorrectas!','error');
      }
    }
  );
  }

}
