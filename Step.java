package com.example.mybodymatrix;

import java.util.LinkedList;

/**
 * The Step class holds a step (or portion) of a workout consisting of multiple
 * exercises. The Step has a name (i.e. warmup, strength, etc.), description, number of circuits (i.e. number of
 * times each exercise is repeated), and the list of exercises.
 */
public class Step {
    private int circuits;
    private String name;
    private String description;
    private LinkedList<Exercise> exercises;

    public Step(int circuits, String name, String description, LinkedList<Exercise> exercises) {
        this.exercises = exercises;
        this.name = name;
        this.description = description;
        this.circuits = circuits;
    }

    public LinkedList<Exercise> getExercises() {
        return exercises;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
