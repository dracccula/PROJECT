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

    private void getGrades() {
        gradesVOList.clear();
        model.getGrades(new Callback<List<GradesResponse>>() {
            @Override
            public void onResponse(Call<List<GradesResponse>> call, Response<List<GradesResponse>> response) {
                String studentName = "";
                String studentPoints = "";
                List<GradesResponse> gradesResponseList = response.body();
                for (int i = 0; i < gradesResponseList.size(); i++) {
                    List<GradesResponse.Grades> gradesList = gradesResponseList.get(i).getGrades();
                    for (int j = 0; j < gradesList.size(); j++) {
                        GradesVO gradesVO = new GradesVO();
                        studentName = gradesList.get(j).getStudent();
                        gradesVO.setName(studentName);
                        List<GradesResponse.StudentGrade> studentGradeList =  gradesList.get(j).getGrades();
                        studentPoints =studentGradeList.get(studentGradeList.size() - 1).getMark() + "";
                        gradesVO.setPoints(studentPoints);
                        gradesVOList.add(gradesVO);
                    }
                }
                ifViewAttached(new ViewAction<GradesSectionView>() {
                    @Override
                    public void run(@NonNull GradesSectionView view) {
                        view.getGrades(gradesVOList);
                    }
                });
                Log.i("Profile header:", gradesVOList.toString());
            }

            @Override
            public void onFailure(Call<List<GradesResponse>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    void viewIsReady() {
        getGrades();
    }
}
