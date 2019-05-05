package kireev.ftshw.project.Events;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import java.util.List;

import kireev.ftshw.project.MainActivity;
import kireev.ftshw.project.R;


public class EventsFragment extends MvpFragment<EventsView,EventsPresenter> implements EventsView{
    RecyclerView rvEvents;
    ActiveEventsAdapter activeEventsAdapter;

    public EventsFragment() {
        // Required empty public constructor
    }

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
        rvEvents = v.findViewById(R.id.rvActiveEvents);
        rvEvents.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        activeEventsAdapter = new ActiveEventsAdapter(getContext());
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
        activeEventsAdapter.setItems(activeEventsVO);
        rvEvents.setAdapter(activeEventsAdapter);
    }
}
