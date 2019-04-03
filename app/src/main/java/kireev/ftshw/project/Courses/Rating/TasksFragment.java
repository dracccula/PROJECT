package kireev.ftshw.project.Courses.Rating;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kireev.ftshw.project.R;

import static kireev.ftshw.project.Courses.Rating.TasksActivity.HOMEWORK_TITLE;

/**
 * A simple {@link Fragment} subclass.
 */
public class TasksFragment extends Fragment {
    TextView homeworkTitle;

    public TasksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tasks, container, false);
        homeworkTitle = v.findViewById(R.id.tvHomeworkTitle);
        homeworkTitle.setText(getActivity().getIntent().getStringExtra(HOMEWORK_TITLE));
        return v;
    }

}
