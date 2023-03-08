package org.intercettamentonotifica.notificationwhatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startServiceIntercept();
    }

    private void startServiceIntercept() {
        Intent startIntent = new Intent(MainActivity.this,MyServiceforeground.class);
        startIntent.setAction(Constants.ACTION.START_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            getApplicationContext().startForegroundService(startIntent);
        }else {startService(startIntent);}
    }
}