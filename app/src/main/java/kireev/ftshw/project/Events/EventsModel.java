package kireev.ftshw.project.Events;

import java.util.List;

import kireev.ftshw.project.App;
import kireev.ftshw.project.Database.Dao.EventsDao;
import kireev.ftshw.project.Database.Entity.Events;
import kireev.ftshw.project.Database.ProjectDatabase;
import kireev.ftshw.project.Network.Connector;
import kireev.ftshw.project.Network.FintechAPI;
import kireev.ftshw.project.Network.Model.EventsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

class EventsModel {
    private ProjectDatabase db = App.getInstance().getDatabase();

    void getEventList(Callback<EventsResponse> callback) {
        Retrofit retrofit = Connector.getRetrofitClient();
        FintechAPI fintechAPI = retrofit.create(FintechAPI.class);
        Call<EventsResponse> call = fintechAPI.getEventsList();
        call.enqueue(callback);
    }


    void updateActiveEventsDB(List<EventsResponse.Active> activeList, boolean isArchive) {
        EventsDao eventsDao = db.eventsDao();
        Events events = new Events();
        for (int i = 0; i< activeList.size(); i++) {
            events.setTitle(activeList.get(i).getTitle());
            events.setArchive(isArchive);
            events.setDateStart(activeList.get(i).getDateStart());
            events.setDateEnd(activeList.get(i).getDateEnd());
            if (activeList.get(i).getEventType() != null) {
                events.setEventTypeName(activeList.get(i).getEventType().name);
                events.setEventTypeColor(activeList.get(i).getEventType().color);
            }
            events.setCustomDate(activeList.get(i).getCustomDate());
            events.setPlace(activeList.get(i).getPlace());
            events.setUrl(activeList.get(i).getUrl());
            events.setUrlExternal(activeList.get(i).getUrlExternal());
            events.setUrlText(activeList.get(i).getUrlText());
            events.setDescription(activeList.get(i).getDescription());
            eventsDao.insert(events);
        }
    }

    void updateArchiveEventsDB(List<EventsResponse.Archive> archiveList, boolean isArchive) {
        EventsDao eventsDao = db.eventsDao();
        Events events = new Events();
        for (int i = 0; i< archiveList.size(); i++) {
            events.setTitle(archiveList.get(i).getTitle());
            events.setArchive(isArchive);
            events.setDateStart(archiveList.get(i).getDateStart());
            events.setDateEnd(archiveList.get(i).getDateEnd());
            if (archiveList.get(i).getEventType() != null) {
                events.setEventTypeName(archiveList.get(i).getEventType().name);
                events.setEventTypeColor(archiveList.get(i).getEventType().color);
            }
            events.setCustomDate(archiveList.get(i).getCustomDate());
            events.setPlace(archiveList.get(i).getPlace());
            events.setUrl(archiveList.get(i).getUrl());
            events.setUrlExternal(archiveList.get(i).getUrlExternal());
            events.setUrlText(archiveList.get(i).getUrlText());
            events.setDescription(archiveList.get(i).getDescription());
            eventsDao.insert(events);
        }
    }
}
