package com.example.wortspiel.Model;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wortspiel.Interfaces.Callback;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

public class Utility {

    private static final String TAG = "Utility";

    private static boolean haveNetworkConnection(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        //return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();

    }

    public static void getPronunciationUrl(Context context, Button btn, Callback callback){

        if (isNetworkAvailable(context.getApplicationContext())){
            Log.d(TAG, "getPronunciationUrl: "+btn.getText().toString());
            String word = btn.getText().toString();
            if (word.contains(" ")) {
                // Replace space with hyphen
                word = word.replace(" ", "-");
            }
            new NetworkTask(
                    word,
                    src->{
                        System.out.println(src);
                        //playPronunciation(src.toString());

                        callback.onCallback(src);

                    }).execute();

        } else {
            Snackbar snackbar = Snackbar
                    .make(btn, "no internet connection.\nconnect to internet to hear pronunciation!", Snackbar.LENGTH_LONG)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });
            snackbar.setActionTextColor(Color.RED);
            View sbView = snackbar.getView();
            /*TextView textView = (TextView) sbView.findViewById(android.support.v4.app.);
            textView.setTextColor(Color.YELLOW);*/
            snackbar.show();
        }


    }

    public static void playPronunciation(String src){
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mediaPlayer.setDataSource(src);
            // below line is use to prepare
            // and start our media player.
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
