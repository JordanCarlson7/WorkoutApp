package com.example.mybodymatrix;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayerView;


public class MainActivity extends YouTubeBaseActivity {
    WorkoutMapStorageHandler workOutHandler = new WorkoutMapStorageHandler(this);
    ViewPager viewPager;
    private LinearLayout mDotLayout;
    private TextView[] mDots;
    YouTubePlayerView videoView;
    YoutubeVideoHandler videoHandler;
    int savedPosition;
    String [] urls;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String CURRENT_WORKOUT = "SAVED_STATE";
    private static final String WORKOUT_ID = "workoutID";
    private static final String WORKOUT_LEVEL = "workoutLevel";
    private static final String WKOT_LVB = "workout level button id";
    private static final String WKOT_IDB = "wokrout ID button id";
    public int workoutID = 1;
    public int workoutLevel = 1;
    RadioGroup rg1;
    RadioGroup rg2;
    Button nextButton;
    Button backButton;
    SliderAdapter sliderAdapter = new SliderAdapter(this, this, videoHandler);

    //ends with onDestroy()

    /**
     * Establishes ViewPager and all User-Interface components
     * @param savedInstanceState is not used, SharedPreferences restores previous data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set Radio Buttons
        rg1 = findViewById(R.id.radioGroup1);
        rg2 = findViewById(R.id.radioGroup2);

        //Load last used information
        sharedPreferences = this.getSharedPreferences("com.example.mybodymatrix", Context.MODE_PRIVATE);
        workOutHandler.setFilename(sharedPreferences.getString(CURRENT_WORKOUT, "workouts.txt"));
        workoutID = sharedPreferences.getInt(WORKOUT_ID, 1);
        workoutLevel = sharedPreferences.getInt(WORKOUT_LEVEL, 1);
        int tempLevelButton = sharedPreferences.getInt(WKOT_LVB, 1);
        int tempIDButton = sharedPreferences.getInt(WKOT_IDB,1);
        System.out.println("HERE" + workoutID);

        System.out.println(tempLevelButton + " " + tempIDButton + " ");
        //set buttons to restored states
        if (tempIDButton == 1 && tempLevelButton == 1){
            rg1.check((R.id.L1));
            rg2.check(R.id.W1);
        }
        else{
            //start on last used buttons
            rg1.check(tempLevelButton);
            rg2.check(tempIDButton);

        }

        //Set next/back buttons
        nextButton = findViewById(R.id.nextBtn);
        backButton = findViewById(R.id.backBtn);

        //Set and initialize Workouts, the Viewpager, and VideoHandler, and Dots
        videoView = findViewById(R.id.videoView);
        workOutHandler.readStorage();
        workOutHandler.getWorkoutSpecs(workoutLevel * 10 + workoutID);
        urls = workOutHandler.getVideoUrls();
        videoHandler = new YoutubeVideoHandler(videoView, urls[0]);
        videoHandler.initializeYoutubePlayer();
        viewPager = findViewById(R.id.viewPager);
        mDotLayout = findViewById(R.id.dotsLayout);

        //Two THIS statements for context and activity
        SliderAdapter sliderAdapter = new SliderAdapter(this, this, videoHandler);
        sliderAdapter.setWorkoutSpecs(workOutHandler.getExercises(), workOutHandler.getVideoUrls(), workOutHandler.getReps());
        viewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        viewPager.addOnPageChangeListener(viewListener);


    }

    /**
     * Called after button interactions and reloads proper wokrkout Data
     */
    public void refresh(){
        workOutHandler.getWorkoutSpecs(workoutLevel * 10 + workoutID);
        sliderAdapter.setWorkoutSpecs(workOutHandler.getExercises(), workOutHandler.getVideoUrls(), workOutHandler.getReps());
        urls = workOutHandler.getVideoUrls();
        addDotsIndicator(0);
        videoHandler.getPlayer().cueVideo(urls[0]);
        SliderAdapter newAdapter = new SliderAdapter(this,this, videoHandler);
        newAdapter.setWorkoutSpecs(workOutHandler.getExercises(), workOutHandler.getVideoUrls(), workOutHandler.getReps());
        viewPager.setAdapter(newAdapter);
    }

