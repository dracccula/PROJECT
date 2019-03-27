package kireev.ftshw.project.Profile;

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

import kireev.ftshw.project.MainActivity;
import kireev.ftshw.project.Network.Ser.SignIn;
import kireev.ftshw.project.Network.Ser.SignInAPI;
import kireev.ftshw.project.Network.Ser.SignInResponse;
import kireev.ftshw.project.R;
import kireev.ftshw.project.Network.Connector;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    EditText etLogin, etPassword;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle(getString(R.string.title_activity_login));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        //Obtain an instance of Retrofit by calling the static method.
        Retrofit retrofit = Connector.getRetrofitClient();
        /*
        The main purpose of Retrofit is to create HTTP calls from the Java interface based on the annotation associated with each method. This is achieved by just passing the interface class as parameter to the create method
        */
        SignInAPI signInAPI = retrofit.create(SignInAPI.class);
        /*
        Invoke the method corresponding to the HTTP request which will return a Call object. This Call object will used to send the actual network request with the specified parameters
        */
        SignIn signIn = new SignIn(etLogin.getText().toString(),etPassword.getText().toString());
        Call<SignInResponse> call = signInAPI.postCredentials(signIn);
        /*
        This is the line which actually sends a network request. Calling enqueue() executes a call asynchronously. It has two callback listeners which will invoked on the main thread
        */
        call.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call call, Response response) {
                /*This is the success callback. Though the response type is JSON, with Retrofit we get the response in the form of WResponse POJO class
                 */
                if(response.isSuccessful()) {
                    //showResponse(response.body().toString());
                    Log.i("Response", "post submitted to API. " + response.body());
                    MainActivity.IS_AUTORIZED = true;
                    finish();
                }
//                if (response.body() != null) {
//                    SignInResponse signInResponse = (SignInResponse) response.body();
//                    textView.setText("firstName: " + signInResponse.getFirstName() + "\n " +
//                            "lastName: " + signInResponse.getLastName() + "\n" +
//                            "email: " + signInResponse.getEmail());
//                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("onFailure","ooops!");
                Toast.makeText(getBaseContext(), "Fail!", Toast.LENGTH_SHORT).show();
            }


        });
    }
}