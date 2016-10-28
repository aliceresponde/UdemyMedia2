package com.example.alice.udemymedia;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewHolder;
    private VideoView videoViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageViewHolder = (ImageView) findViewById(R.id.imageVHolder);
        videoViewHolder = (VideoView) findViewById(R.id.videoVHolder);
    }

    /**
     * play song located in raw folder
     * @param view
     */
    public void playSong(View view) {
        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.casino_sound);
        mediaPlayer.start();
    }

    //TODO check  way dont cant display video
    public void playVideo(View view) {
        String strPath = "android.resource://"  + getPackageName() +"/"+ R.raw.fantastic_video;
        Uri uri = Uri.parse(strPath);
        videoViewHolder.setVideoURI(uri);
        videoViewHolder.setMediaController(new MediaController(this));
        videoViewHolder.start();
        videoViewHolder.requestFocus();
    }

    public void selectGaleryImage(View view) {
    }

    public void takeCameraPhoto(View view) {
    }
}
