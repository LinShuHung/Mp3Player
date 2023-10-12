package com.suhun.mp3player;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

public class Mp3SongListHandler {
    private String tag = Mp3SongListHandler.class.getSimpleName();
    private ContentResolver mp3ContentResolver;
    public ArrayList<String> mp3SongList;
    public int playIndex;

    public Mp3SongListHandler(ContentResolver contentResolver){
        this.mp3ContentResolver = contentResolver;
        mp3SongList = new ArrayList<String>();
        playIndex = 0;
    }

    public void genMp3SongList(){
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        Cursor cursor = mp3ContentResolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()){
            String filepath = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
            if(filepath.endsWith(".mp3")){
                Log.d(tag, "----mp3 file path:" + filepath);
                mp3SongList.add(filepath);
            }
        }
    }
}
