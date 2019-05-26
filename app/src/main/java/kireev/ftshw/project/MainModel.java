package kireev.ftshw.project;

import kireev.ftshw.project.Network.Connector;
import kireev.ftshw.project.Network.FintechAPI;
import kireev.ftshw.project.Network.Model.ConnectionsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

class MainModel {

    void getConnections(Callback<ConnectionsResponse> callback){
        Retrofit retrofit = Connector.getRetrofitClient();
        FintechAPI fintechAPI = retrofit.create(FintechAPI.class);
        Call<ConnectionsResponse> call = fintechAPI.getConnections();
        call.enqueue(callback);
    }
}
