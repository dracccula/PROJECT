package kireev.ftshw.project.Events;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import kireev.ftshw.project.Network.Model.EventsResponse;
import kireev.ftshw.project.TempTools.SetRandom;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class EventsPresenter extends MvpBasePresenter<EventsView> {

    private EventsModel model;
    private List<ActiveEventsVO> activeEventsVOList = new ArrayList<>();
    private List<ArchiveEventsVO> archiveEventsVOList = new ArrayList<>();

    EventsPresenter(EventsModel eventsModel) {
        this.model = eventsModel;
    }

    private void getEvents() {
        activeEventsVOList.clear();
        archiveEventsVOList.clear();
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
                            activeEventsVO.setImageId(SetRandom.SetRandomInt());
                            activeEventsVOList.add(activeEventsVO);
                        }
                        getView().getActiveEventsList(activeEventsVOList);
                        getView().hideActiveProgressbar();
                    } else {
                        getView().hideActiveProgressbar();
                        getView().showActiveErrorText();
                    }
                    if (code == 200 && Objects.requireNonNull(eventsResponse).getArchive() != null) {
                        List<EventsResponse.Archive> archiveList = eventsResponse.getArchive();
                        for (int i = 0; i < archiveList.size(); i++) {
                            ArchiveEventsVO archiveEventsVO = new ArchiveEventsVO();
                            archiveEventsVO.setTitle(archiveList.get(i).getTitle());
                            if (archiveList.get(i).getEventType() != null) {
                                archiveEventsVO.setEventTypeName(archiveList.get(i).getEventType().name);
                                archiveEventsVO.setEventTypeColor(archiveList.get(i).getEventType().color);
                            }
                            archiveEventsVOList.add(archiveEventsVO);
                        }
                        getView().getArchiveEventsList(archiveEventsVOList);
                        getView().hideArchiveProgressbar();
                    } else {
                        getView().hideArchiveProgressbar();
                        getView().showArchiveErrorText();
                    }

                }

                @Override
                public void onFailure(@NonNull Call<EventsResponse> call, @NonNull Throwable t) {
                    getView().hideActiveProgressbar();
                    getView().showActiveErrorText();
                    getView().hideArchiveProgressbar();
                    getView().showArchiveErrorText();
                }
            });
        }
    }

    void viewIsReady() {
        getEvents();
    }

}