    /**
     * Evaluates which radio button is clicked and sets correct properties to change workouts
     * @param v
     */
    public void radioClick(View v){
        RadioButton levelButton = findViewById(rg1.getCheckedRadioButtonId());
        RadioButton workoutButton = findViewById(rg2.getCheckedRadioButtonId());
        System.out.println("level now: " + levelButton.getText().charAt(1));
        switch(levelButton.getText().charAt(1)) {
            case '1':
                workoutLevel = 1;
                break;
            case '2':
                workoutLevel = 2;
                break;
            case '3':
                workoutLevel = 3;
            default:
                break;
        }
        System.out.println("workout now: " + workoutButton.getText().charAt(1));
        switch(workoutButton.getText().charAt(1)) {
            case '1':
                workoutID = 1;
                break;
            case '2':
                workoutID = 2;
                break;
            case '3':
                workoutID = 3;
                break;
            case '4':
                workoutID = 4;
                break;
            case '5':
                workoutID = 5;
                break;
            case '6':
                workoutID = 6;
                break;
            default:
                break;
        }
        System.out.println(workoutLevel);
        System.out.println(workoutID);
        refresh();
    }

    /**
     * Move to the previous workout
     * @param v
     */
    public void moveBack(View v){
        if(workoutID != 1) {
            workoutID--;
        }
        else{
            if (workoutID == 1 && workoutLevel == 1){

            }
            else{
                workoutID = 6;
            }

            if (workoutLevel != 1) {
                workoutLevel--;
            }
        }


        System.out.println("level" + workoutLevel);
        System.out.println("workout" + workoutID);

        switch (workoutLevel){
            case 1:
                rg1.check(R.id.L1);
                break;
            case 2:
                rg1.check(R.id.L2);
                break;
            case 3:
                rg1.check(R.id.L3);
                break;
            default:
                rg1.clearCheck();
                break;

        }
        switch (workoutID){
            case 1:
                rg2.check(R.id.W1);
                break;
            case 2:
                rg2.check(R.id.W2);
                break;
            case 3:
                rg2.check(R.id.W3);
                break;
            case 4:
                rg2.check(R.id.W4);
                break;
            case 5:
                rg2.check(R.id.W5);
                break;
            case 6:
                rg2.check(R.id.W6);
                break;
            default:
                rg2.clearCheck();
                break;
        }
    refresh();
    }

    /**
     * Move to the next workout
     * @param v
     */
    public void moveNext(View v){
        if(workoutID != 6) {
            workoutID++;
        }
        else{
            if (workoutID == 6 && workoutLevel == 3){

            }
            else{
                workoutID = 1;
            }
            if (workoutLevel != 3) {
                workoutLevel++;
            }
        }
        System.out.println("level" + workoutLevel);
        System.out.println("workout" + workoutID);
        switch (workoutLevel){
            case 1:
                rg1.check(R.id.L1);
                break;
            case 2:
                rg1.check(R.id.L2);
                break;
            case 3:
                rg1.check(R.id.L3);
                break;
            default:
                rg1.clearCheck();
                break;

        }
        switch (workoutID){
            case 1:
                rg2.check(R.id.W1);
                break;
            case 2:
                rg2.check(R.id.W2);
                break;
            case 3:
                rg2.check(R.id.W3);
                break;
            case 4:
                rg2.check(R.id.W4);
                break;
            case 5:
                rg2.check(R.id.W5);
                break;
            case 6:
                rg2.check(R.id.W6);
                break;
            default:
                rg2.check(R.id.W6);
                break;
        }
        refresh();
    }

    /**
     * Set correct number of dots to indicate "swipe" feature for the ViewPager
     * @param position
     */
    public void addDotsIndicator(int position) {
        mDots = new TextView[urls.length];
        mDotLayout.removeAllViews();

        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorAccent));

            mDotLayout.addView(mDots[i]);
        }

        if(mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    //Listens and changes the viewpager when swiping left or right
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                if (videoHandler.getPlayer() != null) {
                    videoHandler.loadVideo(urls[i]);
                }
                addDotsIndicator(i);
                savedPosition = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        };

    // Occurs after Oncreate() or after onRestart**************End with onStop()
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    //foreground lifetime
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Save data when application stops
        sharedPreferences = this.getSharedPreferences("com.example.mybodymatrix", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(CURRENT_WORKOUT, workOutHandler.getFilename());
        editor.putInt(WORKOUT_ID,workoutID);
        editor.putInt(WORKOUT_LEVEL,workoutLevel);
        editor.putInt(WKOT_LVB, rg1.getCheckedRadioButtonId());
        editor.putInt(WKOT_IDB, rg2.getCheckedRadioButtonId());
        editor.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



}
