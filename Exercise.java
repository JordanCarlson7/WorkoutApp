package com.example.mybodymatrix;

/**
 *  The Exercise class holds an individual exercise, which consists of
 *  the exercise's name, a link to a video (to be used by MainActivity to
 *  pull up the appropriate Youtube video), and a number of reps.
 */
public class Exercise {
    private String video;
    private String name;
    private String reps;

    public Exercise(String video, String name, String reps) {
        this.video = video;
        this.name = name;
        this.reps = reps;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReps() {
        return reps;
    }
}
