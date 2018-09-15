package atria.communities.cia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewEventActivity extends AppCompatActivity {

    EditText eventid
            ,eventtitle
            ,eventshortdescription
            ,domainid
            ,eventlocation
            ,eventhost
            ,eventdescription
            ,eventduration
            ,eventprerequisite
            ,eventbenefits
            ,eventcontactnumber;

    TextView eventtimestamp;


    String domainId;
    Button saveorupdate;


    FirebaseFirestore db ;
    FirebaseAuth auth;

    Spinner spinner;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        eventtimestamp = findViewById(R.id.eventtimestamp);

        saveorupdate = findViewById(R.id.saveorupdate);

        db = FirebaseFirestore.getInstance();

        auth = FirebaseAuth.getInstance();

        spinner = findViewById(R.id.spinneritem);



        final ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.propertytype, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter2);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                domainId= ""+(adapterView.getSelectedItemId()+1);
                Toast.makeText(NewEventActivity.this, ""+domainId, Toast.LENGTH_SHORT).show();



               /* roomno = adapterView.getSelectedItemPosition();
                Toast.makeText(ScrollingActivity.this, ""+roomno, Toast.LENGTH_SHORT).show();

                updateRack(datum.getHotelsId(),chechindate.getText().toString(),checkoutdate.getText().toString(),
                        "acsingle_rooms",""+guestsno,""+roomno,""+childrenno,"","","");*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        eventtitle = findViewById(R.id.eventtitle);
     //   eventtimestamp = findViewById(R.id.eventtimestamp);
        eventlocation = findViewById(R.id.eventlocation);
        eventhost = findViewById(R.id.eventhost);
        eventdescription = findViewById(R.id.eventdescription);
        eventduration = findViewById(R.id.eventduration);
        eventprerequisite = findViewById(R.id.eventprerequsites);
        eventbenefits = findViewById(R.id.eventbenefits);
        eventcontactnumber = findViewById(R.id.eventcontactnumber);
 //       domainid = findViewById(R.id.eventdoaminid);

        final TableRow date = findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        saveorupdate.setOnClickListener(new View.OnClickListener() {


            Timestamp timestamp = new Timestamp(new Date());





            @Override
            public void onClick(View view) {
                try{
                /*    EventModel eventModel =
                            new EventModel("",eventtitle.getText().toString(),
                                    eventshortdescription.getText().toString(),"",eventlocation.getText().toString(),
                                    eventhost.getText().toString(),eventdescription.getText().toString(),eventduration.getText().toString(),eventprerequisite.getText().toString(),
                                    eventbenefits.getText().toString(),eventcontactnumber.getText().toString(),timestamp);
*/

                    EventModel eventModel =
                            new EventModel(eventtitle.getText().toString()+auth.getCurrentUser().getUid(),eventtitle.getText().toString(),"ec",domainId,
                                    eventlocation.getText().toString(),eventhost.getText().toString(),
                                    eventdescription.getText().toString(),eventduration.getText().toString(),
                                    eventprerequisite.getText().toString(),eventbenefits.getText().toString()
                                    ,eventcontactnumber.getText().toString(),timestamp);
                    db.collection("events")
                            .document(eventtitle.getText().toString()).
                            set(eventModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {


                            Toast.makeText(NewEventActivity.this, "Done", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });


                }

                catch (Exception e){
                    Toast.makeText(NewEventActivity.this, "Please check all the Fields"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
/*


 /*alendar c = Calendar.getInstance();
                Date date= new Date(c.getTimeInMillis());

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-20YY");
                String fomatedDate = sdf.format(date);*/


 /*   DialogFragment newFragment = new DatePickerFragment("checkin");
                newFragment.show(getSupportFragmentManager(), "datePicker");


                        String str_date= eventtimestamp.getText().toString();

                        DateFormat formatter = new SimpleDateFormat("dd-MM-YY hh:mm");
                        Date date = null;
                        try {
                        date = (Date)formatter.parse(str_date);
                        java.sql.Timestamp timestamp1 = new java.sql.Timestamp(date.getTime());
                        Date newdate = new Date(timestamp1.getTime());
                        firebaseTime[0] = new Timestamp(newdate);*/
                    /*          Date time2 = firebaseTime.toDate();
                                SimpleDateFormat sdf2 =  new SimpleDateFormat("dd-MM-YY hh:mm");
                                String fomatedDate2 = sdf2.format(time2);
                                   ///////////////
            Toast.makeText(getContext(), ""+fomatedDate2, Toast.LENGTH_SHORT).show();
*/


               //         } catch (ParseException e) {
                        // Toast.makeText(NewEventActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
              //          }

