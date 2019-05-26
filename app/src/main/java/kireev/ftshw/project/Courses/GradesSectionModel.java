package kireev.ftshw.project.Courses;

import java.util.List;

import kireev.ftshw.project.Network.Connector;
import kireev.ftshw.project.Network.FintechAPI;
import kireev.ftshw.project.Network.Model.GradesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

import static kireev.ftshw.project.MainActivity.spStorage;

class GradesSectionModel {

    void getGrades(Callback<GradesResponse> callback){
        Retrofit retrofit = Connector.getRetrofitClient();
        FintechAPI fintechAPI = retrofit.create(FintechAPI.class);
        Call<GradesResponse> call = fintechAPI.getGrades(spStorage.getString("courseUrl", ""));
        call.enqueue(callback);
    }

}
