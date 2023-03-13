package org.intercettamentonotifica.notificationwhatsapp.listnerwhatsapp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import android.content.SharedPreferences;
import org.intercettamentonotifica.notificationwhatsapp.Constants;
import org.intercettamentonotifica.notificationwhatsapp.R;

import java.util.Calendar;


public class TimerPickerFragment  extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
        String dato;

        public  void setValue( String dato) {

                this.dato =dato;
        }
@Override
public Dialog onCreateDialog(Bundle savedInstanceState) {
// Use the current time as the default values for the picker

                if (getArguments() != null) {
                       dato = getArguments().getString("dato","");

                }

final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
        DateFormat.is24HourFormat(getActivity()));
        }

public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        if(dato.equals(Constants.ORARIO.ORARIO_DI_PARTENZA)){

        }
        if(dato.equals(Constants.ORARIO.ORARIO_FINALE)){

        }
        }



}