package org.intercettamentonotifica.notificationwhatsapp.listnerwhatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.intercettamentonotifica.notificationwhatsapp.Constants;
import org.intercettamentonotifica.notificationwhatsapp.R;

public class RegistraActivity extends AppCompatActivity {
EditText edit_nome_whatsapp;
TextView nomeREgistrato;
Button button_registranome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registra);
        nomeREgistrato=findViewById(R.id.id_nominativo_registrato);
        nomeREgistrato.setText(getNomeDaIntercettare().toString());
        edit_nome_whatsapp=findViewById(R.id.id_Edit_nominativo_whats_app);
        button_registranome=findViewById(R.id.id_BTN_registra_nome_per_il_servizio);
        button_registranome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String nominativo= edit_nome_whatsapp.getText().toString();
               registraNomeWhatsApp(nominativo);
                nomeREgistrato.setText(getNomeDaIntercettare().toString());
            }
        });
        showScreenUP();
    }

    private void showScreenUP() {

    }

    void registraNomeWhatsApp(String nome){
        SharedPreferences sharedPref = getSharedPreferences("NOTIFICHE",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.saved_nome_whatsapp_key), nome);
        editor.apply();
        Toast.makeText(this,"REgistrato",Toast.LENGTH_LONG).show();
        finish();
    }
    String getNomeDaIntercettare(){
        SharedPreferences sharedPref = getSharedPreferences("NOTIFICHE",Context.MODE_PRIVATE);
        String defaultValue = getResources().getString(R.string.default_key);
        String nome = sharedPref.getString(getString(R.string.saved_nome_whatsapp_key), defaultValue);
        return nome.trim();
    }
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimerPickerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("dato", Constants.ORARIO.ORARIO_DI_PARTENZA);
        newFragment.setArguments(bundle);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
    public void showTimePickerDialogFinale(View v) {
        DialogFragment newFragment = new TimerPickerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("dato", Constants.ORARIO.ORARIO_FINALE);
        newFragment.setArguments(bundle);
        newFragment.show(getSupportFragmentManager(), "timePickerfinale");
    }
}