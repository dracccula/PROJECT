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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import kireev.ftshw.project.Courses.CoursesFragment;
import kireev.ftshw.project.Courses.GradesList.GradesListActivity;
import kireev.ftshw.project.Courses.Rating.RatingActivity;
import kireev.ftshw.project.Events.EventsFragment;
import kireev.ftshw.project.Profile.Login.LoginActivity;
import kireev.ftshw.project.Profile.AnonimProfileFragment;
import kireev.ftshw.project.Profile.MVP.ProfileViewFragment;


public class MainActivity extends AppCompatActivity {

    public AlertDialog.Builder ad, adEmptyFields, adLogout;
    private static long back_pressed;
    public static SharedPreferences spStorage;
    public static String anygenCookie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        spStorage = this.getPreferences(Context.MODE_PRIVATE);
        if (!spStorage.getBoolean("IS_AUTORIZED", false)) {
            startActivity(new Intent(this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
        } else {
            if (savedInstanceState == null) {
                navigation.getMenu().getItem(1).setChecked(true);
                if (navigation.getMenu().findItem(navigation.getSelectedItemId()) == navigation.getMenu().getItem(0)) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new EventsFragment()).commitNow();
                }
                if (navigation.getMenu().findItem(navigation.getSelectedItemId()) == navigation.getMenu().getItem(1)) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new CoursesFragment()).commitNow();
                }
                if (navigation.getMenu().findItem(navigation.getSelectedItemId()) == navigation.getMenu().getItem(2)) {
                    if (spStorage.getBoolean("IS_AUTORIZED", false)) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new ProfileViewFragment()).commitNow();
                    } else {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new AnonimProfileFragment()).commitNow();
                    }
                }
            }
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

        adLogout = new AlertDialog.Builder(this);
        adLogout.setMessage(getString(R.string.profile_logout_text)); // сообщение
        adLogout.setPositiveButton(getString(R.string.profile_logout_yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                SharedPreferences.Editor ed = spStorage.edit();
                ed.remove("anygenCookie");
                ed.remove("IS_AUTORIZED");
                ed.apply();
                Toast.makeText(getBaseContext(), "signOut!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getBaseContext(), LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        adLogout.setNegativeButton(getString(R.string.profile_logout_no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                getSupportFragmentManager().popBackStack();
            }
        });
        adLogout.setCancelable(true);
        adLogout.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            adLogout.show();
        }
        return true;
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
                    if (spStorage.getBoolean("IS_AUTORIZED", false)) {
                        selectedFragment = new ProfileViewFragment();
                        break;
                    } else {
                        selectedFragment = new AnonimProfileFragment();
                        break;
                    }
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commitNow();
            return true;
        }
    };

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    public void gradesButtonClick(View view) {
        Log.d("gradesButtonClick", "clicked!");
        startActivityForResult(new Intent(this, GradesListActivity.class), 1);
        Log.d("gradesButtonClick", "GradesListActivity opened!");
    }

    public void ratingButtonClick(View view) {
        Log.d("gradesButtonClick", "clicked!");
        startActivityForResult(new Intent(this, RatingActivity.class), 1);
        Log.d("gradesButtonClick", "GradesListActivity opened!");
    }

    public void openLoginActivity(View view) {
        Log.d("openLoginActivity", "clicked!");
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) super.onBackPressed();
        else
            Toast.makeText(getBaseContext(), "Нажмите еще раз для выхода", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }
}