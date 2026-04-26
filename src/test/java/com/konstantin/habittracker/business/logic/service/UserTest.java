package com.konstantin.habittracker.business.logic.service;

import com.konstantin.habittracker.model.Role;
import com.konstantin.habittracker.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldCreateUserWithCorrectValues() {
        User user = new User(
                "Konstantin",
                "test@email.com",
                "hashedPassword123",
                Role.USER
        );

        assertEquals("test@email.com", user.getEmail());
        assertEquals(Role.USER, user.getRole());
    }

    @Test
    void shouldSetCreatedAtAndUpdatedAtWhenUserIsCreated() {
        User user = new User(
                "Konstantin",
                "test@email.com",
                "hashedPassword123",
                Role.USER
        );

        user.onCreate();

        assertNotNull(user.getCreatedAt());
        assertNotNull(user.getUpdatedAt());
    }

    @Test
    void shouldUpdateUpdatedAtWhenUserIsUpdated() throws InterruptedException {
        User user = new User(
                "Konstantin",
                "test@email.com",
                "hashedPassword123",
                Role.USER
        );

        user.onCreate();
        LocalDateTime originalUpdatedAt = user.getUpdatedAt();

        Thread.sleep(5);

        user.onUpdate();

        assertTrue(user.getUpdatedAt().isAfter(originalUpdatedAt));
    }

    @Test
    void shouldChangeRole() {
        User user = new User(
                "Konstantin",
                "test@email.com",
                "hashedPassword123",
                Role.USER
        );

        user.changeRole(Role.ADMIN);

        assertEquals(Role.ADMIN, user.getRole());
    }

    @Test
    void shouldChangePassword() {
        User user = new User(
                "Konstantin",
                "test@email.com",
                "oldHashedPassword",
                Role.USER
        );

        user.changePassword("newHashedPassword");

        assertEquals("newHashedPassword", user.getPassword());
    }
}