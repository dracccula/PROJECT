package kireev.ftshw.project.Events;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

interface EventsView extends MvpView {
    void getActiveEventsList(List<ActiveEventsVO> activeEventsVO);
    void showActiveErrorText();
    void hideActiveErrorText();
    void showActiveProgressbar();
    void hideActiveProgressbar();
    void showActiveRecyclerView();
    void hideActiveRecyclerView();
    void getArchiveEventsList(List<ArchiveEventsVO> archiveEventsVOList);
    void showArchiveProgressbar();
    void hideArchiveProgressbar();
    void showArchiveErrorText();
    void hideArchiveErrorText();
    void showArchiveRecyclerView();
    void hideArchiveRecyclerView();
    void stopRefreshLayoutAnimation();
    void stopSrcollActiveRV();
    void resumeScrollActiveRV();

}
