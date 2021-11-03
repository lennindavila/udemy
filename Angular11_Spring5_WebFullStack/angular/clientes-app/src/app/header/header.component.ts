
import{Component} from '@angular/core';
import {AuthService} from '../usuarios/auth.service';

@Component({
  selector:'app-header',
  templateUrl:'./header.component.html'
})
export class HeaderComponent{
  title:string = 'Curso Spring con Angular';
  constructor(public authService: AuthService){

  }
}
