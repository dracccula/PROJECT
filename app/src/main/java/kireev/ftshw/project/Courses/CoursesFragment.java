package kireev.ftshw.project.Courses;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kireev.ftshw.project.MainActivity;
import kireev.ftshw.project.R;
import kireev.ftshw.project.TempTools.TempTools;

import static kireev.ftshw.project.Courses.GradesFragment.pointsBadge1;
import static kireev.ftshw.project.Courses.GradesFragment.pointsBadge2;
import static kireev.ftshw.project.Courses.GradesFragment.pointsBadge3;


public class CoursesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    public static SwipeRefreshLayout mSwipeRefreshLayout;

    public CoursesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity())
                .setActionBarTitle(getString(R.string.title_courses));
        View v = inflater.inflate(R.layout.fragment_courses, container, false);
        mSwipeRefreshLayout = v.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        return v;

        /*ArrayList<CoursesItems> exampleList = new ArrayList<>();
        exampleList.add(new CoursesItems(R.drawable.ic_courses_black_24dp, "Line 1", "Line 2"));
        exampleList.add(new CoursesItems(R.drawable.ic_events_black_24dp, "Line 3", "Line 4"));
        */
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
    public void onRefresh() {
        pointsBadge1.setText(String.valueOf(TempTools.SetRandom()));
        pointsBadge2.setText(String.valueOf(TempTools.SetRandom()));
        pointsBadge3.setText(String.valueOf(TempTools.SetRandom()));
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
