package com.konstantin.habittracker.business.logic.service;

import com.konstantin.habittracker.dto.request.RegisterRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RegisterRequestValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void shouldReturnErrorWhenNameIsBlank(String name) {
        RegisterRequest request = validRegisterRequest();
        request.setName(name);

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);

        assertTrue(containsMessage(violations, "Name is required"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void shouldReturnErrorWhenEmailIsBlank(String email) {
        RegisterRequest request = validRegisterRequest();
        request.setEmail(email);

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);

        assertTrue(containsMessage(violations, "Email is required"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"invalidemail", "test.com", "test@", "@email.com"})
    void shouldReturnErrorWhenEmailFormatIsInvalid(String email) {
        RegisterRequest request = validRegisterRequest();
        request.setEmail(email);

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);

        assertTrue(containsMessage(violations, "Email must be valid"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void shouldReturnErrorWhenPasswordIsBlank(String password) {
        RegisterRequest request = validRegisterRequest();
        request.setPassword(password);

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);

        assertTrue(containsMessage(violations, "Password is required"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "123", "1234567"})
    void shouldReturnErrorWhenPasswordIsTooShort(String password) {
        RegisterRequest request = validRegisterRequest();
        request.setPassword(password);

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);

        assertTrue(containsMessage(violations, "Password must be at least 8 characters"));
    }

    @Test
    void shouldReturnNoErrorsForValidRegisterRequest() {
        RegisterRequest request = validRegisterRequest();

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty());
    }

    private RegisterRequest validRegisterRequest() {
        RegisterRequest request = new RegisterRequest();
        request.setName("Konstantin");
        request.setEmail("test@email.com");
        request.setPassword("password123");
        return request;
    }

    private boolean containsMessage(Set<ConstraintViolation<RegisterRequest>> violations, String message) {
        return violations.stream()
                .anyMatch(violation -> violation.getMessage().equals(message));
    }
}
