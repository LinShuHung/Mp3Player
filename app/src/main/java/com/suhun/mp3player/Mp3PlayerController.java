package com.suhun.mp3player;

import android.media.MediaPlayer;
import android.os.Binder;

public class Mp3PlayerController extends Binder {
    private MediaPlayer mp3Player;

    public Mp3PlayerController(MediaPlayer mediaPlayer){
        this.mp3Player = mediaPlayer;
    }
}
