package kireev.ftshw.project.Profile.MVP;

import java.util.List;

import kireev.ftshw.project.Network.Connector;
import kireev.ftshw.project.Network.FintechAPI;
import kireev.ftshw.project.Network.Model.GradesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

import static kireev.ftshw.project.MainActivity.spStorage;

class ProfileModel {

    void getUserData(Callback<ProfileData> callback) {
        Retrofit retrofit = Connector.getRetrofitClient();
        FintechAPI fintechAPI = retrofit.create(FintechAPI.class);
        Call<ProfileData> call = fintechAPI.getUser();
        call.enqueue(callback);
    }

    void getGrades(Callback<List<GradesResponse>> callback) {
        Retrofit retrofit = Connector.getRetrofitClient();
        FintechAPI fintechAPI = retrofit.create(FintechAPI.class);
        Call<List<GradesResponse>> call = fintechAPI.getGrades(spStorage.getString("courseUrl", ""));
        call.enqueue(callback);
    }

}

