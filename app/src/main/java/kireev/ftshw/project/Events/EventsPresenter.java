package kireev.ftshw.project.Events;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import kireev.ftshw.project.App;
import kireev.ftshw.project.Database.Dao.EventsDao;
import kireev.ftshw.project.Database.Entity.Events;
import kireev.ftshw.project.Database.ProjectDatabase;
import kireev.ftshw.project.Network.Model.EventsResponse;
import kireev.ftshw.project.TempTools.SetRandom;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class EventsPresenter extends MvpBasePresenter<EventsView> {

    private EventsModel model;
    private List<ActiveEventsVO> activeEventsVOList = new ArrayList<>();
    private List<ArchiveEventsVO> archiveEventsVOList = new ArrayList<>();
    private ProjectDatabase db = App.getInstance().getDatabase();

    EventsPresenter(EventsModel eventsModel) {
        this.model = eventsModel;
    }

    private void checkDatabase() {
        EventsDao eventsDao = db.eventsDao();
        if (eventsDao.getAll().isEmpty()) {
            getEvents();
        } else {
            getEventsFromDb();
        }
    }

    private void getEventsFromDb() {
        activeEventsVOList.clear();
        EventsDao eventsDao = App.getInstance().getDatabase().eventsDao();
        List<Events> activeList = eventsDao.getAllActive();
        for (int i = 0; i < activeList.size(); i++) {
            ActiveEventsVO activeEventsVO = new ActiveEventsVO();
            activeEventsVO.setTitle(activeList.get(i).getTitle());
            activeEventsVO.setDateStart(activeList.get(i).getDateStart());
            activeEventsVO.setDateEnd(activeList.get(i).getDateEnd());
            if (activeList.get(i).getEventTypeName() != null && activeList.get(i).getEventTypeColor() != null) {
                activeEventsVO.setEventTypeName(activeList.get(i).getEventTypeName());
                activeEventsVO.setEventTypeColor(activeList.get(i).getEventTypeColor());
            }
            activeEventsVO.setImageId(SetRandom.SetRandomInt());
            activeEventsVOList.add(activeEventsVO);
        }
        ifViewAttached(view -> {
            view.getActiveEventsList(activeEventsVOList);
            view.hideActiveProgressbar();
        });

        archiveEventsVOList.clear();
        List<Events> archiveList = eventsDao.getAllArchive();
        for (int i = 0; i < archiveList.size(); i++) {
            ArchiveEventsVO archiveEventsVO = new ArchiveEventsVO();
            archiveEventsVO.setTitle(archiveList.get(i).getTitle());
            archiveEventsVO.setDateStart(archiveList.get(i).getDateStart());
            archiveEventsVO.setDateEnd(archiveList.get(i).getDateEnd());
            if (archiveList.get(i).getEventTypeName() != null && archiveList.get(i).getEventTypeColor() != null) {
                archiveEventsVO.setEventTypeName(archiveList.get(i).getEventTypeName());
                archiveEventsVO.setEventTypeColor(archiveList.get(i).getEventTypeColor());
            }
            archiveEventsVOList.add(archiveEventsVO);
        }
        ifViewAttached(view -> {
            view.getArchiveEventsList(archiveEventsVOList);
            view.hideArchiveProgressbar();
        });
    }

    public void getEvents() {
        ifViewAttached(view -> view.stopSrcollActiveRV());
        activeEventsVOList.clear();
        archiveEventsVOList.clear();
        ifViewAttached(view -> model.getEventList(new Callback<EventsResponse>() {
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
                    model.updateActiveEventsDB(activeList, false);
                    ifViewAttached(view -> {
                        view.getActiveEventsList(activeEventsVOList);
                        view.hideActiveErrorText();
                        view.hideActiveProgressbar();
                        view.showActiveRecyclerView();
                        view.resumeScrollActiveRV();
                        view.stopRefreshLayoutAnimation();
                    });
                } else {
                    ifViewAttached(view -> {
                        view.hideActiveProgressbar();
                        view.showActiveErrorText();
                        view.stopRefreshLayoutAnimation();
                    });
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
                    model.updateArchiveEventsDB(archiveList, true);
                    ifViewAttached(view -> {
                        view.getArchiveEventsList(archiveEventsVOList);
                        view.hideArchiveErrorText();
                        view.hideArchiveProgressbar();
                        view.showArchiveRecyclerView();
                        view.stopRefreshLayoutAnimation();
                    });
                } else {
                    ifViewAttached(view -> {
                        view.hideArchiveProgressbar();
                        view.showArchiveErrorText();
                        view.stopRefreshLayoutAnimation();
                    });
                }

            }

            @Override
            public void onFailure(@NonNull Call<EventsResponse> call, @NonNull Throwable t) {
                ifViewAttached(view -> {
                    view.hideActiveProgressbar();
                    view.hideActiveRecyclerView();
                    view.showActiveErrorText();
                    view.hideArchiveProgressbar();
                    view.hideArchiveRecyclerView();
                    view.showArchiveErrorText();
                    view.stopRefreshLayoutAnimation();
                });
            }
        }));

    }

    void viewIsReady() {
        checkDatabase();
    }

}
