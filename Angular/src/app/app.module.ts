import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {
  MatInputModule
  , MatCardModule
  , MatButtonModule
  , MatToolbarModule
  , MatExpansionModule
  , MatSidenavModule
  , MatIconModule
  , MatListModule
  , MatTableModule
  , MatPaginatorModule
  , MatSortModule
  , MatProgressSpinnerModule
} from '@angular/material';
import { LayoutModule } from '@angular/cdk/layout';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { PostCreateComponent } from './posts/post-create/post-create.component';
import { PostListComponent } from './posts/post-list/post-list.component';
import { MainNavComponent } from './main-nav/main-nav.component';
import { DataTableComponent } from './data-table/data-table.component';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';


@NgModule({
  declarations: [
    AppComponent
    , PostCreateComponent
    , PostListComponent
    , MainNavComponent
    , DataTableComponent
    , LoginComponent
    , SignupComponent
  ],
  imports: [
    BrowserModule
    , AppRoutingModule
    , ReactiveFormsModule
    , FormsModule
    , BrowserAnimationsModule
    , MatInputModule
    , MatCardModule
    , MatButtonModule
    , MatToolbarModule
    , MatExpansionModule
    , LayoutModule
    , MatSidenavModule
    , MatIconModule
    , MatListModule
    , MatTableModule
    , MatPaginatorModule
    , MatSortModule
    , MatProgressSpinnerModule
    , HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
