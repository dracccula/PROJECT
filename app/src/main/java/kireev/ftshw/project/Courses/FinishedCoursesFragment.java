package kireev.ftshw.project.Courses;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kireev.ftshw.project.R;

public class FinishedCoursesFragment extends Fragment {

    private FinishedCoursesViewModel mViewModel;

    public static FinishedCoursesFragment newInstance() {
        return new FinishedCoursesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.finished_courses_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FinishedCoursesViewModel.class);
        // TODO: Use the ViewModel
    }

}
