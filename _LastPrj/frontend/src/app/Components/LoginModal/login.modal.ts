import { Component } from "@angular/core";
import { MatDialogRef } from '@angular/material';
import { BackendService } from './../../Services/backend.service';

@Component({
    selector: 'dialog-login',
    templateUrl: 'login.modal.html'
})
export class LoginModal {

    email: string;
    password: string;

    constructor(public dialogRef: MatDialogRef<LoginModal>,
        private backend: BackendService) {}
    
    connect() {
        this.backend.connect(this.email, this.password);
        this.dialogRef.close();
    }

    onNoClick() {
        this.dialogRef.close();
    }
}