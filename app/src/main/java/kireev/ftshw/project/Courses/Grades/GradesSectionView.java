package kireev.ftshw.project.Courses.Grades;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

interface GradesSectionView extends MvpView {

    void showGrades(List<GradesVO> gradesVOList);

    void hideProgressBar();

}
