package kireev.ftshw.project.Profile.Login;

import kireev.ftshw.project.Network.Connector;
import kireev.ftshw.project.Network.FintechAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class LoginModel {

    public void signIn(Callback<SignInResponse> callback , String login, String password) {
        Retrofit retrofit = Connector.getRetrofitClient();
        FintechAPI fintechAPI = retrofit.create(FintechAPI.class);
        LoginData loginData = new LoginData(login, password);
        Call<SignInResponse> call = fintechAPI.postCredentials(loginData);
        call.enqueue(callback);
    }
}
