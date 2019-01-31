import { Component, OnInit } from "@angular/core";

import { MatDialog } from '@angular/material';

import { BackendService } from '../../Services/backend.service';
import { Product } from '../../Shared/Product';
import { ProductModal } from '../ProductModal/product.modal';

@Component({
    templateUrl: 'products.component.html',
    styleUrls: ['products.component.css'],
    selector: 'products-component'
})
export class ProductsComponent implements OnInit {

    products: Product[];

    constructor(private backendService: BackendService,
        public dialog: MatDialog) {}

    openModal(product: Product) {
        this.dialog.open(ProductModal, {
            data: product
        });
    }

    ngOnInit() {
        this.backendService.getAllProducts()
        .subscribe(data => {
            for (let i = 0; i < (<any>data).data.products.length; i++) {
                this.products = (<any>data).data.products;
            }
        });
    }
}