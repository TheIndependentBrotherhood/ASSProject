package fr.univtours.polytech.dii.assproject.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Arrays;

public class Product {
    @Id
    private ObjectId _id;

    private String name;
    private String description;
    //private ArrayList<String> photos;
    private int quantity;

    public Product() {}

    public Product(ObjectId _id, String name, String description, String[] photos, int quantity) {
        this._id = _id;
        this.name = name;
        this.description = description;
        //this.photos = new ArrayList<String>(Arrays.asList(photos));
        this.quantity = quantity;
    }

    public String get_id() {
        return this._id.toHexString();
    }
    public void set_id(ObjectId id) {
        this._id = id;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    /*public ArrayList<String> getPhotos() {
        return this.photos;
    }
    public String getOnePhoto(int id) {
        if (id < photos.size())
            return photos.get(id);
        else
            return null;
    }
    public void addPhoto(String photo) {
        photos.add(photo);
    }
    public void deletePhoto(int id) {
        photos.remove(id);
    }*/

    public int getQuantity() {
        return this.quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
