package com.konstantin.habittracker.controller;

import com.konstantin.habittracker.business.logic.service.DailyHabitService;
import com.konstantin.habittracker.dto.request.UpdateHabitCompletionRequest;
import com.konstantin.habittracker.dto.response.DailyHabitResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/daily-habits")
public class DailyHabitController {

    private final DailyHabitService dailyHabitService;

    public DailyHabitController(DailyHabitService dailyHabitService) {
        this.dailyHabitService = dailyHabitService;
    }

    @GetMapping
    public ResponseEntity<List<DailyHabitResponse>> getTodayHabits() {
        List<DailyHabitResponse> dailyHabits = dailyHabitService.getTodayHabits();

        return ResponseEntity.ok(dailyHabits);
    }

    @PutMapping("/{habitId}/completion")
    public ResponseEntity<DailyHabitResponse> updateTodayHabitCompletion(
            @PathVariable Long habitId,
            @Valid @RequestBody UpdateHabitCompletionRequest request
    ) {
        DailyHabitResponse response = dailyHabitService.updateTodayHabitCompletion(habitId, request);

        return ResponseEntity.ok(response);
    }
}