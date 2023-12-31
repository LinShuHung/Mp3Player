package com.suhun.mp3player;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class Mp3PlayerService extends Service {
    private String tag = Mp3PlayerService.class.getSimpleName();
    private MediaPlayer mediaPlayer;
    private Binder mp3PlayerController;
    private AppData appData;
    public Mp3PlayerService() {
        mediaPlayer = new MediaPlayer();
        mp3PlayerController = new Mp3PlayerController(mediaPlayer);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(tag, "----Mp3PlayerService onBind----");
        return mp3PlayerController;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(tag, "----Mp3PlayerService onUnbind----");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        Log.d(tag, "----Mp3PlayerService onCreate----");
        super.onCreate();
        appData = (AppData) getApplication();
        appData.mediaPlayer = mediaPlayer;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(tag, "----Mp3PlayerService onStartCommand----");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(tag, "----Mp3PlayerService onDestroy----");
        super.onDestroy();
    }
}