package fr.univtours.polytech.dii.assproject.repositories;

import fr.univtours.polytech.dii.assproject.models.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    Product findById(ObjectId id);
}
