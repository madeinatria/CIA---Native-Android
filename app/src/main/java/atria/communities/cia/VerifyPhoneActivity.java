package atria.communities.cia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class VerifyPhoneActivity extends AppCompatActivity {

    FirebaseFirestore db;


    //These are the objects needed
    //It is the verification id that will be sent to the user
    private String mVerificationId;

    //The edittext to input the code
    private EditText editTextCode;

    //firebase auth object
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);


        db = FirebaseFirestore.getInstance();
        //initializing objects
        mAuth = FirebaseAuth.getInstance();





    /*    AuthCredential credential2= GoogleAuthProvider.getCredential(
              , null);*/
        editTextCode = findViewById(R.id.editTextCode);


        //getting mobile number from the previous activity
        //and sending the verification code to the number
        Intent intent = getIntent();
        String mobile = intent.getStringExtra("mobile");
        sendVerificationCode(mobile);


        //if the automatic sms detection did not work, user can also enter the code manually
        //so adding a click listener to the button
        findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editTextCode.getText().toString().trim();
                if (code.isEmpty() || code.length() < 6) {
                    editTextCode.setError("Enter valid code");
                    editTextCode.requestFocus();
                    return;
                }

                //verifying the code entered manually
                verifyVerificationCode(code);
            }
        });

    }

    //the method is sending verification code
    //the country id is concatenated
    //you can take the country id as user input as well
    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }


    //the callback to detect the verification status
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                editTextCode.setText(code);
                //verifying the code
                verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(VerifyPhoneActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            //storing the verification id that is sent to the user
            mVerificationId = s;
        }
    };


    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }



    private void signInWithPhoneAuthCredential(final PhoneAuthCredential credential) {




        Task<GetTokenResult> getTokenResultTask = mAuth.getCurrentUser().getIdToken(true);
        getTokenResultTask.addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
            @Override
            public void onComplete(@NonNull Task<GetTokenResult> task) {
                final String token;

                try {

                    GetTokenResult account = task.getResult(ApiException.class);
                    token = account.getToken();
                    AuthCredential credentialgoogle = GoogleAuthProvider.getCredential(token, null);
                    givingAuthbro(credentialgoogle);
                   // Toast.makeText(VerifyPhoneActivity.this, ""+account.getToken(), Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();

                    user.updatePhoneNumber(credential);


                    // Create a new user with a first and last name
                    Map<String, Object> userdata = new HashMap<>();
                    userdata.put("uid",user.getUid());
                    userdata.put("dispalyname", user.getDisplayName());
                    userdata.put("email", user.getEmail());
                    userdata.put("phonenumber", user.getPhoneNumber());
                   // userdata.put("photourl",user.getPhotoUrl());



// Add a new document with a generated ID
                    db.collection("users")
                            .add(userdata)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    /*Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());*/
                                    Toast.makeText(VerifyPhoneActivity.this, "Done", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                   /* Log.w(TAG, "Error adding document", e);*/
                                }
                            });



                   /* UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName("Jane Q. User")
                            .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                            .build();

                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                       *//* Log.d(TAG, "User profile updated.");*//*
                                        Toast.makeText(VerifyPhoneActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });*/


                    Toast.makeText(VerifyPhoneActivity.this, ""+user.getPhoneNumber(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(VerifyPhoneActivity.this, BottomNavigationActivity.class);


                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);


                } catch (ApiException e) {
                    e.printStackTrace();
                }



            }
        });

     /*   mAuth.getCurrentUser().linkWithCredential(credentialgoogle[0])
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                           // Log.d(TAG, "linkWithCredential:success");
                            FirebaseUser user = task.getResult().getUser();
                            Toast.makeText(VerifyPhoneActivity.this, "yay"+user.getEmail(), Toast.LENGTH_SHORT).show();

                            //updateUI(user);
                        } else {
                            //Log.w(TAG, "linkWithCredential:failure", task.getException());
                         //   Toast.makeText(AnonymousAuthActivity.this, "Authentication failed.",
                                //    Toast.LENGTH_SHORT).show();
                           // updateUI(null);
                        }

                        // ...
                    }
                });
*/


      /*  mAuth.signInWithCredential(credential)
                .addOnCompleteListener(VerifyPhoneActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //verification successful we will start the profile activity
                            Intent intent = new Intent(VerifyPhoneActivity.this, BottomNavigationActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                        } else {

                            //verification unsuccessful.. display an error message

                            String message = "Somthing is wrong, we will fix it soon...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                            }

                      *//*      Snackbar snackbar = Snackbar.make(findViewById(R.id.parent), message, Snackbar.LENGTH_LONG);
                            snackbar.setAction("Dismiss", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                            snackbar.show();*//*
                        }
                    }
                });*/
    }

    public void givingAuthbro(AuthCredential credentialgoogle ){

        mAuth.getCurrentUser().linkWithCredential(credentialgoogle)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Log.d(TAG, "linkWithCredential:success");
                            FirebaseUser user = task.getResult().getUser();
                          //  updateUI(user);

                            Toast.makeText(VerifyPhoneActivity.this, ""+user.getDisplayName(), Toast.LENGTH_SHORT).show();
                        } else {
                         //   Log.w(TAG, "linkWithCredential:failure", task.getException());
                         //   Toast.makeText(AnonymousAuthActivity.this, "Authentication failed.",
                           //         Toast.LENGTH_SHORT).show();
                         //   updateUI(null);
                        }

                        // ...
                    }
                });


    }

}