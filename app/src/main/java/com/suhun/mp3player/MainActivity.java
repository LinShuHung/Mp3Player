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
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private String tag = MainActivity.class.getSimpleName();
    private AppData appData;
    private Button playBtn;
    private Binder mp3Controller;

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

    }

    public void stopFun(View view){

    }

    public void prevFun(View view){

    }

    public void nextFun(View view){

    }
}