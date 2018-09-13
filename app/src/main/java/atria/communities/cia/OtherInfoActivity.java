package atria.communities.cia;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import atria.communities.cia.User.Usermain;
import atria.communities.cia.User.UserLocalStore;
import atria.communities.cia.network.ApiUtils;
import atria.communities.cia.network.UserService;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class OtherInfoActivity extends AppCompatActivity {


    String value,name,phonenumber,usnnumber,email;

    CheckBox ai,web,mobile,iot,robotics;

    EditText othercommunities;
    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_info);

        final ProgressBar progressBar = findViewById(R.id.progess);
        progressBar.setVisibility(View.GONE);
        getSupportActionBar().hide();

        othercommunities = findViewById(R.id.othercommunities);
        done = findViewById(R.id.done_button);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("basicdata");
            String[] data =  value.split(",");
            name = data[0];
            usnnumber = data[1];
            email = data[2];
            phonenumber = data[3];
           // Toast.makeText(this, ""+name+usnnumber+email+phonenumber, Toast.LENGTH_SHORT).show();

        }




        ai = findViewById(R.id.ai);
        web = findViewById(R.id.web);
        mobile = findViewById(R.id.mobile);
        iot = findViewById(R.id.iot);
        robotics = findViewById(R.id.robotics);





        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String othercomm ="";
                if (ai.isChecked() || web.isChecked() || mobile.isChecked() || iot.isChecked() || robotics.isChecked()){

                    if (!othercommunities.getText().toString().isEmpty()){



                        othercomm =othercommunities.getText().toString();

                        progressBar.setVisibility(View.VISIBLE);

                    }
           String domains = c(ai)+c(web)+c(mobile)+c(iot)+c(robotics);

                    UserService userService = ApiUtils.getUserService();





                   /*retrofit2.Call<Void> call = userService.register(email,name,usnnumber,phonenumber,email,"AI /ML","","Mobile","IOT","Robotics");*/


                    String aiString="",webString="",mobileString="",iotString="",roboticsString="";

                    if (ai.isChecked()){
                        aiString="AI /ML";
                    }
                    if (web.isChecked()){
                        webString="Web";
                    }if (mobile.isChecked()){
                        mobileString="Mobile";
                    }if (iot.isChecked()){
                        iotString="IOT";
                    }if (robotics.isChecked()){
                        roboticsString="Robotics";
                    }





                    retrofit2.Call<Void> call = userService.register(email,name,usnnumber,phonenumber,othercomm,aiString,webString,mobileString,iotString,roboticsString);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(retrofit2.Call<Void> call, Response<Void> response) {
                            Toast.makeText(OtherInfoActivity.this, ""+response.message(), Toast.LENGTH_SHORT).show();

                            if(response.isSuccessful()){

                                //////////////////////////////////////////////
                                Usermain user = new Usermain(name, usnnumber);

                                //user.username = us;

                                //         user.vedorid= Integer.parseInt(resObj.getVendorID());
                                //Toast.makeText(Main2Activity.this, ""+, Toast.LENGTH_SHORT).show();
                                UserLocalStore userLocalStore = new UserLocalStore(getApplicationContext());
                                userLocalStore.storeUserData(user);

                                userLocalStore.setUserLoggedIn(true);
                                //////////////////////////////////////////////


                                Toast.makeText(OtherInfoActivity.this, "Cool Bro - You logged into Awesomeness", Toast.LENGTH_SHORT).show();

                                progressBar.setVisibility(View.GONE);

                                startActivity(new Intent(OtherInfoActivity.this,BottomNavigationActivity.class));



                            }
                        }

                        @Override
                        public void onFailure(retrofit2.Call<Void> call, Throwable t) {

                            Toast.makeText(OtherInfoActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });




                }
                else {

                    Toast.makeText(OtherInfoActivity.this, "Select atleast one domain", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }


    public  String c(CheckBox check){

        if (check.isChecked()){

            return "";
        }
        else {
            return "";
        }


    }


    @Override
    public void finish() {
        super.finish();
        onLeaveThisActivity();
    }

    protected void onLeaveThisActivity() {
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }



    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent); onStartNewActivity();

    }

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        super.startActivity(intent, options);
        onStartNewActivity();
    }

    protected void onStartNewActivity() {
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

}
