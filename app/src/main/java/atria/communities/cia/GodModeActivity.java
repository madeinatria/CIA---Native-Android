package atria.communities.cia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class GodModeActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore db;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<EventModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_god_mode);
        data = new ArrayList<EventModel>();
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();


        LinearLayout newevent = findViewById(R.id.newevent);
        newevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GodModeActivity.this,NewEventActivity.class));
            }
        });


        db.collection("events").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //    Log.d(TAG, document.getId() + " => " + document.getData());
                        //  data = data+ document.getId() + " => " + document.getData();


                        //public EventModel(String eventid, String eventtitle, String eventshortdescription, String domainid,
                        // String eventlocation, String eventhost, String eventdescription,
                        // String eventduration, String eventprerequisite, String eventbenefits,
                        // String eventcontactnumber, Timestamp eventtimestamp) {
                        data.add(new EventModel(document.getString("eventid"),
                                document.getString("eventtitle"),
                                document.getString("eventshortdescription"),
                                document.getString("domainid"),
                                document.getString("eventlocation"),
                                document.getString("eventhost"),
                                document.getString("eventdescription"),
                                document.getString("eventduration"),
                                document.getString("eventprerequisite"),
                                document.getString("eventbenefi" +
                                        "ts"),
                                document.getString("eventcontactnumber"),
                                document.getTimestamp("eventtimestamp")));




                        //Log.d(TAG, document.getId() + " => " + i + document.getString("problem")+" "+document.getString("Reported to") +" " +document.getString("village"));

                        //

                    }




                    recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
                    recyclerView.setHasFixedSize(true);

                    layoutManager = new LinearLayoutManager(GodModeActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    Context mcontext = GodModeActivity.this;
                    adapter = new EventAdapter(data, mcontext);
                    recyclerView.setAdapter(adapter);



                } else {
                    //  Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });







    }
}
