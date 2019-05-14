package kireev.ftshw.project.Events;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

interface EventsView extends MvpView {
    void getActiveEventsList(List<ActiveEventsVO> activeEventsVO);
    void showActiveErrorText();
    void hideActiveProgressbar();
    void getArchiveEventsList(List<ArchiveEventsVO> archiveEventsVOList);
    void hideArchiveProgressbar();
    void showArchiveErrorText();
    void stopRefreshLayoutAnimation();
}
