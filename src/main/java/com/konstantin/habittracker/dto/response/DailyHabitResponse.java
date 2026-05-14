package com.konstantin.habittracker.dto.response;

import com.konstantin.habittracker.model.HabitType;

import java.time.LocalDate;

public class DailyHabitResponse {

    private Long habitId;
    private String habitName;
    private HabitType habitType;
    private LocalDate completionDate;
    private boolean completed;

    public DailyHabitResponse(
            Long habitId,
            String habitName,
            HabitType habitType,
            LocalDate completionDate,
            boolean completed
    ) {
        this.habitId = habitId;
        this.habitName = habitName;
        this.habitType = habitType;
        this.completionDate = completionDate;
        this.completed = completed;
    }

    public Long getHabitId() {
        return habitId;
    }

    public String getHabitName() {
        return habitName;
    }

    public HabitType getHabitType() {
        return habitType;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public boolean isCompleted() {
        return completed;
    }
}