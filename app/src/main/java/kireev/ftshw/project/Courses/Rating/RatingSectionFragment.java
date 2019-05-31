package kireev.ftshw.project.Courses.Rating;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import kireev.ftshw.project.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RatingSectionFragment extends MvpFragment<RatingSectionView, RatingSectionPresenter> implements RatingSectionView {

    TextView tvPointsValue, tvRatingValue, tvTestsValue, tvHomeworksValue, tvLessonsValue, tvLessonsDoneValue, tvLessonsLeftValue;

    ProgressBar pbLessons, pbRating;

    ConstraintLayout clRating;


    public RatingSectionFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public RatingSectionPresenter createPresenter() {
        return new RatingSectionPresenter();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_rating_section, container, false);
        tvPointsValue = v.findViewById(R.id.tvPointsValue);
        tvRatingValue = v.findViewById(R.id.tvRatingValue);
        tvTestsValue = v.findViewById(R.id.tvTestsValue);
        tvHomeworksValue = v.findViewById(R.id.tvHomeworksValue);
        tvLessonsValue = v.findViewById(R.id.tvLessonsValue);
        tvLessonsDoneValue = v.findViewById(R.id.tvLessonsDoneValue);
        tvLessonsLeftValue = v.findViewById(R.id.tvLessonsLeftValue);
        pbLessons = v.findViewById(R.id.pbLessons);
        pbRating = v.findViewById(R.id.pbRating);
        clRating = v.findViewById(R.id.clRating);
        clRating.setVisibility(View.GONE);
        return v;
    }

    @Override
    public void showRating(int profilePoints, int allStudents, int studentPosition, int acceptedTests, int allTests, int acceptedHomeworks, int allHomeworks, int allLessons, int lessonsDone, int lessonsLeft) {
        tvPointsValue.setText(profilePoints + "");
        tvRatingValue.setText(studentPosition + "/" + allStudents);
        tvTestsValue.setText(acceptedTests + "/" + allTests);
        tvHomeworksValue.setText(acceptedHomeworks + "/" + allHomeworks);
        tvLessonsValue.setText(allLessons + "");
        pbLessons.setMax(0);
        pbLessons.setMax(allLessons);
        pbLessons.setProgress(lessonsDone);
        tvLessonsDoneValue.setText(lessonsDone + "");
        tvLessonsLeftValue.setText(lessonsLeft + "");
        pbRating.setVisibility(View.GONE);
        clRating.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        pbRating.setVisibility(View.GONE);
    }
}
