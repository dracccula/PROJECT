package kireev.ftshw.project;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

import java.util.List;
import java.util.Objects;


import kireev.ftshw.project.Network.Model.ConnectionsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static kireev.ftshw.project.MainActivity.spStorage;

class MainPresenter implements MvpPresenter<MainView> {

    private MainModel model;

    MainPresenter(MainModel mainModel) {
        this.model = mainModel;
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
