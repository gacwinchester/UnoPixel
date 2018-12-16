package com.androide.bobs.unoproject;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class LoseGame extends AppCompatActivity {

    ArrayList<Integer> songs = new ArrayList<>();
    MediaPlayer soundtrack = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        int qtSongs = 4;
        songs.add(R.raw.megaman_endgame);//Adicionar as musicas a lista
        songs.add(R.raw.mario_endgame);//Adicionar as musicas a lista
        songs.add(R.raw.pokemon_endgame);//Adicionar as musicas a lista
        songs.add(R.raw.zelda_endgame);//Adicionar as musicas a lista
        soundtrack.stop();
        soundtrack = MediaPlayer.create(LoseGame.this, songs.get(ThreadLocalRandom.current().nextInt(0, qtSongs + 1)));
        soundtrack.start();
        soundtrack.setLooping(true);
    }
}
