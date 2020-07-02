package com.example.mybodymatrix;

import java.util.HashMap;

/**
 * The WorkoutMap object is designed to hold all 18 of the workouts provided
 * by John Sirrine.
 */
public class WorkoutMap {
    private HashMap<Integer, Workout> workoutMap;

    public WorkoutMap(HashMap<Integer, Workout> workoutMap) {
        this.workoutMap = workoutMap;
    }

    public HashMap<Integer, Workout> getWorkoutMap() {
        return workoutMap;
    }


}
