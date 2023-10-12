package com.suhun.mp3player;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class Mp3PlayerService extends Service {
    private String tag = Mp3PlayerService.class.getSimpleName();
    public Mp3PlayerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(tag, "----Mp3PlayerService onBind----");
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
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