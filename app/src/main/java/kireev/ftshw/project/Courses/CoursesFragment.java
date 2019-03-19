package kireev.ftshw.project.Courses;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kireev.ftshw.project.MainActivity;
import kireev.ftshw.project.R;
import kireev.ftshw.project.TempTools.SetRandom;

import static kireev.ftshw.project.Courses.GradesFragment.pointsBadge1;
import static kireev.ftshw.project.Courses.GradesFragment.pointsBadge2;
import static kireev.ftshw.project.Courses.GradesFragment.pointsBadge3;
import static kireev.ftshw.project.Courses.GradesFragment.viewAvatarOne;
import static kireev.ftshw.project.Courses.GradesFragment.viewAvatarTwo;
import static kireev.ftshw.project.Courses.GradesFragment.viewAvatarThree;


public class CoursesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LooperThread looperThread;

    public CoursesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity())
                .setActionBarTitle(getString(R.string.title_courses));
        View v = inflater.inflate(R.layout.fragment_courses, container, false);
        mSwipeRefreshLayout = v.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        looperThread = new LooperThread();
        looperThread.start();
        return v;
    }

    @Override
    public void onRefresh() {
        postRunnable(SetRandom.SetRandomInt(), SetRandom.SetRandomInt(), SetRandom.SetRandomInt());
    }

    private void postRunnable(final int id1, final int id2, final int id3) {
        looperThread.post(new Runnable() {
            @Override
            public void run() {
                tryToSleepForTwoSecondsInNewThread(id1, id2, id3);
            }
        });
    }

    private void tryToSleepForTwoSecondsInNewThread(final int id1, final int id2, final int id3) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    logMessageInUiThread(id1, id2, id3);
                } catch (InterruptedException e) {
                    logMessageInUiThread(id1, id2, id3);
                }
            }
        }).start();
    }

    private void logMessageInUiThread(final int id1, final int id2, final int id3) {
        final String message1 = String.valueOf(id1);
        final String message2 = String.valueOf(id2);
        final String message3 = String.valueOf(id3);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pointsBadge1.setText(message1);
                pointsBadge2.setText(message2);
                pointsBadge3.setText(message3);
                viewAvatarOne.setBackgroundColor(SetRandom.SetRandomColor());
                viewAvatarTwo.setBackgroundColor(SetRandom.SetRandomColor());
                viewAvatarThree.setBackgroundColor(SetRandom.SetRandomColor());
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
