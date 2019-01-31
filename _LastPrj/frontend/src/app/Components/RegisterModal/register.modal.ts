import { Component } from "@angular/core";
import { MatDialogRef } from '@angular/material';
import { BackendService } from './../../Services/backend.service';

@Component({
    selector: 'dialog-register',
    templateUrl: 'register.modal.html'
})
export class RegisterModal {

    email: string;
    username: string;
    password: string;
    fullname: string;
    address: string;

    constructor(public dialogRef: MatDialogRef<RegisterModal>,
        private backend: BackendService) {}
    
    register() {
        this.backend.register(this.email, this.username, this.password, this.fullname, this.address);
        this.dialogRef.close();
    }

    onNoClick() {
        this.dialogRef.close();
    }
}