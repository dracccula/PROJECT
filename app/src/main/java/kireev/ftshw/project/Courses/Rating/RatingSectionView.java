package kireev.ftshw.project.Courses.Rating;

import com.hannesdorfmann.mosby3.mvp.MvpView;

interface RatingSectionView extends MvpView {

    void showRating(int profilePoints, int allStudents, int studentPosition, int acceptedTests, int allTests, int acceptedHomeworks, int allHomeworks, int allLessons, int lessonsDone, int lessonsLeft);

    void hideProgressBar();
}
