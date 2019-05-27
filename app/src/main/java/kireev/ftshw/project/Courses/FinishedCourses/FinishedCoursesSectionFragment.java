package kireev.ftshw.project.Courses.FinishedCourses;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import java.util.ArrayList;
import java.util.List;

import kireev.ftshw.project.R;
import kireev.ftshw.project.WebViewActivity;

public class FinishedCoursesSectionFragment extends MvpFragment<FinishedCoursesSectionView, FinishedCoursesSectionPresenter> implements FinishedCoursesSectionView {

    ProgressBar pbCourses;
    RecyclerView rvCourses;
    CoursesAdapter coursesAdapter;

    @NonNull
    @Override
    public FinishedCoursesSectionPresenter createPresenter() {
        return new FinishedCoursesSectionPresenter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_finished_courses_section, container, false);
        pbCourses = v.findViewById(R.id.pbCourses);
        rvCourses = v.findViewById(R.id.rvCourses);
        rvCourses.setLayoutManager(new LinearLayoutManager(getContext()));
        CoursesAdapter.OnClickListener onClickListener = new CoursesAdapter.OnClickListener() {
            @Override
            public void onClick(CoursesVO coursesVO) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                startActivity(intent);
            }
        };
        coursesAdapter = new CoursesAdapter(onClickListener, getContext());
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.attachView(this);
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
    public void getCourses(String title, String dateStart, String points) {
        List<CoursesVO> coursesVOList = new ArrayList<>();
        CoursesVO coursesVO = new CoursesVO();
        coursesVO.setTitle(title);
        coursesVO.setDateStart(dateStart);
        coursesVO.setPoints(points);
        coursesVOList.add(0,coursesVO);
        coursesAdapter.setItems(coursesVOList);
        rvCourses.setAdapter(coursesAdapter);
    }

    @Override
    public void hideCoursesProgressBar() {
        pbCourses.setVisibility(View.GONE);
    }
}
