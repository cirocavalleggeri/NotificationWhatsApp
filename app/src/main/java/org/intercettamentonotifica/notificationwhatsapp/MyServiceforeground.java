package org.intercettamentonotifica.notificationwhatsapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import org.intercettamentonotifica.notificationwhatsapp.listnerwhatsapp.MyNotificationListenerWhatsApp;

public class MyServiceforeground extends Service {
    Context context;
    private boolean bStop = true;
    boolean mStarted = false;
    final static String TAG="WAT";
MyNotificationListenerWhatsApp listnerWhatsapp;
    public MyServiceforeground() {
    }
    @Override
    public void onCreate() {
        super.onCreate();

        context= getApplicationContext();
        Log.d(TAG,"ONcREATE");
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       // throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        if (intent.getAction().equals(Constants.ACTION.START_SERVICE)) {

            if (mStarted) {

            } else {
                mStarted = true;
                 creaNotificaForeground();
                Log.d(TAG,"START_SERVICE");
            }
        }else if (intent.getAction().equals(Constants.ACTION.START_INTERCEPT_MESSAGE)){
            //start intercept
            bStop = false;
            Log.d(TAG,"START_INTERCEPT_MESSAGE");
            listnerWhatsapp=new MyNotificationListenerWhatsApp();
        } else if (intent.getAction().equals(Constants.ACTION.STOP_INTERCEPT_MESSAGE)){
            //stop intercept
            if(bStop=false){

            }
            bStop = true;
            Log.d(TAG,"STOP_INTERCEPT_MESSAGE");
        } else if (intent.getAction().equals(Constants.ACTION.STOP_SERVICE)){
            //stop service
            Log.d(TAG,"STOP_SERVICE");
            mStarted = false;
            bStop = true;
            stopForeground(true);
            stopSelf();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }


        return START_REDELIVER_INTENT;
    }
//fine onStartComand
    private void creaNotificaForeground() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(Constants.NOTIFICATION_ID.LOCATION_UPDATE_CHANNEL_ID,
                    Constants.NOTIFICATION_ID.LOCATION_UPDATE_CHANNEL, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        Intent startRangingIntent = new Intent(MyServiceforeground.this, MyServiceforeground.class);
        startRangingIntent.setAction(Constants.ACTION.START_INTERCEPT_MESSAGE);
        Intent stopRangingIntent = new Intent(MyServiceforeground.this, MyServiceforeground.class);
        stopRangingIntent.setAction(Constants.ACTION.STOP_INTERCEPT_MESSAGE);
        Intent quitRangingIntent = new Intent(MyServiceforeground.this, MyServiceforeground.class);
        quitRangingIntent.setAction(Constants.ACTION.STOP_SERVICE);
        Notification notification = new NotificationCompat.Builder(this, Constants.NOTIFICATION_ID.LOCATION_UPDATE_CHANNEL_ID)
                .setContentTitle("Intercept WhatsApp")
                .setContentText("Detect  Service")
                .setSmallIcon(R.drawable.icons8whatsapp)
                .setChannelId(Constants.NOTIFICATION_ID.LOCATION_UPDATE_CHANNEL_ID)
                //.setContentIntent(pendingIntent)
                .setOngoing(true)
                .addAction(R.drawable.icons8whatsapp, "Start Intercept",
                        PendingIntent.getForegroundService(this, Constants.NOTIFICATION_ID.LOCATION_RANGING_SERVICE, startRangingIntent, 0))
                .addAction(R.drawable.icons8whatsapp, "Stop Intercept",
                        PendingIntent.getForegroundService(this, Constants.NOTIFICATION_ID.LOCATION_RANGING_SERVICE, stopRangingIntent, 0))
                .addAction(R.drawable.icons8whatsapp, "Kill service",
                        PendingIntent.getForegroundService(this, Constants.NOTIFICATION_ID.LOCATION_RANGING_SERVICE, quitRangingIntent, 0))
                .build();
        //.addAction(R.drawable.ic_pin_drop,
        //        "Record Location", pRecordLocationIntent).build();
        startForeground(Constants.NOTIFICATION_ID.LOCATION_RANGING_SERVICE,
                notification);

    }


}