package kireev.ftshw.project.Profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import kireev.ftshw.project.R;
import kireev.ftshw.project.Network.Connector;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle(getString(R.string.title_activity_login));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    public void Autorization(View view){
        Log.d("Login","clicked");
        if (Connector.isOnline()){
            Toast.makeText(getBaseContext(),"Online",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getBaseContext(),"Offline",Toast.LENGTH_SHORT).show();
        }
    }
}
