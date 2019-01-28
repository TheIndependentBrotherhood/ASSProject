package fr.univtours.polytech.dii.assproject.controller;

import fr.univtours.polytech.dii.assproject.enumeration.Rank;
import fr.univtours.polytech.dii.assproject.models.StoreUser;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserController userController;

    private ObjectId id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String city;

    private StoreUser user;

    @Before
    public void createUser() {
        id = ObjectId.get();
        username = "assproject";
        email = "ass@project.net";
        password = "amazingpassword";
        firstName = "Hello";
        lastName = "World";
        address = "1 Rue de la cathedrale";
        city = "Paris";

        user = new StoreUser(username, email, password, firstName, lastName, address, Rank.USER);
        user.setId(id);
    }

    @Test
    public void whenAddNewUser_thenReturnUser() throws Exception {
        when(userController.addUser(username, email, password, address, firstName, lastName)).thenReturn(user);

        mockMvc.perform(post("/user")
                .param("username", username)
                .param("email", email)
                .param("password", password)
                .param("firstname", firstName)
                .param("lastname", lastName)
                .param("address", address)
                .param("city", city))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", is(id.toHexString())))
                .andExpect(jsonPath("$.username", is(username)))
                .andExpect(jsonPath("$.email", is(email)))
                .andExpect(jsonPath("$.password", is(password)))
                .andExpect(jsonPath("$.firstName", is(firstName)))
                .andExpect(jsonPath("$.lastName", is(lastName)))
                .andExpect(jsonPath("$.address", is(address)))
                .andExpect(jsonPath("$.city", is(city)));
    }

    @Test
    public void whenGetAUser_thenReturnUser() throws Exception {
        when(userController.getUser(id)).thenReturn(user);

        mockMvc.perform(get("/user/" + id))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", is(id.toHexString())))
                .andExpect(jsonPath("$.username", is(username)))
                .andExpect(jsonPath("$.email", is(email)))
                .andExpect(jsonPath("$.password", is(password)))
                .andExpect(jsonPath("$.firstName", is(firstName)))
                .andExpect(jsonPath("$.lastName", is(lastName)))
                .andExpect(jsonPath("$.address", is(address)))
                .andExpect(jsonPath("$.city", is(city)));
    }

    @Test
    public void whenGetAllUsers_thenReturnListOfUsers() throws Exception {
        when(userController.getAllUsers()).thenReturn(Arrays.asList(user));

        mockMvc.perform(get("/user"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(id.toHexString())))
                .andExpect(jsonPath("$[0].username", is(username)))
                .andExpect(jsonPath("$[0].email", is(email)))
                .andExpect(jsonPath("$[0].password", is(password)))
                .andExpect(jsonPath("$[0].firstName", is(firstName)))
                .andExpect(jsonPath("$[0].lastName", is(lastName)))
                .andExpect(jsonPath("$[0].address", is(address)))
                .andExpect(jsonPath("$[0].city", is(city)));
    }

    @Test
    public void whenUpdateUser_thenReturnUpdatedUser() throws Exception {
        String newUsername  = "fabienne";
        String newPassword = "gkc6o6";
        String newCity = "Le Puy En Velay";

        StoreUser updatedUser = user;
        updatedUser.setUsername(newUsername);
        updatedUser.setPassword(newPassword);
        updatedUser.setCity(newCity);

        when(userController.updateUser(id, newUsername, null, newPassword, null, newCity, null, null))
                .thenReturn(updatedUser);

        mockMvc.perform(put("/user/" + id)
                .param("username", newUsername)
                .param("password", newPassword)
                .param("city", newCity))
                .andExpect(jsonPath("$.id", is(id.toHexString())))
                .andExpect(jsonPath("$.username", is(newUsername)))
                .andExpect(jsonPath("$.email", is(email)))
                .andExpect(jsonPath("$.password", is(newPassword)))
                .andExpect(jsonPath("$.firstName", is(firstName)))
                .andExpect(jsonPath("$.lastName", is(lastName)))
                .andExpect(jsonPath("$.address", is(address)))
                .andExpect(jsonPath("$.city", is(newCity)));
    }

    @Test
    public void whenDeleteUser_thenReturnHttpCode2xx() throws Exception {
        mockMvc.perform(delete("/user/" + id))
                .andExpect(status().is2xxSuccessful());
    }
}
