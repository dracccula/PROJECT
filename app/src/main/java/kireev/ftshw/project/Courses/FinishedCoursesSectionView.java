package kireev.ftshw.project.Courses;

import com.hannesdorfmann.mosby3.mvp.MvpView;

interface FinishedCoursesSectionView extends MvpView {
    void getCourses(String title, String dateStart);
    void hideCoursesProgressBar();
}
