import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { DirectivaComponent } from './directiva/directiva.component';
import { ClientesComponent } from './clientes/clientes.component';
import { ClienteService } from './clientes/cliente.service';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormComponent } from './clientes/form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { registerLocaleData } from '@angular/common';
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
import { AuthGuard } from './usuarios/guards/auth.guard';
import { RoleGuard } from './usuarios/guards/role.guard';
import { TokenInterceptor } from './usuarios/interceptors/token.interceptor';
import { AuthInterceptor } from './usuarios/interceptors/auth.interceptor';
import { DetalleFacturaComponent } from './facturas/detalle-factura.component';
import { FacturasComponent } from './facturas/facturas.component';

/*autocomplete: inicio, Agregados al usar el autocomplete en facturas.component.html*/
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
/*autocomplete: fin*/


registerLocaleData(localeES, 'es')

const routes: Routes = [
  { path: '', redirectTo: '/clientes', pathMatch: 'full' }
  , { path: 'directivas', component: DirectivaComponent }
  , { path: 'clientes', component: ClientesComponent }
  , { path: 'clientes/page/:page', component: ClientesComponent }
  //,{path: 'clientes/form',component:FormComponent}
  , { path: 'clientes/form', component: FormComponent, canActivate: [AuthGuard, RoleGuard], data: { role: 'ROLE_ADMIN' } }/*seguridad con Guard*/
  , { path: 'clientes/form/:id', component: FormComponent }
  , { path: 'clientes/form/:id', component: FormComponent, canActivate: [AuthGuard, RoleGuard], data: { role: 'ROLE_ADMIN' } }/*seguridad con Guard*/
  , { path: 'login', component: LoginComponent }
  , { path: 'facturas/:id', component: DetalleFacturaComponent, canActivate: [AuthGuard, RoleGuard], data: { role: 'ROLE_USER' } }
  , { path: 'facturas/form/:clienteId', component: FacturasComponent, canActivate: [AuthGuard, RoleGuard], data: { role: 'ROLE_ADMIN' } }
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
    LoginComponent,
    DetalleFacturaComponent,
    FacturasComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes),
    BrowserAnimationsModule,
    MatDatepickerModule,//agreagdo para el datetime picker de angular material
    //MatNativeDateModule,
    MatMomentDateModule,
    ReactiveFormsModule,//agreagdo para el autocomplete de angular material
    MatAutocompleteModule,//agreagdo para el autocomplete de angular material
    MatInputModule,//agreagdo para el autocomplete de angular material
    MatFormFieldModule//agreagdo para el autocomplete de angular material
  ],
  providers: [
    ClienteService
    , { provide: LOCALE_ID, useValue: 'es' }
    , { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true }
    , { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
