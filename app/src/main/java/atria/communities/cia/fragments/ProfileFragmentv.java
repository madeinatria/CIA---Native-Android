package atria.communities.cia.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import atria.communities.cia.R;
import atria.communities.cia.ReportAdapter;
import atria.communities.cia.ReportModel;
import atria.communities.cia.VerifyPhoneActivity;

import static android.app.Activity.RESULT_OK;
import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * Created by suryamurugan on 4/4/18.
 */

public class ProfileFragmentv extends Fragment implements AdapterView.OnItemSelectedListener {

    // Access a Cloud Firestore instance from your Activity

    FirebaseFirestore db;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<ReportModel> data;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile,null);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        data = new ArrayList<ReportModel>();

        db = FirebaseFirestore.getInstance();


        // Toast.makeText(VerifyPhoneActivity.this, ""+account.getToken(), Toast.LENGTH_SHORT).show();
        FirebaseAuth mAuth;

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

       // user.updatePhoneNumber(credential);


        // Create a new user with a first and last name
        Map<String, Object> userdata = new HashMap<>();
        userdata.put("uid",user.getUid());
        userdata.put("dispalyname", user.getDisplayName());
        userdata.put("email", user.getEmail());
        userdata.put("phonenumber", user.getPhoneNumber());
       // userdata.put("photourl",user.getPhotoUrl());




// Add a new document with a generated ID
        db.collection("users")
                .document(user.getUid()).set(userdata).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Toast.makeText(getContext(), "added", Toast.LENGTH_SHORT).show();

            }
        });


        db.collection("events").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                String data2="";
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                    //    Log.d(TAG, document.getId() + " => " + document.getData());
                          //  data = data+ document.getId() + " => " + document.getData();



                        data.add(new ReportModel("", document.getString("title"),document.getString("content"),document.getString("timestamp")));

   //Log.d(TAG, document.getId() + " => " + i + document.getString("problem")+" "+document.getString("Reported to") +" " +document.getString("village"));

                   //

                    }




                    recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
                    recyclerView.setHasFixedSize(true);

                    layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    Context mcontext = getContext();
                    adapter = new ReportAdapter(data, mcontext);
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
