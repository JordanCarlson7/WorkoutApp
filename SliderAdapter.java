package com.example.mybodymatrix;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter{

    Context context;
    Activity activity;
    String [] exercises;
    String [] videoUrls;
    String [] reps;
    YoutubeVideoHandler videoHandler;

    public SliderAdapter(Context context, Activity activity, YoutubeVideoHandler videoHandler) {
        this.context = context;
        this.activity = activity;
        this.videoHandler = videoHandler;
    }

    public void setWorkoutSpecs(String [] exercises, String [] videoUrls, String [] reps) {
        this.exercises = exercises;
        this.videoUrls = videoUrls;
        this.reps = reps;
    }


    @Override
    public int getCount() {
        return exercises.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        TextView exercise = view.findViewById(R.id.exercise);
        TextView repCount = view.findViewById(R.id.reps);

        exercise.setText(exercises[position]);
        repCount.setText("" + reps[position]);

        videoHandler.setSliderView(view);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){

        container.removeView((ConstraintLayout)object);
    }


}
