package kireev.ftshw.project.Courses;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import kireev.ftshw.project.R;

public class FinishedCoursesSectionFragment extends MvpFragment<FinishedCoursesSectionView, FinishedCoursesSectionPresenter> implements FinishedCoursesSectionView {

    TextView tvCourseTitle, tvCourseDate, tvCoursePoints;
    RecyclerView rvCourses;
    CoursesAdapter coursesAdapter;

    @NonNull
    @Override
    public FinishedCoursesSectionPresenter createPresenter() {
        return new FinishedCoursesSectionPresenter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_finished_courses_section, container, false);
        tvCourseDate = v.findViewById(R.id.tvCourseDate);
        tvCourseTitle = v.findViewById(R.id.tvCourseTitle);
        tvCoursePoints = v.findViewById(R.id.tvCoursePoints);
        rvCourses = v.findViewById(R.id.rvCourses);
        rvCourses.setLayoutManager(new LinearLayoutManager(getContext()));
        coursesAdapter = new CoursesAdapter(getContext());
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.viewIsReady();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void getCourses(String title, String dateStart) {
        tvCourseTitle.setText("555");
        tvCourseDate.setText("111");
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
