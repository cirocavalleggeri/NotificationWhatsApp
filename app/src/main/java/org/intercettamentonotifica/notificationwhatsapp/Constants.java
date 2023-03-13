package org.intercettamentonotifica.notificationwhatsapp;

public class Constants {
    public interface ACTION {
        String START_SERVICE="org.intercettamentonotifica.notificationwhatsapp.startservice";
        String START_INTERCEPT_MESSAGE="org.intercettamentonotifica.notificationwhatsapp.startINTERCEPT";
        String STOP_INTERCEPT_MESSAGE="org.intercettamentonotifica.notificationwhatsapp.stopINTERCEPT";
        String STOP_SERVICE="org.intercettamentonotifica.notificationwhatsapp.stopservice";
    }

    public interface NOTIFICATION_ID {
        int LOCATION_RANGING_SERVICE = 1;
        String LOCATION_UPDATE_CHANNEL_ID = "org.intercettamentonotifica.notificationwhatsapp.channelID";
        String LOCATION_UPDATE_CHANNEL = "location channel";
    }
    public interface ORARIO {
        String INTERVALLO_ORARIO="org.intercettamentonotifica.notificationwhatsapp.intervalloorario";
        String ORARIO_DI_PARTENZA="org.intercettamentonotifica.notificationwhatsapp.orariodipartenza";
        String ORARIO_FINALE= "org.intercettamentonotifica.notificationwhatsapp.orariofinale";

    }
}
