package kireev.ftshw.project.Login;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kireev.ftshw.project.MainActivity;
import kireev.ftshw.project.Network.Connector;
import kireev.ftshw.project.R;

public class LoginActivity extends AppCompatActivity implements LoginView {
    EditText etLogin, etPassword;
    ImageView ivLogo;
    Button bLogin;
    ProgressBar pbLogin;
    private static long back_pressed;

    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));
        etLogin = findViewById(R.id.et_login);
        etPassword = findViewById(R.id.et_password);
        ivLogo = findViewById(R.id.iv_logo);
        pbLogin = findViewById(R.id.pbLogin);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ivLogo.setVisibility(View.GONE);
        }
        bLogin = findViewById(R.id.btn_login);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autorization(v);
                //presenter.signIn(etLogin.getText().toString(), etPassword.getText().toString());
                Log.e("Login onClick", "clicked");
            }
        });
        LoginModel loginModel = new LoginModel();
        presenter = (LoginPresenter) getLastCustomNonConfigurationInstance();

        if (presenter == null) {
            presenter = new LoginPresenter(loginModel);
        }
        presenter.attachView(this);
        Log.e("Login onCreate", "view attached");

    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        } else
            Toast.makeText(getBaseContext(), "Нажмите еще раз для выхода", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return presenter;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    public void autorization(View view) {
        if (Connector.isOnline(this)) {
        } else {
            Toast.makeText(getBaseContext(), "Отсутствует подключению к интернету", Toast.LENGTH_SHORT).show();
        }
        if (etLogin.getText().length() != 0 && etPassword.getText().length() != 0){
            if (isEmailValid(etLogin.getText().toString())) {
                presenter.signIn(etLogin.getText().toString(), etPassword.getText().toString());
            } else {
                Toast.makeText(getBaseContext(), "Невалидный email", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getBaseContext(), "Для авторизации необходимо заполнить поля Логин и Пароль", Toast.LENGTH_SHORT).show();
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

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    @Override
    public void closeActivity() {
        finish();
        startActivity(new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    @Override
    public void showError(String message) {
        Log.e("signIn onFailure", "ooops!");
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        pbLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLogin.setVisibility(View.GONE);
    }
}
