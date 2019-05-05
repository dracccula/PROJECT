package kireev.ftshw.project.Events;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import kireev.ftshw.project.Network.Model.EventsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class EventsPresenter extends MvpBasePresenter<EventsView> {

    private EventsModel model;
    private List<ActiveEventsVO> activeEventsVOList = new ArrayList<>();

    EventsPresenter(EventsModel eventsModel) {
        this.model = eventsModel;
    }

    private void getEvents() {
        if (getView() != null) {
            model.getEventList(new Callback<EventsResponse>() {
                @Override
                public void onResponse(@NonNull Call<EventsResponse> call, @NonNull Response<EventsResponse> response) {
                    EventsResponse eventsResponse = response.body();
                    int code = response.code();
                    if (code == 200 && Objects.requireNonNull(eventsResponse).getActive() != null) {
                        List<EventsResponse.Active> activeList = eventsResponse.getActive();
                        for (int i = 0; i < activeList.size(); i++) {
                            ActiveEventsVO activeEventsVO = new ActiveEventsVO();
                            activeEventsVO.setTitle(activeList.get(i).getTitle());
                            activeEventsVO.setDateStart(activeList.get(i).getDateStart());
                            activeEventsVO.setDateEnd(activeList.get(i).getDateEnd());
                            if (activeList.get(i).getEventType() != null) {
                                activeEventsVO.setEventTypeName(activeList.get(i).getEventType().name);
                                activeEventsVO.setEventTypeColor(activeList.get(i).getEventType().color);
                            }
                            activeEventsVOList.add(activeEventsVO);
                            getView().getActiveEventsList(activeEventsVOList);
                        }
                    }
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
