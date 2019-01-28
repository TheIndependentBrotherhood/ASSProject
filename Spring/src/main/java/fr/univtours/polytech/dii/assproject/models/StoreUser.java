package fr.univtours.polytech.dii.assproject.models;

import fr.univtours.polytech.dii.assproject.enumeration.Rank;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class StoreUser {
    @Id
    private ObjectId id;

    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String city;

    private List<Order> orders = new ArrayList<>();

    private Rank rank;

    public StoreUser() {}

    public StoreUser(String username, String email, String password,
                String firstName, String lastName, String address,
                Rank rank) {
        this.id = ObjectId.get();
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.rank = rank;
    }

    public String getId() {
        return id.toHexString();
    }
    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public Rank getRank() {
        return rank;
    }
    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order... orders) {
        for (Order order: orders) {
            this.orders.add(order);
        }
    }

    public Order getOrder(ObjectId id) {
        for (Order order: orders) {
            if (order.getId().equals(id.toHexString()))
                return order;
        }
        return null;
    }
}
