package kireev.ftshw.project;

import kireev.ftshw.project.Database.Dao.CourseDao;
import kireev.ftshw.project.Database.Entity.Course;
import kireev.ftshw.project.Database.ProjectDatabase;
import kireev.ftshw.project.Network.Connector;
import kireev.ftshw.project.Network.FintechAPI;
import kireev.ftshw.project.Network.Model.ConnectionsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

class MainModel {
    private ProjectDatabase db = App.getInstance().getDatabase();

    void getConnections(Callback<ConnectionsResponse> callback){
        Retrofit retrofit = Connector.getRetrofitClient();
        FintechAPI fintechAPI = retrofit.create(FintechAPI.class);
        Call<ConnectionsResponse> call = fintechAPI.getConnections();
        call.enqueue(callback);
    }
}
