package com.konstantin.habittracker.business.logic.service;

import com.konstantin.habittracker.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RegisterValidationService{
    public List<String> validateUser(User user) {
        List<String> errors = new ArrayList<>();
        if (user == null){
            errors.add("User object cannot be null");
            return errors;
        }
        if (user.getEmail() == null || user.getEmail().isBlank() || !user.getEmail().contains("@")) {
            errors.add("Invalid email format.");
        }

        if (user.getName() == null || user.getName().isBlank() || user.getName().length() < 3) {
            errors.add("Username must be at least 3 characters long.");
        }

        if (user.getPassword() == null || user.getPassword().isBlank() || user.getPassword().length() < 6) {
            errors.add("Password must be at least 6 characters long.");
        }
        return errors;
    }
}
