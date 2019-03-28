package kireev.ftshw.project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import kireev.ftshw.project.Courses.CoursesFragment;
import kireev.ftshw.project.Courses.GradesListActivity;
import kireev.ftshw.project.Courses.ui.gradeslist.GradesListFragment;
import kireev.ftshw.project.Events.EventsFragment;
import kireev.ftshw.project.Network.Connector;
import kireev.ftshw.project.Network.FintechAPI;
import kireev.ftshw.project.Network.Ser.SignIn;
import kireev.ftshw.project.Network.Ser.SignInResponse;
import kireev.ftshw.project.Network.Ser.UserResponse;
import kireev.ftshw.project.Profile.AnonimProfileFragment;
import kireev.ftshw.project.Profile.LoginActivity;
import kireev.ftshw.project.Profile.ProfileEditFragment;
import kireev.ftshw.project.Profile.ProfileFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static kireev.ftshw.project.Profile.LoginActivity.sPrefCookie;


public class MainActivity extends AppCompatActivity
        implements ProfileFragment.OnProfileFragmentListener {

    public AlertDialog.Builder ad;
    public AlertDialog.Builder adEmptyFields;
    private static long back_pressed;
    public static boolean IS_AUTORIZED = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (savedInstanceState == null) {
            navigation.getMenu().getItem(2).setChecked(true);
            if (IS_AUTORIZED) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commitNow();
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AnonimProfileFragment()).commitNow();
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

    }

    @Override
    protected void onResume() {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        if ((navigation.getSelectedItemId()) == R.id.navigation_profile) {
            if (IS_AUTORIZED) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commitNow();
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AnonimProfileFragment()).commitNow();
            }
        }
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.logout) {
            SharedPreferences.Editor ed = sPrefCookie.edit();
            ed.clear();
            signOut();
            Toast.makeText(getBaseContext(), "signOut!", Toast.LENGTH_SHORT).show();
            IS_AUTORIZED = false;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new AnonimProfileFragment()).commitNow();
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
                    if (IS_AUTORIZED) {
                        selectedFragment = new ProfileFragment();
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

    @Override
    public void onOpenEditProfile() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new ProfileEditFragment())
//                .addToBackStack(null)
                .commitNow();
    }

    public void gradesButtonClick(View view) {
        Log.d("gradesButtonClick", "clicked!");
        startActivityForResult(new Intent(this, GradesListActivity.class), 1);
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

    private void signOut(){
        Retrofit retrofit = Connector.getRetrofitClient();
        FintechAPI fintechAPI = retrofit.create(FintechAPI.class);
        Call<SignInResponse> call = fintechAPI.postEmpty();
        call.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call call, Response response) {
                String headers = response.headers().toString();
                String cookie = response.headers().get("Set-Cookie");
                if (response.isSuccessful()) {
                    Log.i("signOut Response", "headers: " + headers);
                    Log.i("signOut Response", "cookie: " + cookie);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("signOut onFailure", "ooops!");
                Toast.makeText(getBaseContext(), "signOut went wrong!", Toast.LENGTH_SHORT).show();
            }


        });
    }
}
