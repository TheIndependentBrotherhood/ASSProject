import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
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
} from '@angular/material';
import { LayoutModule } from '@angular/cdk/layout';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { PostCreateComponent } from './posts/post-create/post-create.component';
import { PostListComponent } from './posts/post-list/post-list.component';
import { MainNavComponent } from './main-nav/main-nav.component';
import { DataTableComponent } from './data-table/data-table.component';


@NgModule({
  declarations: [
    AppComponent
    , PostCreateComponent
    , PostListComponent
    , MainNavComponent
    , DataTableComponent
  ],
  imports: [
    BrowserModule
    , AppRoutingModule
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
    , HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
