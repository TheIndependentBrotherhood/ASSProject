import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';

@Injectable()
export class BackendService {
    constructor(private http: HttpClient) {}

    isConnected = false;
    email: string;
    username: string;
    token: string;

    public connect(email: string, password: string) {
        this.http.post('http://localhost:3000/users/authenticate', {
            email: email,
            password: password
        }).subscribe((data: any) => {
            if (data.status === "success") {
                this.isConnected = true;
                this.email = data.data.user.email;
                this.username = data.data.user.username;
                this.token = data.data.token;
            }
        });
    }

    public register(email: string, username: string, password: string, fullname: string, address: string) {
        this.http.post('http://localhost:3000/users/register', {
            email: email,
            username: username,
            password: password,
            fullname: fullname,
            address: address
        }).subscribe();
    } 

    public getAllProducts() {
        return this.http.get('http://localhost:3000/products');
    }
}