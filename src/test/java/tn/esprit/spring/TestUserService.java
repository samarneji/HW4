package tn.esprit.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.services.IUserService;
import java.util.Date;
import java.util.List;

import static tn.esprit.spring.entities.Role.INGENIEUR;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
//@TestMethodOrder(MethodOrderer.Alphanumeric.class)
@DisplayName("Test User class")
public class TestUserService {
    private static User user;

    @Autowired
    private IUserService userService;

    @Test
    @DisplayName("Test Ajouter User")
    public void AddUser() {
        user = new User();
        user.setFirstName("Samar");
        user.setLastName("Neji");
        user.setDateNaissance(new Date(18 / 1999));
        user.setRole(INGENIEUR);
        long id = userService.addUser(user).getId();
        assertTrue(id != 0 );
    }

    @Test
    public void testRetrieveAllUsers() {
        assertTrue(userService.retrieveAllUsers().size() > 0);
    }

    @Test
    @DisplayName("Modifier un User ")
    @Order(4)
    public void UpdateContract() {
        log.info("Update user");
        User user = userService.retrieveUser(1L);
        user.setFirstName("Asma");
        userService.updateUser(user);
        log.info("Update user first name");
        assertTrue(userService.retrieveUser(user.getId()).getFirstName().equals("Asma"));
    }

    @Test
    @DisplayName("Select les users")
    @Order(3)
    public void getAllUsers() {
        log.info("Get all users");
        assertTrue(userService.retrieveAllUsers().size() > 0);
    }

    @Test
    @DisplayName("Supprimer un user ")
    @Order(5)
    public void RemoveContract() {
        log.info("Remove user");
        userService.deleteUser(user.getId());
        assertFalse(userService.retrieveAllUsers().contains(user));
    }


}
