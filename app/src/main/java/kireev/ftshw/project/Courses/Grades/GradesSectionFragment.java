package kireev.ftshw.project.Courses.Grades;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import java.util.List;

import kireev.ftshw.project.R;

public class GradesSectionFragment extends MvpFragment<GradesSectionView, GradesSectionPresenter> implements GradesSectionView {
    public RecyclerView rvGrades;
    GradesAdapter gradesAdapter;
    ProgressBar pbGradesStudents;
    private List<GradesVO> gradesVOList;


    public GradesSectionFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public GradesSectionPresenter createPresenter() {
        GradesSectionModel gradesSectionModel = new GradesSectionModel();
        return new GradesSectionPresenter(gradesSectionModel);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_grades_section, container, false);
        pbGradesStudents = v.findViewById(R.id.pbGradesStudents);
        rvGrades = v.findViewById(R.id.rvGradesStudents);
        gradesAdapter = new GradesAdapter(getContext());
        if (gradesVOList != null) {
            gradesAdapter.setItems(gradesVOList);
            gradesVOList = null;
        }
        presenter.attachView(this);
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("hui123", "onDestroy");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.attachView(this);
        Log.i("hui123", "GradesSectionFragment onViewCreated");
        //presenter.viewIsReady();
    }

    @Override
    public void showGrades(List<GradesVO> gradesVOList) {
        this.gradesVOList = gradesVOList;
        Log.i("hui123", "getGrades in Grades");
        if (isAdded() && gradesAdapter != null){
            this.gradesVOList = null;
            gradesAdapter.clearList(gradesVOList);
            gradesAdapter.setItems(gradesVOList);
            rvGrades.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            hideProgressBar();
            showRecyclerView();
            rvGrades.setAdapter(gradesAdapter);
        }
    }

    @Override
    public void hideProgressBar() {
        pbGradesStudents.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        pbGradesStudents.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRecyclerView() {
        rvGrades.setVisibility(View.GONE);
    }

    @Override
    public void showRecyclerView() {
        rvGrades.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopScrollRV() {
        rvGrades.stopScroll();
    }



}
