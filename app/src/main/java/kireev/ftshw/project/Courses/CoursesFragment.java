package kireev.ftshw.project.Courses;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import kireev.ftshw.project.Courses.FinishedCourses.FinishedCoursesSectionFragment;
import kireev.ftshw.project.Courses.Grades.GradesSectionFragment;
import kireev.ftshw.project.MainActivity;
import kireev.ftshw.project.MainModel;
import kireev.ftshw.project.MainPresenter;
import kireev.ftshw.project.R;
import kireev.ftshw.project.TempTools.SetRandom;

import static kireev.ftshw.project.Courses.Grades.GradesSectionFragment.pointsBadge1;
import static kireev.ftshw.project.Courses.Grades.GradesSectionFragment.pointsBadge2;
import static kireev.ftshw.project.Courses.Grades.GradesSectionFragment.pointsBadge3;
import static kireev.ftshw.project.Courses.Grades.GradesSectionFragment.viewAvatarOne;
import static kireev.ftshw.project.Courses.Grades.GradesSectionFragment.viewAvatarTwo;
import static kireev.ftshw.project.Courses.Grades.GradesSectionFragment.viewAvatarThree;


public class CoursesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private MainModel mainModel;
    MainPresenter mainPresenter = new MainPresenter(mainModel);
//    GradesSectionFragment fragmentGrades = new GradesSectionFragment();
//    FinishedCoursesSectionFragment fragmentFinishedCourses = new FinishedCoursesSectionFragment();

    public CoursesFragment() {
        // Required empty public constructor
    }

    public GradesSectionFragment getGradesSectionFragment() {
        Fragment fragment = getFragmentManager().findFragmentById(R.id.fragmentGrades);
        return (GradesSectionFragment) fragment;
    }

    public FinishedCoursesSectionFragment getFinishedCoursesSectionFragment() {
        Fragment fragment = getFragmentManager().findFragmentById(R.id.fragmentFinishedCourses);
        return (FinishedCoursesSectionFragment) fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainPresenter.setGradesSectionFragment(getGradesSectionFragment());
        mainPresenter.setFinishedCoursesSectionFragment(getFinishedCoursesSectionFragment());
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) Objects.requireNonNull(getActivity()))
                .setActionBarTitle(getString(R.string.title_courses));
//        CourseDao courseDao = App.getInstance().getDatabase().courseDao();
//        List<Course> course = courseDao.getAll();
        View v = inflater.inflate(R.layout.fragment_courses, container, false);
        mSwipeRefreshLayout = v.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentGrades, new GradesSectionFragment());
        ft.replace(R.id.fragmentFinishedCourses, new FinishedCoursesSectionFragment());
        ft.commit();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_menu, menu);
        MenuItem item = menu.findItem(R.id.logout);
        item.setVisible(false);
    }

    @Override
    public void onRefresh() {
        postRunnable(SetRandom.SetRandomInt(), SetRandom.SetRandomInt(), SetRandom.SetRandomInt());
    }

    private void postRunnable(final int id1, final int id2, final int id3) {
        tryToSleepForTwoSecondsInNewThread(id1, id2, id3);
    }

    private void tryToSleepForTwoSecondsInNewThread(final int id1, final int id2, final int id3) {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 42) {
                    List<String> badges = (List<String>) msg.obj;
                    pointsBadge1.setText(badges.get(0));
                    pointsBadge2.setText(badges.get(1));
                    pointsBadge3.setText(badges.get(2));
                    viewAvatarOne.setBackgroundColor(SetRandom.SetRandomColor());
                    viewAvatarTwo.setBackgroundColor(SetRandom.SetRandomColor());
                    viewAvatarThree.setBackgroundColor(SetRandom.SetRandomColor());
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final List<String> messages = new ArrayList<>(3);
                messages.add(String.valueOf(SetRandom.SetRandomInt()));
                messages.add(String.valueOf(SetRandom.SetRandomInt()));
                messages.add(String.valueOf(SetRandom.SetRandomInt()));
                Message message = handler.obtainMessage(42, messages);
                handler.sendMessage(message);
            }
        }).start();
    }
}
