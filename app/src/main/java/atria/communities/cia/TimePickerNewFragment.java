package atria.communities.cia;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.telecom.Call;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.Timestamp;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.fragment.app.DialogFragment;

@SuppressLint("ValidFragment")
public class TimePickerNewFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    Calendar calendar = Calendar.getInstance();
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), this, hourOfDay, minute, true);

        return timePickerDialog;
    }

    @SuppressLint("ValidFragment")
    public TimePickerNewFragment() {

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);


        Time time = new Time(calendar.getTime().getTime());
        //SimpleDateFormat sdf = new SimpleDateFormat(" hh:mm");
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        String fomatedDate = sdf.format(time);

    //    Toast.makeText(getContext(), ""+fomatedDate, Toast.LENGTH_SHORT).show();


        ((TextView) getActivity().findViewById(R.id.eventtimestamp)).append(" "+fomatedDate);












        //do some stuff
    }
}