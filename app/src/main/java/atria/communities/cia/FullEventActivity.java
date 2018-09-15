package atria.communities.cia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FullEventActivity extends AppCompatActivity {

    String value;
    EventModel event;
    Button register;

    FirebaseAuth auth;
    FirebaseFirestore db;
    TextView eventid
            ,eventtitle
            ,eventshortdescription
            ,domainid
            ,eventlocation
            ,eventhost
            ,eventdescription
            ,eventduration
            ,eventprerequisite
            ,eventbenefits
            ,eventcontactnumber,eventtimestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_event);


        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //Toast.makeText(this, ""+sharedpreferences.getString("checkout",null), Toast.LENGTH_LONG).show();

            value = extras.getString("selectedEvent");
            Gson gson = new Gson();
            event = gson.fromJson(value,EventModel.class);
            //textView.setText(datum.getHotelName());
            //  Toast.makeText(this, ""+datum.getAreaName(), Toast.LENGTH_SHORT).show();
           // Glide.with(ScrollingActivity.this).load("https://jiwirooms.com/app/hotel_logo/"+datum.getHotelLogo()).into(imageView);
        }
        final FirebaseUser firebaseUser= auth.getCurrentUser();

       /* db.collection("eventregistration").document(event.getDomainid()).collection(event.getEventtitle()).document(firebaseUser.getUid())*/
        register= findViewById(R.id.register);

       //////                    Works properly
        final DocumentReference dbref =  db.collection("eventregistration").document(event.getDomainid()).
                collection(event.getEventtitle()).document(firebaseUser.getUid());

        db.collection("eventregistration")
                .document(event.getDomainid()).collection(event.getEventtitle())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                 @Override
                                                 public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                     /// Toast.makeText(FullEventActivity.this, ""+task.getResult().getDocuments()
                                                     //      , Toast.LENGTH_SHORT).show();

                                                     for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {

                                                         if (queryDocumentSnapshot.getId().compareTo(firebaseUser.getUid()) == 0) {

                                                             Toast.makeText(FullEventActivity.this, "already Registered", Toast.LENGTH_SHORT).show();
                                                             register.setBackgroundColor(Color.GREEN);

                                                         }
                                                         else{

                                                         }
                                                         //  Toast.makeText(FullEventActivity.this, ""+queryDocumentSnapshot.getId(), Toast.LENGTH_SHORT).show();
                                                     }
                                                 }

                                             });



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


/*
                                 Works properly
               final DocumentReference dbref =  db.collection("eventregistration").document(event.getDomainid()).
                        collection(event.getEventtitle()).document(firebaseUser.getUid());

                db.collection("eventregistration")
                        .document(event.getDomainid()).collection(event.getEventtitle())
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                       /// Toast.makeText(FullEventActivity.this, ""+task.getResult().getDocuments()
                          //      , Toast.LENGTH_SHORT).show();

                        for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){

                            if (queryDocumentSnapshot.getId().compareTo(firebaseUser.getUid()) == 0){

                                Toast.makeText(FullEventActivity.this, "already Registered", Toast.LENGTH_SHORT).show();

                            }
                            //  Toast.makeText(FullEventActivity.this, ""+queryDocumentSnapshot.getId(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                */













                   Map<String, Object> user = new HashMap<>();
                   user.put("uid", firebaseUser.getUid());
                   user.put("displayname", firebaseUser.getDisplayName());
                   user.put("phonenumber", firebaseUser.getPhoneNumber());
                   user.put("email",firebaseUser.getEmail());
                   db.collection(
                           "eventregistration").
                           document(event.getDomainid()).
                           collection(event.getEventtitle()).
                           document(firebaseUser.getUid()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if (task.isSuccessful()){
                               register.setText("Registered Yo");
                               register.setBackgroundColor(Color.GREEN);
                           Toast.makeText(FullEventActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                           }
                       }
                   });



            }
        });



       eventtitle = findViewById(R.id.eventtitle);
        eventtimestamp = findViewById(R.id.eventtimestamp);
        eventlocation = findViewById(R.id.eventlocation);
        eventhost = findViewById(R.id.eventhost);
        eventdescription = findViewById(R.id.eventdescription);
        eventduration = findViewById(R.id.eventduration);
        eventprerequisite = findViewById(R.id.eventprerequsites);
        eventbenefits = findViewById(R.id.eventbenefits);
        eventcontactnumber = findViewById(R.id.eventcontactnumber);
        domainid = findViewById(R.id.eventdoaminid);

        eventtitle.setText(event.getEventtitle());


        Date date = event.getEventtimestamp().toDate();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm  dd-MM-YY");
        String fomatedDate = sdf.format(date);



        eventtimestamp.setText("Time : "+fomatedDate);



        eventlocation.setText("Location : "+event.getEventlocation());
        eventhost.setText("Hosted by : "+event.getEventhost());



        eventdescription.setText(event.getEventdescription());

        eventdescription.setMovementMethod(LinkMovementMethod.getInstance());
        Linkify.addLinks(eventdescription, Linkify.ALL);
        eventduration.setText(event.getEventduration());
        eventprerequisite.setText(event.getEventprerequisite());
        eventbenefits.setText(event.getEventbenefits());
        eventcontactnumber.setText(event.getEventcontactnumber());

        String defaultInputText[] = getResources().getStringArray(R.array.propertytype);
        Toast.makeText(this, ""+event.getDomainid()+"\n"+defaultInputText[Integer.parseInt(event.getDomainid())-1], Toast.LENGTH_SHORT).show();
        domainid.setText("Domain : "+defaultInputText[Integer.parseInt(event.getDomainid())-1]);



    }





}
