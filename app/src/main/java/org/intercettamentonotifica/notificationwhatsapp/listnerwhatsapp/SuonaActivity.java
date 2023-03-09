package org.intercettamentonotifica.notificationwhatsapp.listnerwhatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
}