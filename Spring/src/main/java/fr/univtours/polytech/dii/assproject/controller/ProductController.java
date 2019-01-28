package fr.univtours.polytech.dii.assproject.controller;

import fr.univtours.polytech.dii.assproject.models.Product;
import fr.univtours.polytech.dii.assproject.repositories.ProductRepository;
import org.apache.commons.io.FilenameUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ValidationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductRepository repository;

    private static final String IMAGE_FOLDER = System.getProperty("user.home") +
            File.separator + "ASS_Images" + File.separator;

    @Autowired
    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }


    /**
     * Return all products store in MongoDB
     * @return A array JSON with the list of product
     */
    @GetMapping(value = {"", "/"})
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    /**
     * Return the product with the corresponding id
     * @param id The id of the product
     * @return The product
     */
    @GetMapping(value = "/{id}")
    public Product getProductById(@PathVariable("id")ObjectId id) {
        return repository.findById(id);
    }

    /**
     * Create a new product
     * @param name The name of the new product
     * @param description The description of the product
     * @param quantity The quantity of product
     * @param photo The photo of the product which will be store on server
     * @return the created product
     * @throws Exception
     */
    @PostMapping(value = {"", "/"})
    public Product addProduct(@RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("price") Double price,
                              @RequestParam("quantity") Integer quantity,
                              @RequestPart("photo") MultipartFile photo) throws IOException {

        if (photo.isEmpty())
            throw new ValidationException("The file filed is empty");

        Product product = new Product(name, description, price, quantity, null);

        addPhoto(photo, product);

        // Save Product in MongoDB
        repository.save(product);
        return product;
    }

    /**
     * Return the product's photo
     * @param id The product's id
     * @return The image to the client
     * @throws IOException
     */
    @GetMapping(value = "/{id}/photo", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getPhoto(@PathVariable("id") ObjectId id) throws IOException {
        Product product = repository.findById(id);

        File photo = new File(IMAGE_FOLDER + product.getPhoto());

        if (!photo.exists())
            throw new IOException("File Not Found");

        byte[] bytes = StreamUtils.copyToByteArray(new FileInputStream(photo));

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }

    /**
     *
     * @param id
     * @param name
     * @param description
     * @param quantity
     * @param photo
     * @return
     */
    @PutMapping(value = "/{id}")
    public Product modifyProductById(@PathVariable("id") ObjectId id,
                                     @RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "description", required = false) String description,
                                     @RequestParam(value = "price", required = false) Double price,
                                     @RequestParam(value = "quantity", required = false) Integer quantity,
                                     @RequestPart(value = "photo", required = false) MultipartFile photo) throws IOException {
        Product productToUpdate = repository.findById(id);

        if (productToUpdate != null) {
            if (name != null)
                productToUpdate.setName(name);
            if (description != null)
                productToUpdate.setDescription(description);
            if (price != null)
                productToUpdate.setPrice(price);
            if (quantity != null)
                productToUpdate.setQuantity(quantity);
            if (photo != null) {
                deletePhoto(productToUpdate);
                addPhoto(photo, productToUpdate);
            }

            repository.save(productToUpdate);
            return productToUpdate;
        } else {
            return null;
        }
    }

    /**
     * Delete the product
     * @param id
     * @return
     * @throws IOException
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteProduct(@PathVariable ObjectId id) throws IOException {
        Product deletedProduct = repository.findById(id);

        if (deletedProduct != null) {
            repository.delete(deletedProduct);

            deletePhoto(deletedProduct);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * Delete the product's photo from storage
     * @param product Product which the photo will be removed
     * @throws IOException
     */
    private void deletePhoto(Product product) throws IOException {
        Path photo = Paths.get(IMAGE_FOLDER + product.getPhoto());
        Files.deleteIfExists(photo);
    }

    /**
     * Add a photo to the product
     * @param photo The photo file
     * @param product The product to which we will add the photo
     * @throws IOException
     */
    private void addPhoto(MultipartFile photo, Product product) throws IOException {

        // Create file name, based on timestamp + extensions
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        String extension = FilenameUtils.getExtension(photo.getOriginalFilename());
        String filename = ts.getTime() + "." + extension;

        // Store the file on disk
        byte[] fileContent = photo.getBytes();
        Path pathToRegister = Paths.get(IMAGE_FOLDER +  filename);

        // Create the directory if not exists
        if (!pathToRegister.getParent().toFile().exists())
            Files.createDirectories(pathToRegister.getParent());

        // Write the photo in the folder
        Files.write(pathToRegister, fileContent);

        // Store the filename in product
        product.setPhoto(filename);
    }
}
