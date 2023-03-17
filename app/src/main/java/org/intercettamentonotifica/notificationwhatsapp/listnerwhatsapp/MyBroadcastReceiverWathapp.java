package org.intercettamentonotifica.notificationwhatsapp.listnerwhatsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

import org.intercettamentonotifica.notificationwhatsapp.R;


public class MyBroadcastReceiverWathapp extends BroadcastReceiver {
MediaPlayer mp;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        mp= MediaPlayer.create(context, R.raw.tiromancino  );
        mp.start();


        /*Log.v(this.toString(), "Inside onReceive.");
        Intent startActivityIntent = new Intent(context, SuonaActivity.class);
        startActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        Log.v(this.toString(), "Starting new activity.");
        context.getApplicationContext().startActivity(startActivityIntent);
        Log.v(this.toString(), "Activity should have started.");
        Toast.makeText(context, "Alarm...di pending intent.", Toast.LENGTH_LONG).show();*/
    }
}