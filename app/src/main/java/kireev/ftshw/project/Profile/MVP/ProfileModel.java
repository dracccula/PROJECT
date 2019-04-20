package kireev.ftshw.project.Profile.MVP;

import kireev.ftshw.project.Network.Connector;
import kireev.ftshw.project.Network.FintechAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class ProfileModel {

    public void getUserData(Callback<ProfileData> callback) {
        Retrofit retrofit = Connector.getRetrofitClient();
        FintechAPI fintechAPI = retrofit.create(FintechAPI.class);
        Call<ProfileData> call = fintechAPI.getUser();
        call.enqueue(callback);
    }

}

