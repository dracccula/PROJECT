package kireev.ftshw.project.Profile;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kireev.ftshw.project.Network.Model.SignIn;
import kireev.ftshw.project.Network.FintechAPI;
import kireev.ftshw.project.Network.Model.SignInResponse;
import kireev.ftshw.project.R;
import kireev.ftshw.project.Network.Connector;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static kireev.ftshw.project.MainActivity.*;

public class LoginActivity extends AppCompatActivity {
    EditText etLogin, etPassword;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle(getString(R.string.title_activity_login));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textView = findViewById(R.id.tv_temp);
        etLogin = findViewById(R.id.et_login);
        etPassword = findViewById(R.id.et_password);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    public void autorization(View view) {
        Log.d("Login", "clicked");
        if (Connector.isOnline(this)) {
            Log.d("Login", "online!");
        } else {
            Toast.makeText(getBaseContext(), "Offline", Toast.LENGTH_SHORT).show();
        }
        if (isEmailValid(etLogin.getText().toString())) {
            signIn();
        } else {
            Toast.makeText(getBaseContext(), "Email is not valid", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isEmailValid(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
            return true;
        else
            return false;
    }

    private void signIn() {
        textView.setText("");
        Retrofit retrofit = Connector.getRetrofitClient();
        FintechAPI fintechAPI = retrofit.create(FintechAPI.class);
        SignIn signIn = new SignIn(etLogin.getText().toString(), etPassword.getText().toString());
        Call<SignInResponse> call = fintechAPI.postCredentials(signIn);
        call.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call call, Response response) {
                SignInResponse signInResponse = (SignInResponse) response.body();
                String headers = response.headers().toString();
                String cookie = response.headers().get("Set-Cookie");
                anygenCookie = cookie;
                SharedPreferences.Editor ed = spStorage.edit();
                if (response.isSuccessful()) {
                    Log.i("signIn Response", "body: " + signInResponse);
                    Log.i("signIn Response", "headers: " + headers);
                    Log.i("signIn Response", "cookie: " + cookie);
                    ed.putBoolean("IS_AUTORIZED", true);
                    ed.putString("anygenCookie", anygenCookie);
                    finish();
                }
//                if (signInResponse != null) {
//                    textView.setText("firstName: " + signInResponse.getFirstName() + "\n" +
//                            "lastName: " + signInResponse.getLastName() + "\n" +
//                            "email: " + signInResponse.getEmail());
//                }
                ed.apply();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("signIn onFailure", "ooops!");
                Toast.makeText(getBaseContext(), "signIn went wrong!", Toast.LENGTH_SHORT).show();
            }


        });
    }
}