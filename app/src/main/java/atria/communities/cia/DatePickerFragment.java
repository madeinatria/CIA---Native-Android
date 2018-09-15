package atria.communities.cia;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.fragment.app.DialogFragment;

@SuppressLint("ValidFragment")
public  class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    String from;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
;
        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());


        // Create a new instance of DatePickerDialog and return it
        return datePickerDialog;

    }

    @SuppressLint("ValidFragment")
    public DatePickerFragment(String from) {
        this.from = from;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user




        Date date= new Date(year,month,day);

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YY");
            String fomatedDate = sdf.format(date);


            ((TextView) getActivity().findViewById(R.id.eventtimestamp)).setText(fomatedDate);

            /*
0YY
            if (from.equals("checkin"))
                ((TextView) getActivity().findViewById(R.id.checkindate)).setText(fomatedDate);

            else
                ((TextView) getActivity().findViewById(R.id.checkoutdate)).setText(fomatedDate);
*/


            DialogFragment dialogFragment = new TimePickerNewFragment();
            dialogFragment.show(getFragmentManager(),"timePicker");


        }catch (Exception ex){

        }





        //   Toast.makeText(getContext(), ""+fomatedDate, Toast.LENGTH_SHORT).show();
    }
}