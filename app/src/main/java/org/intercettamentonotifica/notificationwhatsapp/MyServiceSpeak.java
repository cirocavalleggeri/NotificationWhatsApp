package org.intercettamentonotifica.notificationwhatsapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

public class MyServiceSpeak extends Service implements TextToSpeech.OnInitListener, TextToSpeech.OnUtteranceCompletedListener {
    private static final String TAG = "SERVICESPEAK";

    public MyServiceSpeak() {
    }
    private TextToSpeech mTts;
    private String spokenText=""
            ;

    @Override
    public void onCreate() {

        mTts = new TextToSpeech(this, this);
        // Inside OnCreate Method


    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            // int result = mTts.setLanguage(Locale.ITALY);
            int result = mTts.setLanguage(Locale.getDefault());
            if (result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED) {

                mTts.speak(spokenText, TextToSpeech.QUEUE_FLUSH, null);
            }

        }
    }

    @Override
    public void onUtteranceCompleted(String uttId) {
        stopSelf();
    }

    @Override
    public void onDestroy() {
        if (mTts != null) {
            mTts.stop();
            mTts.shutdown();
        }
        super.onDestroy();
    }
    public int onStartCommand(Intent intent, int flags, int startId) {

        String action = intent.getAction(); // This is null when started from broadcast receiver
        String extra = intent.getStringExtra("messaggio"); // and so is this

        // int result = mTts.setLanguage(Locale.ITALY);
        Log.i(TAG, "MESSAGGIO RICEVUTO "+extra);
        Toast.makeText(this,"MESSAGGIO RICEVUTO "+extra, Toast.LENGTH_LONG).show();
       // aumentaAudio();
        mTts.speak(extra, TextToSpeech.QUEUE_FLUSH, null);

        mTts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onDone(String utteranceId) {
                // Log.d("MainActivity", "TTS finished");
              //  Utility.Scrivichiavefile(getApplicationContext(),"APPFIGLIO","stoparlando",false);
                Log.d("SERVICESPEAK", "TTS finished");
            }

            @Override
            public void onError(String utteranceId) {
            }

            @Override
            public void onStart(String utteranceId) {
            }
        });

        return START_NOT_STICKY;
    }
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
    void aumentaAudio(){
        AudioManager mAudioManager =(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        //  mAudioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, 0);
        } else {
            mAudioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
        }
    }
}