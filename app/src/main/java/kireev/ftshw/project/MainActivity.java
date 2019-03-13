package kireev.ftshw.project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import kireev.ftshw.project.Courses.CoursesFragment;
import kireev.ftshw.project.Courses.GradesListActivity;
import kireev.ftshw.project.Events.EventsFragment;
import kireev.ftshw.project.Profile.ProfileEditFragment;
import kireev.ftshw.project.Profile.ProfileFragment;



public class MainActivity extends AppCompatActivity
        implements ProfileFragment.OnProfileFragmentListener {

    public AlertDialog.Builder ad;
    public AlertDialog.Builder adEmptyFields;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            navigation.getMenu().getItem(1).setChecked(true);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new CoursesFragment()).commitNow();
        }

        ad = new AlertDialog.Builder(this);
        ad.setMessage(getString(R.string.edit_profile_alert_text)); // сообщение
        ad.setPositiveButton(getString(R.string.edit_profile_alert_stay), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
            }
        });
        ad.setNegativeButton(getString(R.string.edit_profile_alert_leave), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                getSupportFragmentManager().popBackStack();
            }
        });
        ad.setCancelable(true);
        ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
            }
        });

        adEmptyFields = new AlertDialog.Builder(this);
        adEmptyFields.setMessage(R.string.edit_profile_alert_empty_text);
        adEmptyFields.setCancelable(true);

    }


    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_events:
                    selectedFragment = new EventsFragment();
                    break;
                case R.id.navigation_courses:
                    selectedFragment = new CoursesFragment();
                    break;
                case R.id.navigation_profile:
                    selectedFragment = new ProfileFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commitNow();
            return true;
        }
    };

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onOpenEditProfile() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new ProfileEditFragment())
                .addToBackStack(null)
                .commit();
    }

    public void gradesButtonClick(View view)
    {
        Log.d("gradesButtonClick", "clicked!");
        Intent intent = new Intent(this, GradesListActivity.class);
        startActivityForResult(intent,1);
        Log.d("gradesButtonClick", "GradesListActivity opened!");
    }

}
