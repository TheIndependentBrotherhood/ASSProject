package fr.univtours.polytech.dii.assproject.controller;

import fr.univtours.polytech.dii.assproject.models.Product;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductController productController;

    private ObjectId id;
    private String name;
    private String description;
    private Double price;
    private int quantity;

    private MockMultipartFile photo;

    private Product product;

    @Before
    public void createProduct() {
        id = ObjectId.get();
        name = "Lit d'époque";
        description = "Meuble d'époque de la renaissance";
        price = 5.99;
        quantity = 42;

        photo = new MockMultipartFile("photo", "image.jpg", "image/jpeg",
                "fr/univtours/polytech/dii/assproject/controller/resource/test.jpg".getBytes());
        product = new Product(name, description, price, quantity, "123123.jpg");
        product.setId(id);
    }

    @Test
    public void whenAddNewProduct_thenReturnProduct() throws Exception {
        when(productController.addProduct(name, description, price, quantity, photo)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/product")
                    .file(photo)
                    .param("name", name)
                    .param("description", description)
                    .param("price", String.valueOf(price))
                    .param("quantity", String.valueOf(quantity)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", is(id.toHexString())))
                .andExpect(jsonPath("$.name", is(product.getName())))
                .andExpect(jsonPath("$.description", is(product.getDescription())))
                .andExpect(jsonPath("$.photo").exists())
                .andExpect(jsonPath("$.quantity", is(product.getQuantity())));
    }

    @Test
    public void whenGetAllProducts_thenReturnListOfProducts() throws Exception {
        when(productController.getAllProducts()).thenReturn(Arrays.asList(product));

        mockMvc.perform(get("/product"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(id.toHexString())))
                .andExpect(jsonPath("$[0].name", is(product.getName())))
                .andExpect(jsonPath("$[0].description", is(product.getDescription())))
                .andExpect(jsonPath("$[0].price", is(product.getPrice())))
                .andExpect(jsonPath("$[0].quantity", is(product.getQuantity())))
                .andExpect(jsonPath("$[0].photo").exists());
    }

    @Test
    public void whenGetAProduct_thenReturnProduct() throws Exception {
        when(productController.getProductById(id)).thenReturn(product);

        mockMvc.perform(get("/product/" + product.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", is(product.getId())))
                .andExpect(jsonPath("$.name", is(product.getName())))
                .andExpect(jsonPath("$.description", is(product.getDescription())))
                .andExpect(jsonPath("$.price", is(product.getPrice())))
                .andExpect(jsonPath("$.quantity", is(product.getQuantity())))
                .andExpect(jsonPath("$.photo").exists());
    }

    @Test
    public void whenUpdateProduct_thenReturnUpdatedProduct() throws Exception {
        String newName = "Télévision",
                newDescription = "Télévision des années 80";
        int newQuantity = 2;

        Product updatedProduct = product;
        updatedProduct.setName(newName);
        updatedProduct.setDescription(newDescription);
        updatedProduct.setQuantity(newQuantity);
        updatedProduct.setPhoto("456456.jpg");

        when(productController.modifyProductById(id, newName, newDescription, null, newQuantity, photo))
                .thenReturn(updatedProduct);

        // Creation of multipart mock PUT method
        MockMultipartHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.multipart("/product/" + product.getId());
        builder.with(request -> {
            request.setMethod("PUT");
            return request;
        });

        mockMvc.perform(builder
                    .file(photo)
                    .param("name", newName)
                    .param("description", newDescription)
                    .param("quantity", String.valueOf(newQuantity)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", is(product.getId())))
                .andExpect(jsonPath("$.name", is(newName)))
                .andExpect(jsonPath("$.description", is(newDescription)))
                .andExpect(jsonPath("$.price", is(product.getPrice())))
                .andExpect(jsonPath("$.quantity", is(newQuantity)))
                .andExpect(jsonPath("$.photo", not(product.getPhoto())));
    }

    @Test
    public void whenDeleteProduct_thenReturnHttpCode2xx() throws Exception {
        mockMvc.perform(delete("/product/" + id))
                .andExpect(status().is2xxSuccessful());
    }
}
