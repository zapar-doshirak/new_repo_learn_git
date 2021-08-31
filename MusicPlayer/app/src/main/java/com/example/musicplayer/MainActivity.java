package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    ImageView playPauseIcon;
    SeekBar seekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music_1);
        playPauseIcon = findViewById(R.id.play);

        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                 seekBar.setProgress(mediaPlayer.getCurrentPosition());

            }
        }, 0 , 1000);


    }

    public void next(View view) {
        seekBar.setProgress(mediaPlayer.getDuration());
        mediaPlayer.seekTo(mediaPlayer.getDuration());
        mediaPlayer.pause();
        playPauseIcon.setImageResource(R.drawable.play);
    }

    public void previos(View view) {
        seekBar.setProgress(0);
        mediaPlayer.seekTo(0);
        mediaPlayer.pause();
        playPauseIcon.setImageResource(R.drawable.play);
    }

    public void play(View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            playPauseIcon.setImageResource(R.drawable.play);
        } else {
            mediaPlayer.start();
            playPauseIcon.setImageResource(R.drawable.pause);
        }
    }
}