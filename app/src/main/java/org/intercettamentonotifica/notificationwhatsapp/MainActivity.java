package org.intercettamentonotifica.notificationwhatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.intercettamentonotifica.notificationwhatsapp.listnerwhatsapp.RegistraActivity;

public class MainActivity extends AppCompatActivity {
Button nominativoEsattoWhatsApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nominativoEsattoWhatsApp=findViewById(R.id.id_Btn_nominativo_esatto_whatsapp);
        nominativoEsattoWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, RegistraActivity.class);
                startActivity(intent);
            }
        });
        startServiceIntercept();
        abilitaRicezioneNotifiche();
		 ignoraBatteria();
    }

    private void startServiceIntercept() {
        Intent startIntent = new Intent(MainActivity.this,MyServiceforeground.class);
        startIntent.setAction(Constants.ACTION.START_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            getApplicationContext().startForegroundService(startIntent);
        }else {startService(startIntent);}
    }
    void abilitaRicezioneNotifiche(){
        Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        startActivity(intent);
    }

    protected  boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    public void onBackPressed() {
   /*    Scrivi qui il tuo codice prima di uscire
        }*/
        if(isNetworkAvailable()){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ultimaprovaprimadi.altervista.org/le-mie-app-android/"));
            startActivity(browserIntent);

        }

        super.onBackPressed();

    }
	 private final void ignoraBatteria() {
        if ( Build.VERSION.SDK_INT >= 23) {
            Intent intent = new Intent();
            String packageName = getPackageName();
            if (!((PowerManager) getSystemService("power")).isIgnoringBatteryOptimizations(packageName)) {
                intent.setAction("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
                intent.setData(Uri.parse("package:" + packageName));
                startActivity(intent);
            }

        }
    }
}