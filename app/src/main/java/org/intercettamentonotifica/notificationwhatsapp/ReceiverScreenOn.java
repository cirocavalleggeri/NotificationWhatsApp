package org.intercettamentonotifica.notificationwhatsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.intercettamentonotifica.notificationwhatsapp.listnerwhatsapp.ApplicationMain;

public class ReceiverScreenOn extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Log.i("WAT","Screen went OFF");
            Toast.makeText(context, "screen OFF",Toast.LENGTH_LONG).show();
            ApplicationMain.sCreenOn=false;
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            ApplicationMain.sCreenOn=true;
            Log.i("WAT","Screen went ON");
            Toast.makeText(context, "screen ON",Toast.LENGTH_LONG).show();
        }
    }
}