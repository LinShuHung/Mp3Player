package com.suhun.mp3player;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Mp3PlayerService extends Service {
    public Mp3PlayerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}