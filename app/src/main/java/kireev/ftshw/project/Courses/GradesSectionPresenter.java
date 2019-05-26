package kireev.ftshw.project.Courses;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

import kireev.ftshw.project.Network.Model.GradesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class GradesSectionPresenter implements MvpPresenter<GradesSectionView> {

    private GradesSectionModel model;

    @Override
    public void attachView(@NonNull GradesSectionView view) {
        //getGrades();
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

    private void getGrades(){
        model.getGrades(new Callback<GradesResponse>() {
            @Override
            public void onResponse(Call<GradesResponse> call, Response<GradesResponse> response) {

            }

            @Override
            public void onFailure(Call<GradesResponse> call, Throwable t) {

            }
        });
    }
}
