package kireev.ftshw.project.Courses;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import kireev.ftshw.project.Courses.FinishedCourses.FinishedCoursesSectionFragment;
import kireev.ftshw.project.Courses.Grades.GradesSectionFragment;
import kireev.ftshw.project.Courses.Rating.RatingSectionFragment;
import kireev.ftshw.project.MainActivity;
import kireev.ftshw.project.MainModel;
import kireev.ftshw.project.MainPresenter;
import kireev.ftshw.project.R;

public class CoursesFragment extends MvpFragment<CoursesFragmentView, CourseFragmentPresenter> implements SwipeRefreshLayout.OnRefreshListener, CoursesFragmentView {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private CoursesFragmentModel model = new CoursesFragmentModel();

    public CoursesFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public CourseFragmentPresenter createPresenter() {
        return new CourseFragmentPresenter(model);
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
        setHasOptionsMenu(false);
        Log.i("hui123", "onCreate " + getFragmentManager().getFragments());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) Objects.requireNonNull(getActivity()))
                .setActionBarTitle(getString(R.string.title_courses));
        View v = inflater.inflate(R.layout.fragment_courses, container, false);
        mSwipeRefreshLayout = v.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        Log.i("hui123", "onCreateView ");
        return v;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("hui123", "onViewCreated " + getFragmentManager().getFragments());
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        GradesSectionFragment fragmentGrades = new GradesSectionFragment();
        ft.replace(R.id.fragmentGrades, fragmentGrades);
        RatingSectionFragment fragmentRating = new RatingSectionFragment();
        ft.replace(R.id.fragmentRating, fragmentRating);
        FinishedCoursesSectionFragment fragmentFinishedCourses = new FinishedCoursesSectionFragment();
        ft.replace(R.id.fragmentFinishedCourses, fragmentFinishedCourses);
        ft.commit();
        presenter.setGradesSectionFragment(fragmentGrades);
        Log.i("hui123", "CoursesFragment onViewCreated " + fragmentGrades);
        presenter.setRatingSectionFragment(fragmentRating);
        presenter.setFinishedCoursesSectionFragment(fragmentFinishedCourses);
        presenter.viewIsReady();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_menu, menu);
        MenuItem item = menu.findItem(R.id.logout);
        item.setVisible(false);
    }

    @Override
    public void onRefresh() {
        presenter.viewIsReady();
    }

    @Override
    public void stopRefreshing() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
