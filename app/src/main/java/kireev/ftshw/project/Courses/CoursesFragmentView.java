package kireev.ftshw.project.Courses;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

import kireev.ftshw.project.Courses.Grades.GradesVO;

interface CoursesFragmentView extends MvpView {

    void stopRefreshing();

    void showGrades(List<GradesVO> gradesVOList);

    void showRating(int profilePoints, int allStudents, int studentPosition, int acceptedTests, int allTests, int acceptedHomeworks, int allHomeworks, int allLessons, int lessonsDone, int lessonsLeft);

    void showCourses(String courseTitleFromSP, String courseStartDateFromSP, String coursePointsFromSP);

    void hideGradesProgressBar();

    void hideRatingProgressBar();

    void hideCoursesProgressBar();
}