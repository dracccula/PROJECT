package kireev.ftshw.project;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import kireev.ftshw.project.Courses.Grades.GradesSectionFragment;
import kireev.ftshw.project.Courses.Grades.GradesVO;
import kireev.ftshw.project.Network.Model.ConnectionsResponse;
import kireev.ftshw.project.Network.Model.GradesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static kireev.ftshw.project.MainActivity.spStorage;

class MainPresenter extends MvpBasePresenter<MainView> {

    private MainModel model;
    private GradesSectionFragment fragment;
    private List<GradesVO> gradesVOList = new ArrayList<>();

    MainPresenter(MainModel mainModel, GradesSectionFragment fragment) {
        this.model = mainModel;
        this.fragment = fragment;
    }


    public void getConnections() {
        model.getConnections(new Callback<ConnectionsResponse>() {
            @Override
            public void onResponse(Call<ConnectionsResponse> call, Response<ConnectionsResponse> response) {
                ConnectionsResponse connectionsResponse = response.body();
                int code = response.code();
                if (code == 200 && Objects.requireNonNull(connectionsResponse).getCourses() != null) {
                    List<ConnectionsResponse.Course> courseList = connectionsResponse.getCourses();
                    SharedPreferences.Editor ed = spStorage.edit();
                    String title = courseList.get(0).getTitle();
                    String url = courseList.get(0).getUrl();
                    String status = courseList.get(0).getStatus();
                    String dateStart = courseList.get(0).getEventDateStart();
                    ed.putString("courseTitle", title);
                    ed.putString("courseUrl", url);
                    ed.putString("courseStatus", status);
                    ed.putString("courseDateStart",dateStart);
                    ed.apply();
                    //model.updateProfileCourseData(url, title, status, dateStart);
                    Log.e("getConnections", url + title + status + dateStart);
                }
            }

            @Override
            public void onFailure(Call<ConnectionsResponse> call, Throwable t) {
            }
        });
    }

    public void getGrades() {
        //gradesVOList.clear();
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
                Log.i("hui123", "getGrades in main");
                fragment.getGrades(gradesVOList);
                Log.i("Profile header:", gradesVOList.toString());
            }

            @Override
            public void onFailure(Call<List<GradesResponse>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }


    @Override
    public void attachView(@NonNull MainView view) {

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
}
