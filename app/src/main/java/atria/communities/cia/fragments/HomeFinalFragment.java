package atria.communities.cia.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.JsonObject;

import java.sql.Timestamp;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import atria.communities.cia.EventAdapter;
import atria.communities.cia.EventModel;
import atria.communities.cia.R;
import atria.communities.cia.ReportAdapter;
import atria.communities.cia.ReportModel;

import static android.app.Activity.RESULT_OK;

/**
 * Created by suryamurugan on 4/4/18.
 */

public class HomeFinalFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    ProgressBar progressBar;


    FirebaseFirestore db;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<EventModel> data;

    FirebaseAuth auth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_final_fragmentv3,null);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progressbar);

        progressBar.setVisibility(View.VISIBLE);
        data = new ArrayList<EventModel>();

        db = FirebaseFirestore.getInstance();

        FirebaseMessaging.getInstance().subscribeToTopic("all");


        // Toast.makeText(VerifyPhoneActivity.this, ""+account.getToken(), Toast.LENGTH_SHORT).show();
        FirebaseAuth mAuth;

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        TextView displaname;
        displaname = view.findViewById(R.id.displayname);
        displaname.setText("Hi, "+user.getDisplayName());

        final JsonObject jsonObject = new JsonObject();



    /*    db.collection("users").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                Toast.makeText(getContext(), ""+task.getResult().getData().get("Register"), Toast.LENGTH_SHORT).show();
            }
        });
*/

        db.collection("events").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                String data2="";
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

                        progressBar.setVisibility(View.INVISIBLE);

                    }




                    recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
                    recyclerView.setHasFixedSize(true);

                    layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    Context mcontext = getContext();
                    adapter = new EventAdapter(data, mcontext);
                    recyclerView.setAdapter(adapter);



                } else {
                    //  Log.w(TAG, "Error getting documents.", task.getException());
                }
                // Toast.makeText(getContext(), data, Toast.LENGTH_SHORT).show();
            }
        });




    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        onStartNewActivity();

    }

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        super.startActivity(intent, options);
        onStartNewActivity();
    }


    protected void onLeaveThisActivity() {
       // getActivity().overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    protected void onStartNewActivity() {
       // getActivity().overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK){
        //    Toast.makeText(getContext(), ""+data.getExtras().getString("selectedCity"), Toast.LENGTH_SHORT).show();
           // city = data.getExtras().getString("selectedCity");
        }


    }
}
