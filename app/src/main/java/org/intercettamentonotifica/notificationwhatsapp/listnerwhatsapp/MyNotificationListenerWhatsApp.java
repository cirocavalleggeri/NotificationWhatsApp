package org.intercettamentonotifica.notificationwhatsapp.listnerwhatsapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import org.intercettamentonotifica.notificationwhatsapp.MainActivity;
import org.intercettamentonotifica.notificationwhatsapp.MyServiceSpeak;
import org.intercettamentonotifica.notificationwhatsapp.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyNotificationListenerWhatsApp extends NotificationListenerService  {

    Context context ;
    //static MyListener myListener ;
    String titleData = "", textData = "" ;


    @Override
    public void onCreate() {
        super .onCreate() ;
        context = getApplicationContext() ;
        Log.d("WAT","nOME DA INTERCETTARE:"+getNomeDaIntercettare());

    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {


        if (sbn.getPackageName().equals("com.whatsapp"))
        // if (!sbn.getPackageName().equals(""))
                 {
            String pack = sbn.getPackageName();
            Bundle extras = sbn.getNotification().extras;
            if (extras.getString("android.title") != null) {
                  titleData = extras.getString("android.title");
            } else {
                 titleData = "No Title";
            }
            if (extras.getCharSequence("android.text") != null) {
                  textData = extras.getCharSequence("android.text").toString();
            } else {
               textData = "No data";
            }


           if (!(pack.equals("android"))){

                Log.d("WAT","MESSAGGIO DA WATSAPP:"+sbn.getPackageName()+":"+titleData+":"+String.valueOf(sbn.getTag()));
               if( titleData.contains( getNomeDaIntercettare())){
                   impostaOrario();
                 // lanciaactivity();// non funziona
                   Log.d("WAT","INTERCETTATO:"+sbn.getPackageName()+":"+titleData+":"+String.valueOf(sbn.getTag()));
                   //imposta allarme
               }

               tiParlo("messaggio da WhatsApp,da parte di:"+titleData+",che dice:"+textData);
           }

        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {

    }

    @Override
    public void onNotificationRankingUpdate(RankingMap rankingMap) {
        super.onNotificationRankingUpdate(rankingMap);
    }



    private void setAlarm(Calendar targetCal){
       // info.setText("\n\n***\n" + "Alarm is set@ " + targetCal.getTime() + "\n" + "***\n");
        Ringtone ringTone;
        int RQS_1 = 1;
        Intent intent = new Intent(getBaseContext(), MyBroadcastReceiverWathapp.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
        Uri uriAlarm = RingtoneManager.getDefaultUri(R.raw.tiromancino);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        ringTone = RingtoneManager.getRingtone(getApplicationContext(), uriAlarm);

    }
    private void impostaOrario(){
        Calendar current = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        cal.set(now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH),
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                10);
        setAlarm(cal);

    }
    String getNomeDaIntercettare(){
        SharedPreferences sharedPref = getSharedPreferences("NOTIFICHE",Context.MODE_PRIVATE);
       String defaultValue = getResources().getString(R.string.default_key);
       String nome = sharedPref.getString(getString(R.string.saved_nome_whatsapp_key), defaultValue);
       return nome.trim();
    }
void lanciaactivity(){
    Intent notifyIntent = new Intent(this, SuonaActivity.class);
// Set the Activity to start in a new, empty task
   /* notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
            | Intent.FLAG_ACTIVITY_CLEAR_TASK);*/
// Create the PendingIntent
    PendingIntent notifyPendingIntent = PendingIntent.getActivity(
            this, 2, notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE
    );
    AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
    alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+5000, notifyPendingIntent);
}
    private void tiParlo(String messaggio_speak){
        Intent i = new Intent(this, MyServiceSpeak.class);
        // Add extras to the bundle
        i.putExtra("messaggio",  messaggio_speak);
        getBaseContext().startService(i);

    }
}
