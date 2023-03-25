package org.intercettamentonotifica.notificationwhatsapp.listnerwhatsapp;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.intercettamentonotifica.notificationwhatsapp.MyServiceSpeak;
import org.intercettamentonotifica.notificationwhatsapp.ReceiverScreenOn;

public class ApplicationMain extends Application {
    ReceiverScreenOn mREceiverScreenOn;
    public static boolean sCreenOn;
    @Override
    public void onCreate() {

        super.onCreate();
        mREceiverScreenOn=new ReceiverScreenOn();
        registraScreenOn();
        tiParlo("Buongiorno");
        tiParlo("Buongiorno");
       // tiParlo("Questa app intercetta i messaggi di un singolo utente WhatsApp,registra il nome del contatto");
        dozeMode();
    }



    private void registraScreenOn() {
        IntentFilter screenStateFilter = new IntentFilter();
        screenStateFilter.addAction(Intent.ACTION_SCREEN_ON);
        screenStateFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mREceiverScreenOn, screenStateFilter);


       // unregisterReceiver(mScreenStateReceiver);
    }

    public Context getContext() {
        context=getApplicationContext();
        return context;
    }

    Context context;

    public void dozeMode(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            BroadcastReceiver receiver = new BroadcastReceiver() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override public void onReceive(Context context, Intent intent) {
                    PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);

                    if (pm.isDeviceIdleMode()) {
                        Log.d("WAT", "Vado a dormire");
                        tiParlo("Vado a dormire");
                        // the device is now in doze mode
                    } else {
                        // the device just woke up from doze mode
                        Log.d("WAT","Appena svegliato");}
                        tiParlo("Appena svegliato");
                }
            };

            getApplicationContext().registerReceiver(receiver, new IntentFilter(PowerManager.ACTION_DEVICE_IDLE_MODE_CHANGED));
        }
    }
    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }
    @Override
    public void onTrimMemory(int level) {

        super.onTrimMemory(level);
    }
    @Override
    public void onTerminate() {
        super.onTerminate();

    }

    private void tiParlo(String messaggio_speak){
        Intent i = new Intent(this, MyServiceSpeak.class);
        // Add extras to the bundle
        i.putExtra("messaggio",  messaggio_speak);
        getBaseContext().startService(i);
        
    }
}
