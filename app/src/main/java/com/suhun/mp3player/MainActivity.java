package com.suhun.mp3player;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String tag = MainActivity.class.getSimpleName();
    private AppData appData;
    private Button playBtn;
    private TextView songName;
    private Mp3PlayerController mp3Controller;
    private boolean isPlayMp3;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mp3Controller = (Mp3PlayerController) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        if(isSendUserPermissionRequestAboutMp3Player()){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
        }else {
            initMp3Player();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 123){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                initMp3Player();
            }else{
                finish();
            }
        }
    }

    private void initView(){
        playBtn = findViewById(R.id.lid_playBtn);
        songName = findViewById(R.id.lid_songName);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playFun();
            }
        });
    }

    private void initMp3Player(){
        appData = (AppData) getApplication();
        appData.mp3SongListHandler = new Mp3SongListHandler(getContentResolver());
        appData.mp3SongListHandler.genMp3SongList();
        Intent intent = new Intent(this, Mp3PlayerService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    private boolean isSendUserPermissionRequestAboutMp3Player(){
        boolean result = false;

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
            result = true;
        }
        return result;
    }

    public void playFun(){
        if(!isPlayMp3) {
            isPlayMp3 = true;
            playBtn.setText("Pause");
            mp3Controller.playMp3Song(appData);
            songName.setText(appData.mp3SongListHandler.songName);
        }else {
            isPlayMp3 = false;
            playBtn.setText("Play");
//            mp3Controller.pauseMp3Song();
        }

    }

    public void stopFun(View view){
        isPlayMp3 = false;
        playBtn.setText("Play");
        mp3Controller.stopMp3Song();
    }

    public void prevFun(View view){
        isPlayMp3 = true;
        playBtn.setText("Pause");
        mp3Controller.prevMp3Song(appData);
        songName.setText(appData.mp3SongListHandler.songName);
    }

    public void nextFun(View view){
        isPlayMp3 = true;
        playBtn.setText("Pause");
        mp3Controller.nextMp3Song(appData);
        songName.setText(appData.mp3SongListHandler.songName);
    }
}