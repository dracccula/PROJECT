package kireev.ftshw.project.Events;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import kireev.ftshw.project.Network.Model.EventsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class EventsPresenter extends MvpBasePresenter<EventsView> {

    private EventsModel model;

    EventsPresenter(EventsModel eventsModel) {
        this.model = eventsModel;
    }

    private void getEvents() {
        if (getView() != null) {
            model.getEventList(new Callback<EventsResponse>() {
                @Override
                public void onResponse(@NonNull Call<EventsResponse> call, @NonNull Response<EventsResponse> response) {
                    EventsResponse eventsResponse = response.body();
                }

                @Override
                public void onFailure(@NonNull Call<EventsResponse> call, @NonNull Throwable t) {

                }
            });
        }
    }

    void viewIsReady() {
        getEvents();
    }

}
