package com.androide.bobs.unoproject;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class StartScreen extends AppCompatActivity {

    ArrayList<Integer> songs = new ArrayList<>();
    MediaPlayer soundtrack = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        songs.add(R.raw.megaman);

        // criar metodo random que escolhe a musica de fundo

        soundtrack.stop();
        soundtrack = MediaPlayer.create(StartScreen.this, songs.get(0));
        soundtrack.start();
        soundtrack.setLooping(true);

        Handler hand = new Handler();
        hand.postDelayed(new Runnable() {
            @Override
            public void run() {
//                ActivityOptions opt = ActivityOptions.makeSceneTransitionAnimation(StartScreen.this);
                Intent i = new Intent(StartScreen.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                i.putExtra("FADE_JAVA", "Slide")
//                startActivity(i, ActivityOptions.makeSceneTransitionAnimation(StartScreen.this).toBundle());
                startActivity(i);
                finish();
            }
        }, 4000);
    }

}
