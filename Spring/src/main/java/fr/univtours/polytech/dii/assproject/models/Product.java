package fr.univtours.polytech.dii.assproject.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Product {
    @Id
    private ObjectId id;

    private String name;
    private String description;
    private String photo;
    private Double price;
    private Integer quantity;

    public Product() {}

    public Product(String name, String description, Double price, Integer quantity, String photo) {
        this.id = ObjectId.get();
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.photo = photo;
    }

    public String getId() {
        return id.toHexString();
    }
    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        if (quantity >= 0)
            this.quantity = quantity;
        else
            this.quantity = 0;
    }

    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
