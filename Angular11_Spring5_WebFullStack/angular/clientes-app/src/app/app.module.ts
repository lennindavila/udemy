import { BrowserModule } from '@angular/platform-browser';
import { NgModule,LOCALE_ID } from '@angular/core';

import { AppComponent } from './app.component';
import {HeaderComponent}from './header/header.component';
import {FooterComponent}from './footer/footer.component';
import { DirectivaComponent } from './directiva/directiva.component';
import { ClientesComponent } from './clientes/clientes.component';
import {ClienteService}from './clientes/cliente.service';
import {RouterModule,Routes} from '@angular/router';
import {HttpClientModule} from '@angular/common/http';
import { FormComponent } from './clientes/form.component';
import {FormsModule} from '@angular/forms';
import {registerLocaleData} from '@angular/common';
import localeES from '@angular/common/locales/es';
import { PaginatorComponent } from './paginator/paginator.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

//npm install --save @angular/material
//import { MatDatepickerModule } from '@angular/material/datepicker';
//import { MatNativeDateModule } from '@angular/material/core';


//npm audit fix --force
//npm install --legacy-peer-deps @angular/material-moment-adapter
//npm install --save moment
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatMomentDateModule } from '@angular/material-moment-adapter';
import { DetalleComponent } from './clientes/detalle/detalle.component';
import { LoginComponent } from './usuarios/login.component';

registerLocaleData(localeES,'es')

const routes:Routes=[
  {path: '', redirectTo: '/clientes', pathMatch:'full'}
  ,{path: 'directivas', component: DirectivaComponent}
  ,{path: 'clientes', component: ClientesComponent}
  ,{path: 'clientes/page/:page', component: ClientesComponent}
  ,{path: 'clientes/form',component:FormComponent}
  ,{path: 'clientes/form/:id',component:FormComponent}
  ,{path: 'login',component:LoginComponent}
  //,{path: 'clientes/ver/:id',component:DetalleComponent}/*se comenta por que se hara modal*/
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    DirectivaComponent,
    ClientesComponent,
    FormComponent,
    PaginatorComponent,
    DetalleComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes),
    BrowserAnimationsModule,
    MatDatepickerModule,
    //MatNativeDateModule,
    MatMomentDateModule
  ],
  providers: [ClienteService, {provide: LOCALE_ID,useValue:'es'}],
  bootstrap: [AppComponent]
})
export class AppModule { }
