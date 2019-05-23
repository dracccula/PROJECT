package kireev.ftshw.project.Courses;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.List;

import kireev.ftshw.project.App;
import kireev.ftshw.project.Database.Dao.CourseDao;
import kireev.ftshw.project.Database.Entity.Course;
import kireev.ftshw.project.Database.ProjectDatabase;

class FinishedCoursesSectionPresenter extends MvpBasePresenter<FinishedCoursesSectionView> {
    private ProjectDatabase db = App.getInstance().getDatabase();

    private String getCourseTitleFromDB(){
        CourseDao courseDao = db.courseDao();
        List<Course> course = courseDao.getAll();
        return course.get(0).getCourseTitle();
    }

    private String getCourseStartDateFromDB(){
        CourseDao courseDao = db.courseDao();
        List<Course> course = courseDao.getAll();
        return course.get(0).getCourseDateStart();
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
        getView().getCourses(getCourseTitleFromDB(),getCourseStartDateFromDB());
    }
}
