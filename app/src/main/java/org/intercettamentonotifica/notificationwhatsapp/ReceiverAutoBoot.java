package org.intercettamentonotifica.notificationwhatsapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import org.intercettamentonotifica.notificationwhatsapp.MainActivity;

public class ReceiverAutoBoot extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.Q){
               fai_ripartire_applicazione(context);
                Log.d("BOOT","  restartApp(context)" );

        }
        if ((intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED) )||(intent.getAction().equals(Intent.ACTION_LOCKED_BOOT_COMPLETED) )){
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }

    }
    private void fai_ripartire_applicazione( Context mContext) {
        Log.d("BOOT"," App riparte dopo 15 secondi cambiare i secondi se sono più App che devono ripartire");
        Log.d("BOOT"," Importante!!!Da impostazioni del telefono autorizzare questa app all riavvio");
        try {
            long restartTime = 1000*10;
            Intent intents = mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());//qui puoi mettere anche il pacchetto di un'altra app
            PendingIntent restartIntent = PendingIntent.getActivity(mContext, 0, intents, PendingIntent.FLAG_ONE_SHOT);
            AlarmManager mgr = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mgr.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + restartTime, restartIntent);

            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                //qui non arriverà mai ma lo metto per completezza
                mgr.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + restartTime, restartIntent);
            }
        } catch (Exception e) {
            Log.e("BOOT", e.getMessage());
        }
    }
}
