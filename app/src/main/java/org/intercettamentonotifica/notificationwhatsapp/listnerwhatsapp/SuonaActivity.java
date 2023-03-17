package org.intercettamentonotifica.notificationwhatsapp.listnerwhatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.intercettamentonotifica.notificationwhatsapp.MainActivity;
import org.intercettamentonotifica.notificationwhatsapp.R;

public class SuonaActivity extends AppCompatActivity {
MediaPlayer mp;

Button fermaMusicaBottone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suona);
        mp= MediaPlayer.create(this, R.raw.tiromancino  );
        fermaMusicaBottone=findViewById(R.id.id_suonamusica);

        fermaMusicaBottone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp!=null && mp.isPlaying()){
                    mp.stop();
                    lanciaactivity();
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);


                }
            }
        });
        suonaAvviso();
    }

    private void suonaAvviso() {
        // an Intent broadcast.
        mp.start();
        Toast.makeText(this, "Alarm........", Toast.LENGTH_LONG).show();
    }
    void lanciaactivity(){
        Intent notifyIntent = new Intent(this, MainActivity.class);
// Set the Activity to start in a new, empty task
   /* notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
            | Intent.FLAG_ACTIVITY_CLEAR_TASK);*/
// Create the PendingIntent
        PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                this, 2, notifyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE
        );
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+5*60000, notifyPendingIntent);
    }
}