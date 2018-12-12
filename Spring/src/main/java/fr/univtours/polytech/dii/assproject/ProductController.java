package fr.univtours.polytech.dii.assproject;

import fr.univtours.polytech.dii.assproject.models.Product;
import fr.univtours.polytech.dii.assproject.repositories.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository repository;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable("id")ObjectId id) {
        return repository.findBy_id(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Product modifyProductById(@PathVariable("id") ObjectId id, @Valid @RequestBody Product product) {
        Product oldProduct = repository.findBy_id(id);

        if (product.getName() == null) {
            product.setName(oldProduct.getName());
        } if (product.getDescription() == null) {
            product.setDescription(oldProduct.getDescription());
        } if (product.getQuantity() == 0) {
            product.setQuantity(oldProduct.getQuantity());
        }

        System.out.println("!!! " + product.getQuantity() + " !!!");

        repository.save(oldProduct);
        return product;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public Product addProduct(@Valid @RequestBody Product product) {
        product.set_id(ObjectId.get());
        repository.save(product);
        return product;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable ObjectId id) {
        repository.delete(repository.findBy_id(id));
    }
}
