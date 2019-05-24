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

    public void viewIsReady() {
        ifViewAttached(new ViewAction<FinishedCoursesSectionView>() {
            @Override
            public void run(@NonNull FinishedCoursesSectionView view) {
                view.getCourses(getCourseTitleFromDB(), getCourseStartDateFromDB());
            }
        });
    }
}
