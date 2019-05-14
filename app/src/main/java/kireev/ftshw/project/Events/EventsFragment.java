package kireev.ftshw.project.Events;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import java.util.List;
import java.util.Objects;

import kireev.ftshw.project.MainActivity;
import kireev.ftshw.project.R;


public class EventsFragment extends MvpFragment<EventsView, EventsPresenter> implements EventsView, SwipeRefreshLayout.OnRefreshListener {
    RecyclerView rvActiveEvents, rvArchiveEvents;
    ActiveEventsAdapter activeEventsAdapter;
    ArchiveEventsAdapter archiveEventsAdapter;
    ProgressBar pbActiveEvents, pbArchiveEvents;
    TextView tvActiveEventsError, tvArchiveEventsError;
    SwipeRefreshLayout swipeRefreshLayout;

    public EventsFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public EventsPresenter createPresenter() {
        EventsModel eventsModel = new EventsModel();
        presenter = new EventsPresenter(eventsModel);
        return presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity())
                .setActionBarTitle(getString(R.string.title_events));
        View v = inflater.inflate(R.layout.fragment_events, container, false);
        rvActiveEvents = v.findViewById(R.id.rvActiveEvents);
        pbActiveEvents = v.findViewById(R.id.pbActiveEvents);
        tvActiveEventsError = v.findViewById(R.id.tvActiveEventsError);
        rvActiveEvents.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        activeEventsAdapter = new ActiveEventsAdapter(getContext());

        rvArchiveEvents = v.findViewById(R.id.rvArchiveEvents);
        pbArchiveEvents = v.findViewById(R.id.pbArchiveEvents);
        tvArchiveEventsError = v.findViewById(R.id.tvArchiveEventsError);
        rvArchiveEvents.setLayoutManager(new LinearLayoutManager(getContext()));
        archiveEventsAdapter = new ArchiveEventsAdapter(getContext());
        rvArchiveEvents.setNestedScrollingEnabled(false);
        rvArchiveEvents.addItemDecoration(new ArchiveEventsAdapter.DividerItemDecoration(getContext(),R.drawable.divider2));

        swipeRefreshLayout = v.findViewById(R.id.swipe_events);
        swipeRefreshLayout.setOnRefreshListener(this);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.viewIsReady();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_menu, menu);
        MenuItem item = menu.findItem(R.id.logout);
        item.setVisible(false);
    }

    @Override
    public void getActiveEventsList(List<ActiveEventsVO> activeEventsVO) {
        if (isAdded()) {
            activeEventsAdapter.setItems(activeEventsVO);
            rvActiveEvents.setAdapter(activeEventsAdapter);
        }
    }

    @Override
    public void getArchiveEventsList(List<ArchiveEventsVO> archiveEventsVOList) {
        if (isAdded()) {
            archiveEventsAdapter.setItems(archiveEventsVOList);
            rvArchiveEvents.setAdapter(archiveEventsAdapter);
        }
    }

    @Override
    public void hideActiveProgressbar() {
        if (pbActiveEvents != null) {
            pbActiveEvents.setVisibility(View.GONE);
        }
    }

    @Override
    public void showActiveErrorText() {
        if (tvActiveEventsError != null) {
            tvActiveEventsError.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideArchiveProgressbar() {
        if (pbArchiveEvents != null) {
            pbArchiveEvents.setVisibility(View.GONE);
        }
    }

    @Override
    public void showArchiveErrorText() {
        if (tvArchiveEventsError != null) {
            tvArchiveEventsError.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void stopRefreshLayoutAnimation() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        presenter.getEvents();
    }
}
