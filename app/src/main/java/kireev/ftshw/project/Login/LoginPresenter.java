package kireev.ftshw.project.Login;

import android.content.SharedPreferences;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static kireev.ftshw.project.MainActivity.anygenCookie;
import static kireev.ftshw.project.MainActivity.spStorage;

class LoginPresenter {

    private LoginView view;
    private final LoginModel model;

    LoginPresenter(LoginModel model) {
        this.model = model;
    }

    void attachView(LoginView profileView) {
        view = profileView;
        Log.i("presenter", "presenter=" + this);
    }

    void detachView() {
        view = null;
    }

    void signIn(String login, String password) {
        if (view != null) {
            view.showProgress();
            model.signIn(new Callback<SignInResponse>() {
                @Override
                public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                    SignInResponse signInResponse = response.body();
                    if (response.isSuccessful()){
                        String cookie = response.headers().get("Set-Cookie");
                        anygenCookie = cookie;
                        SharedPreferences.Editor ed = spStorage.edit();
                        Log.i("signIn Response", "body: " + signInResponse);
                        Log.i("signIn Response", "cookie: " + cookie);
                        ed.putBoolean("IS_AUTORIZED", true);
                        ed.putString("anygenCookie", anygenCookie);
                        ed.apply();
                        view.hideProgress();
                        view.closeActivity();
                    } else {
                        view.hideProgress();
                        view.showError("Неверно введены email и/или пароль");
                    }
                }

                @Override
                public void onFailure(Call<SignInResponse> call, Throwable t) {
                    view.hideProgress();
                    view.showError(t.getMessage());
                }
            }, login, password);
        }

    }
}
