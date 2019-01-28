import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { LayoutModule } from '@angular/cdk/layout';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { MainNavComponent } from './main-nav/main-nav.component';
import { DataTableComponent } from './data-table/data-table.component';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { ErrorComponent } from './error/error.component';

import { AuthInterceptor } from './auth/auth-interceptor';
import { ErrorInterceptor } from './error-interceptor';

import { AngularMaterialModule } from './angular-material.module';
import { PostsModule } from './posts/posts.module';


@NgModule({
  declarations: [
    AppComponent
    , MainNavComponent
    , DataTableComponent
    , LoginComponent
    , SignupComponent
    , ErrorComponent
  ],
  imports: [
    BrowserModule
    , AppRoutingModule
    , FormsModule
    , BrowserAnimationsModule
    , LayoutModule
    , HttpClientModule
    , AngularMaterialModule
    , PostsModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
    , { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
  ],
  bootstrap: [AppComponent],
  entryComponents: [ErrorComponent]
})
export class AppModule { }
