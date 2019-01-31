import { Component, OnInit } from "@angular/core";
import { MatDialog } from '@angular/material';

import { LoginModal } from './../LoginModal/login.modal';
import { RegisterModal } from './../RegisterModal/register.modal';

import { BackendService } from './../../Services/backend.service';

@Component({
    templateUrl: 'home.component.html',
    styleUrls: ['home.component.css'],
    selector: 'home-component'
}) export class HomeComponent {

    constructor(private backend: BackendService,
        private dialog: MatDialog) {}

    connect() {
        this.dialog.open(LoginModal);
    }

    register() {
        this.dialog.open(RegisterModal);
    }
}