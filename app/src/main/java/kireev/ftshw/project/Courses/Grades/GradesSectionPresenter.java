package kireev.ftshw.project.Courses.Grades;

import android.support.annotation.NonNull;
import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;

import kireev.ftshw.project.Network.Model.GradesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class GradesSectionPresenter extends MvpBasePresenter<GradesSectionView> {

    private GradesSectionModel model;
    private List<GradesVO> gradesVOList = new ArrayList<>();

    GradesSectionPresenter(GradesSectionModel model) {
        this.model = model;
    }

    @Override
    public void attachView(@NonNull GradesSectionView view) {
        viewIsReady();
    }

    @Override
    public void detachView(boolean retainInstance) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void destroy() {

    }


    void viewIsReady() {
        //getGrades();
    }
}
