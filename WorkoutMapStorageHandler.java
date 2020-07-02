package com.example.mybodymatrix;

import android.app.Activity;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

/**
 * This handler class reads a list of workouts from a file in the
 * assets folder into a WorkoutMap object for later use.
 */
public class WorkoutMapStorageHandler {
    private WorkoutMap workoutMap;
    private String [] exercises;
    private String [] videoUrls;
    private String [] reps;
    private Activity activity;
    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public WorkoutMapStorageHandler(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String[] getExercises() {
        return exercises;
    }

    public String[] getVideoUrls() {
        return videoUrls;
    }

    public String[] getReps() {
        return reps;
    }

    /**
     * The readStorage function opens and reads from the workouts.txt
     * file into a WorkoutMap object to allow the app to load and
     * display various workouts.
     */
    public void readStorage() {
        String json = "";
        try {
            InputStream inputStream = getActivity().getAssets().open("workouts.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException io) {
            System.err.println(io);
        }
        Gson gson = new Gson();
        workoutMap = gson.fromJson(json, WorkoutMap.class);
    }

    public void getWorkoutSpecs(int id) {
        Workout workout = workoutMap.getWorkoutMap().get(id);
        LinkedList<String> exercises = new LinkedList<>();
        LinkedList<String> videoUrls = new LinkedList<>();
        LinkedList<String> reps = new LinkedList<>();
        for (int i = 0; i < workout.getSteps().size(); i++) {
            Step step = workout.getSteps().get(i);
            for (int j = 0; j < step.getExercises().size(); j++) {
                exercises.add(step.getExercises().get(j).getName());
                videoUrls.add(step.getExercises().get(j).getVideo());
                reps.add(step.getExercises().get(j).getReps());
            }
        }
        this.exercises = exercises.toArray(new String[exercises.size()]);
        this.videoUrls = videoUrls.toArray(new String[videoUrls.size()]);
        this.reps = reps.toArray(new String[reps.size()]);

    }
}
