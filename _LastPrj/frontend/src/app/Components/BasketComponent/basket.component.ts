import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Product } from 'src/app/Shared/Product';
import { BackendService } from 'src/app/Services/backend.service';

@Component({
    selector: 'basket-component',
    templateUrl: 'basket.component.html'
})
export class BasketComponent implements OnInit {

    productInBasket: Product[];

    constructor(private backend: BackendService) {}

    checkBasket() {
        if (localStorage.getItem(this.backend.email) != null) {
            this.productInBasket = JSON.parse(localStorage.getItem(this.backend.email));
        }
    }

    delete(product: Product) {
        console.log(this.productInBasket.splice(this.productInBasket.indexOf(product), 1));
        localStorage.setItem(this.backend.email, JSON.stringify(this.productInBasket.splice(this.productInBasket.indexOf(product), 1)));
    }

    ngOnInit() {
        this.checkBasket();
        setInterval(() => {this.checkBasket();}, 250);
    }
}