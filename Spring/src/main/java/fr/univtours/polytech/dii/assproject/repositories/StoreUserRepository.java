package fr.univtours.polytech.dii.assproject.repositories;

import fr.univtours.polytech.dii.assproject.models.StoreUser;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoreUserRepository extends MongoRepository<StoreUser, String> {
    StoreUser findById(ObjectId id);
    StoreUser findByUsername(String username);
}
