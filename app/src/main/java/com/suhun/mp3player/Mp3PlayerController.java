package com.suhun.mp3player;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.util.Log;

import java.util.ArrayList;

public class Mp3PlayerController extends Binder {
    private String tag = Mp3PlayerController.class.getSimpleName();
    private MediaPlayer mp3Player;

    public Mp3PlayerController(MediaPlayer mediaPlayer){
        this.mp3Player = mediaPlayer;
    }

    public void playMp3Song(AppData appData){
        int playIndex = appData.mp3SongListHandler.playIndex;
        ArrayList<String> songList = appData.mp3SongListHandler.mp3SongList;

        if(!mp3Player.isPlaying()){
            mp3Player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mp3Player.setDataSource(songList.get(playIndex));
                mp3Player.prepare();
                mp3Player.start();
            }catch (Exception e){
                Log.d(tag, "----Error occur in PlayMp3Song" + e.toString());
            }
        }
    }

    public void pauseMp3Song(){
        if(mp3Player.isPlaying()){
            mp3Player.pause();
        }
    }

    public void stopMp3Song(){
        if(mp3Player.isPlaying()){
            mp3Player.stop();
            mp3Player.reset();
//            mp3Player.release(); //Release will free mediaPlayer object, when user repress play button will panic
        }
    }

    public void nextMp3Song(AppData appData){
        if(mp3Player.isPlaying()){
            mp3Player.stop();
            mp3Player.reset();
//            mp3Player.release(); //Release will free mediaPlayer object, when user repress play button will panic
        }
        appData.mp3SongListHandler.playIndex++;
        ArrayList<String> songList = appData.mp3SongListHandler.mp3SongList;
        mp3Player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mp3Player.setDataSource(songList.get(appData.mp3SongListHandler.playIndex));
            mp3Player.prepare();
            mp3Player.start();
        }catch (Exception e){
            Log.d(tag, "----Error occur in PlayMp3Song" + e.toString());
        }
    }

    public void prevMp3Song(AppData appData){
        if(mp3Player.isPlaying()){
            mp3Player.stop();
            mp3Player.reset();
//            mp3Player.release(); //Release will free mediaPlayer object, when user repress play button will panic
        }
        appData.mp3SongListHandler.playIndex--;
        if(appData.mp3SongListHandler.playIndex < 0){
            appData.mp3SongListHandler.playIndex = 0;
        }
        ArrayList<String> songList = appData.mp3SongListHandler.mp3SongList;
        mp3Player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mp3Player.setDataSource(songList.get(appData.mp3SongListHandler.playIndex));
            mp3Player.prepare();
            mp3Player.start();
        }catch (Exception e){
            Log.d(tag, "----Error occur in PlayMp3Song" + e.toString());
        }
    }
}
