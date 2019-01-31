import { Component, Inject } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Product } from 'src/app/Shared/Product';

import { BackendService } from './../../Services/backend.service';

@Component({
    selector: 'dialog-product-details',
    templateUrl: 'product.modal.html'
})
export class ProductModal {
    constructor(private backend: BackendService,
        public dialogRef: MatDialogRef<ProductModal>, 
        @Inject(MAT_DIALOG_DATA) public data: Product) {}
    
    addToBasket() {
        let basket: Product[] = JSON.parse(localStorage.getItem(this.backend.email));
        console.log(basket);
        if (basket == null) {
            basket = [];
        }
        basket.push(this.data);

        localStorage.setItem(this.backend.email, JSON.stringify(basket));

        this.dialogRef.close();
    }

    onNoClick() {
        this.dialogRef.close();
    }
}