package kireev.ftshw.project.Courses.FinishedCourses;

import com.hannesdorfmann.mosby3.mvp.MvpView;

interface FinishedCoursesSectionView extends MvpView {
    void showCourses(String title, String dateStart, String points);
    void hideCoursesProgressBar();
}
