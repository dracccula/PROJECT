package kireev.ftshw.project.Courses;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import kireev.ftshw.project.App;
import kireev.ftshw.project.Database.ProjectDatabase;

import static kireev.ftshw.project.MainActivity.spStorage;

class FinishedCoursesSectionPresenter extends MvpBasePresenter<FinishedCoursesSectionView> {
    private ProjectDatabase db = App.getInstance().getDatabase();

    private String getCourseTitleFromDB() {
        return spStorage.getString("courseTitle", "");
    }

    private String getCourseStartDateFromDB() {
        return spStorage.getString("courseDateStart", "");
    }

    @Override
    public void attachView(@NonNull FinishedCoursesSectionView view) {
        //viewIsReady();
        view.hideCoursesProgressBar();
        view.getCourses(getCourseTitleFromDB(), getCourseStartDateFromDB());
    }

    @Override
    public void detachView() {

    }

    @Override
    public void destroy() {

    }

    private void viewIsReady() {
        ifViewAttached(new ViewAction<FinishedCoursesSectionView>() {
            @Override
            public void run(@NonNull FinishedCoursesSectionView view) {
                view.hideCoursesProgressBar();
                view.getCourses(getCourseTitleFromDB(), getCourseStartDateFromDB());
            }
        });
    }
}
