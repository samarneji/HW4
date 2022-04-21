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
 class TestUserService {
    private static User user;

    @Autowired
    private IUserService userService;

    @Test
    @DisplayName("Test Ajouter User")
     void AddUser() {
        user = new User();
        user.setFirstName("Samar");
        user.setLastName("Neji");
        user.setDateNaissance(new Date(18 / 1999));
        user.setRole(INGENIEUR);
        long id = userService.addUser(user).getId();
        assertNotEquals(0,id  );
    }

    @Test
     void testRetrieveAllUsers() {
        assertTrue(userService.retrieveAllUsers().size() > 0);
    }

    @Test
    @DisplayName("Modifier un User ")
    @Order(4)
     void UpdateContract() {
        log.info("Update user");
        User user = userService.retrieveUser(1L);
        user.setFirstName("Asma");
        userService.updateUser(user);
        log.info("Update user first name");
        assertEquals("Asma",userService.retrieveUser(user.getId()).getFirstName());
    }

    @Test
    @DisplayName("Select les users")
    @Order(3)
     void getAllUsers() {
        log.info("Get all users");
        assertTrue(userService.retrieveAllUsers().size() > 0);
    }

    @Test
    @DisplayName("Supprimer un user ")
    @Order(5)
     void RemoveContract() {
        log.info("Remove user");
        userService.deleteUser(user.getId());
        assertFalse(userService.retrieveAllUsers().contains(user));
    }


}
