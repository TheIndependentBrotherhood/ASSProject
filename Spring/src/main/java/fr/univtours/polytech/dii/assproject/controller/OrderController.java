package fr.univtours.polytech.dii.assproject.controller;

import fr.univtours.polytech.dii.assproject.models.Order;
import fr.univtours.polytech.dii.assproject.models.StoreUser;
import fr.univtours.polytech.dii.assproject.repositories.ProductRepository;
import fr.univtours.polytech.dii.assproject.repositories.StoreUserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class OrderController {
    private final StoreUserRepository userRepository;

    @Autowired
    public OrderController(StoreUserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/{id_user}/order")
    public Order addOrder(@PathVariable("id_user") ObjectId idUser,
                         @RequestBody Order order) {
        StoreUser user = userRepository.findById(idUser);

        user.addOrder(order);

        userRepository.save(user);

        return order;
    }

    @GetMapping(value = "/{id_user}/order")
    public List<Order> getAllOrders(@PathVariable("id_user") ObjectId idUser) {
        StoreUser user = userRepository.findById(idUser);
        return user.getOrders();
    }

    @GetMapping(value = "/{id_user}/order/{id_order}")
    public Order getAnOrder(@PathVariable("id_user") ObjectId idUser,
                         @PathVariable("id_order") ObjectId idOrder) {

        StoreUser user = userRepository.findById(idUser);
        return user.getOrder(idOrder);
    }
}
