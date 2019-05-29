package kireev.ftshw.project.Courses.FinishedCourses;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import kireev.ftshw.project.App;
import kireev.ftshw.project.Database.ProjectDatabase;

import static kireev.ftshw.project.MainActivity.spStorage;

class FinishedCoursesSectionPresenter extends MvpBasePresenter<FinishedCoursesSectionView> {
    private ProjectDatabase db = App.getInstance().getDatabase();

    private String getCourseTitleFromSP() {
        return spStorage.getString("courseTitle", "");
    }

    private String getCourseStartDateFromSP() {
        return spStorage.getString("courseDateStart", "");
    }

    private String getCoursePointsFromSP() {
        return String.valueOf(spStorage.getInt("profilePoints", 0));
    }

    @Override
    public void attachView(@NonNull FinishedCoursesSectionView view) {
        //viewIsReady();
        //view.hideCoursesProgressBar();
        //view.showCourses(getCourseTitleFromSP(), getCourseStartDateFromSP(), getCoursePointsFromSP());
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
                view.showCourses(getCourseTitleFromSP(), getCourseStartDateFromSP(), getCoursePointsFromSP());
            }
        });
    }
}
