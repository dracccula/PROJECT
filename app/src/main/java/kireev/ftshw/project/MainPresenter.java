package kireev.ftshw.project;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


import kireev.ftshw.project.Courses.FinishedCourses.FinishedCoursesSectionFragment;
import kireev.ftshw.project.Courses.Grades.GradesSectionFragment;
import kireev.ftshw.project.Courses.Grades.GradesVO;
import kireev.ftshw.project.Database.Dao.ProfileDao;
import kireev.ftshw.project.Database.Entity.Profile;
import kireev.ftshw.project.Database.ProjectDatabase;
import kireev.ftshw.project.Network.Model.ConnectionsResponse;
import kireev.ftshw.project.Network.Model.GradesResponse;
import kireev.ftshw.project.Profile.MVP.ProfileData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static kireev.ftshw.project.MainActivity.spStorage;

public class MainPresenter extends MvpBasePresenter<MainView> {

    private MainModel model;



    public MainPresenter(MainModel mainModel) {
        this.model = mainModel;
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
