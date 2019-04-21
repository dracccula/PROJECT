package kireev.ftshw.project.Profile.Login;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kireev.ftshw.project.Network.Connector;
import kireev.ftshw.project.R;

public class LoginViewActivity extends AppCompatActivity implements LoginView {
    EditText etLogin, etPassword;
    TextView textView;
    ImageView ivLogo;
    Button bLogin;

    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle(getString(R.string.title_activity_login));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textView = findViewById(R.id.tv_temp);
        etLogin = findViewById(R.id.et_login);
        etPassword = findViewById(R.id.et_password);
        ivLogo = findViewById(R.id.iv_logo);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ivLogo.setVisibility(View.GONE);
        }
        bLogin = findViewById(R.id.btn_login);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.signIn(etLogin.getText().toString(), etPassword.getText().toString());
                Log.e("Login onClick", "clicked");
            }
        });
        LoginModel loginModel = new LoginModel();
        presenter = new LoginPresenter(loginModel);
        presenter.attachView(this);
        Log.e("Login onCreate", "view attached");
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
            presenter.signIn(etLogin.getText().toString(), etPassword.getText().toString());
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

    @Override
    public void authorize(SignInResponse signInResponse) {
        textView.setText("firstName: " + signInResponse.getFirstName() + "\n" +
                "lastName: " + signInResponse.getLastName() + "\n" +
                "email: " + signInResponse.getEmail());
    }

    @Override
    public void showError(String message) {
        Log.e("signIn onFailure", "ooops!");
        Toast.makeText(getBaseContext(), "signIn went wrong!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        Log.e("Login onDestroy", "view detached");
    }
}
