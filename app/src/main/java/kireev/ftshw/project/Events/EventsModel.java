package kireev.ftshw.project.Events;

import kireev.ftshw.project.Network.Connector;
import kireev.ftshw.project.Network.FintechAPI;
import kireev.ftshw.project.Network.Model.EventsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

class EventsModel {

    void getEventList(Callback<EventsResponse> callback){
        Retrofit retrofit = Connector.getRetrofitClient();
        FintechAPI fintechAPI = retrofit.create(FintechAPI.class);
        Call<EventsResponse> call = fintechAPI.getEventsList();
        call.enqueue(callback);
    }
}
