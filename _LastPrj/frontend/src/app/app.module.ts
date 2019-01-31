import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MatDialogModule } from '@angular/material/dialog';

import { AppComponent } from './Components/AppComponent/app.component';
import { HomeComponent } from './Components/HomeComponent/home.component';
import { ProductsComponent } from './Components/ProductsComponent/products.component';
import { BasketComponent } from './Components/BasketComponent/basket.component';

import { BackendService } from './Services/backend.service';
import { ProductModal } from './Components/ProductModal/product.modal';
import { LoginModal } from './Components/LoginModal/login.modal';
import { RegisterModal } from './Components/RegisterModal/register.modal';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProductsComponent,
    BasketComponent,
    LoginModal,
    RegisterModal,
    ProductModal
  ],
  imports: [
    BrowserModule,
    FormsModule,
    BrowserAnimationsModule,
    NgbModule,
    MatDialogModule,
    HttpClientModule
  ],
  entryComponents: [
    ProductModal,
    RegisterModal,
    LoginModal,
  ],
  providers: [BackendService],
  bootstrap: [AppComponent]
})
export class AppModule { }
