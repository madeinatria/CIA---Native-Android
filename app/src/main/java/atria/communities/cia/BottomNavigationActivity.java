package atria.communities.cia;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import atria.communities.cia.fragments.HomeFinalFragment;
import atria.communities.cia.fragments.HomeFragmentv3;
import atria.communities.cia.fragments.ProfileFragmentv;
import atria.communities.cia.fragments.ScheduleFragmentv;

import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class BottomNavigationActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private FirebaseAnalytics mFirebaseAnalytics;

    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        Toast.makeText(this, ""+user.getDisplayName(), Toast.LENGTH_SHORT).show();




        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // BottomNavigationViewHelper.removeShiftMode(navigation);


/*
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(maintitle);*/


        ////////////////

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragmentv3()).commit();
        navigationView.getMenu().getItem(0).setChecked(true);
        if (getSupportFragmentManager().findFragmentById(R.id.frame_layout)==null){
            navigationView.getMenu().getItem(0).setChecked(true);

            ///



        }


    }

    private void openFragment(final Fragment fragment)   {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            HomeFinalFragment frg1 = new HomeFinalFragment();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    openFragment(frg1);
                    break;

                    case R.id.navigation_dashboard:

                   // if (checkifno()) {
                        ScheduleFragmentv noBookingsFragment = new ScheduleFragmentv();
                        openFragment(noBookingsFragment);

                  /*  }
                    else {
                        BookingsFragment bookingsFragment = new BookingsFragment();
                        openFragment(bookingsFragment);

                    }*/
                    break;
                case R.id.navigation_notifications:
                    ProfileFragmentv favFragment = new ProfileFragmentv();
                    openFragment(favFragment);
                    break;
                /*case R.id.navigation_profile:
                  *//*  ProfileFragment frg12 = new ProfileFragment ();
                    openFragment(frg12);*//*
                    startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                    break;*/
                default:
                    openFragment(frg1);


                    String sania = "";
                    break;
            }

            return true;
        }
    };



    @Override
    public void finish() {
        super.finish();
        onLeaveThisActivity();
    }

    protected void onLeaveThisActivity() {
       // overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
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
      //  overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    public Boolean checkifno(){

       /* userLocalStore = new UserLocalStore(getApplicationContext());
        if (userLocalStore.getLoggedInUser()==null){
            return true;
        }*/
        return false;
    }

}
