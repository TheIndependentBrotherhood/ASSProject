package fr.univtours.polytech.dii.assproject.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Order {
    @Id
    private ObjectId id;
    private List<Product> products = new ArrayList<>();
    private Double sum;

    public Order() {
        this.id = ObjectId.get();
        this.sum = 0.0;
    }

    public Order(ObjectId id, List<Product> products, Double sum) {
        this.id = id;
        this.products = products;
        this.sum = sum;
    }

    public String getId() {
        return id.toHexString();
    }
    public void setId(ObjectId id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Double getSum() {
        return sum;
    }
    public void setSum(Double sum) {
        this.sum = sum;
    }
}
