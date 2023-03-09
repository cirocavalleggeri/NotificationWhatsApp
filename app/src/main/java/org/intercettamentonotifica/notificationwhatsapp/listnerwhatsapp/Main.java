package org.intercettamentonotifica.notificationwhatsapp.listnerwhatsapp;

import android.app.Application;
import android.content.Context;

 public class Main extends Application {
     public Context getContext() {
        context=getApplicationContext();
        return context;
    }

    Context context;

}
