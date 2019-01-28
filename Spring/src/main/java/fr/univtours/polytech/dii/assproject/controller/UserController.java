package fr.univtours.polytech.dii.assproject.controller;

import fr.univtours.polytech.dii.assproject.enumeration.Rank;
import fr.univtours.polytech.dii.assproject.models.StoreUser;
import fr.univtours.polytech.dii.assproject.repositories.StoreUserRepository;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final StoreUserRepository repository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(StoreUserRepository userRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping(value = {"", "/"})
    public List<StoreUser> getAllUsers() {
        return repository.findAll();
    }

    @GetMapping(value = {"/{id}"})
    public StoreUser getUser(@PathVariable("id") ObjectId id) {
        return repository.findById(id);
    }

    @PostMapping(value = "/sign-up")
    public StoreUser addUser(@RequestParam("username") String username,
                        @RequestParam("email") String email,
                        @RequestParam("password") String password,
                        @RequestParam("address") String address,
                        @RequestParam(value = "firstname", required = false) String firstName,
                        @RequestParam(value = "lastname", required = false) String lastName) {
        StoreUser user = new StoreUser(username, email, password, firstName, lastName, address, Rank.USER);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        repository.save(user);

        return user;
    }

    @PutMapping(value = "/{id}")
    public StoreUser updateUser(@PathVariable("id") ObjectId id,
                           @RequestParam(value = "username", required = false) String username,
                           @RequestParam(value = "email", required = false) String email,
                           @RequestParam(value = "password", required = false) String password,
                           @RequestParam(value = "address", required = false) String address,
                           @RequestParam(value = "city", required = false) String city,
                           @RequestParam(value = "firstname", required = false) String firstName,
                           @RequestParam(value = "lastname", required = false) String lastName) {

        StoreUser user = repository.findById(id);
        if (username != null)
            user.setUsername(username);
        if (email != null)
            user.setEmail(email);
        if (password != null)
            user.setPassword(password);
        if (address != null)
            user.setAddress(address);
        if (city != null)
            user.setCity(city);
        if (firstName != null)
            user.setFirstName(firstName);
        if (lastName != null)
            user.setLastName(lastName);

        repository.save(user);

        return user;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") ObjectId id) {
        StoreUser user = repository.findById(id);
        repository.delete(user);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
