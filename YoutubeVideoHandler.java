package com.example.mybodymatrix;


import android.view.View;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Handles youtube Videos and intializes the youtubevideoplayer to stream Youtube videos
 */
public class YoutubeVideoHandler extends YouTubeBaseActivity {
    public static final String YOUTUBE_API_KEY = "AIzaSyA0wquMR2KLFxFvZB2Rfen9HBfY0yw3D6g";
    YouTubePlayerView videoView;
    View sliderView;
    YouTubePlayer player;
    YouTubePlayer.OnInitializedListener youtubeListener;
    String firstVideo;

    YoutubeVideoHandler(YouTubePlayerView videoView, String firstVideo) {
        this.videoView = videoView;
        this.firstVideo = firstVideo;
    }

    public void setSliderView(View sliderView) {
        this.sliderView = sliderView;
    }

    public YouTubePlayer getPlayer() {
        return player;
    }


    public void loadVideo(String url) {
        player.cueVideo(url);
    }

    public void initializeYoutubePlayer() {
        youtubeListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    player = youTubePlayer;
                    player.cueVideo(firstVideo);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult
                    youTubeInitializationResult) {

            }
        };
        videoView.initialize(YOUTUBE_API_KEY, youtubeListener);
    }
}
