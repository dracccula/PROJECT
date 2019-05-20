package kireev.ftshw.project.Login;

import kireev.ftshw.project.Network.Connector;
import kireev.ftshw.project.Network.FintechAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

class LoginModel {

    void signIn(Callback<SignInResponse> callback, String login, String password) {
        Retrofit retrofit = Connector.getRetrofitClient();
        FintechAPI fintechAPI = retrofit.create(FintechAPI.class);
        LoginData loginData = new LoginData(login, password);
        Call<SignInResponse> call = fintechAPI.postCredentials(loginData);
        call.enqueue(callback);
    }



}
