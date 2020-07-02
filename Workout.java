package com.example.mybodymatrix;

import java.util.LinkedList;

/**
 * The Workout object contains a list of Steps that form a complete workout.
 */
public class Workout {
    private LinkedList<Step> steps;

    public Workout(LinkedList<Step> steps) {
        this.steps = steps;
    }

    public LinkedList<Step> getSteps() {
        return steps;
    }

}
