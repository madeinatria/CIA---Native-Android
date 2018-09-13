package atria.communities.cia;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasicDetailsActivity extends AppCompatActivity {

    EditText name,usnnumber,contactnumber,email;

    String names,usnnumbers,contactnumbers,emails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_details);


        getSupportActionBar().hide();

        name = findViewById(R.id.name);
        usnnumber = findViewById(R.id.usnnumber);
        contactnumber = findViewById(R.id.contactnumber);
        email = findViewById(R.id.email);








        Button nextbutton = findViewById(R.id.next_button);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Intent i = new Intent(BasicDetailsActivity.this,OtherInfoActivity.class);
                names = name.getText().toString();
                usnnumbers = usnnumber.getText().toString();
                contactnumbers = contactnumber.getText().toString();








                emails = email.getText().toString();
                String data = ""+names+","+usnnumbers+","+emails+","+contactnumbers;
                i.putExtra("basicdata",data);


                if (!(names.isEmpty() || usnnumbers.isEmpty() || contactnumbers.isEmpty() || emails.isEmpty())){
                    if ((usnnumber.getText().toString().length() >9)){
                        if (isEmailValid(emails)){
                            if ((contactnumber.getText().toString().length()>9)){
                                startActivity(i);
                            }
                            else {
                                Toast.makeText(BasicDetailsActivity.this, "Check Contact Number", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(BasicDetailsActivity.this, "Check Your Mail ID Bruh", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else {
                        Toast.makeText(BasicDetailsActivity.this, "Check USN Number", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(BasicDetailsActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                }


               // startActivity(new Intent(BasicDetailsActivity.this,OtherInfoActivity.class));
            }
        });
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


    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}
