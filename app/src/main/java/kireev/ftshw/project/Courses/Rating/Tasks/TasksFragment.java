package kireev.ftshw.project.Courses.Rating.Tasks;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import kireev.ftshw.project.Courses.Rating.Adapters.TaskAdapter;
import kireev.ftshw.project.Courses.Rating.Adapters.TaskVO;
import kireev.ftshw.project.R;

import static kireev.ftshw.project.Courses.Rating.Tasks.TasksActivity.HOMEWORK_TITLE;

public class TasksFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView rvHomeworks;
    TaskAdapter taskAdapter;
    TextView homeworkTitle;

    public TasksFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tasks, container, false);
        homeworkTitle = v.findViewById(R.id.tvHomeworkTitle);
        homeworkTitle.setText(getActivity().getIntent().getStringExtra(HOMEWORK_TITLE));

        rvHomeworks = v.findViewById(R.id.rvHomeworks);
        rvHomeworks.setLayoutManager(new LinearLayoutManager(getContext()));
        mSwipeRefreshLayout = v.findViewById(R.id.refreshTasksRV);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        TaskAdapter.OnClickListener onClickListener = new TaskAdapter.OnClickListener() {
            @Override
            public void onClick(TaskVO taskVO) {
                Toast.makeText(getContext(), "id " + taskVO.getTaskId(), Toast.LENGTH_SHORT).show();
            }
        };
        taskAdapter = new TaskAdapter(onClickListener, getContext());
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showFromDatabase();
    }

    private void showFromDatabase() {
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
        Log.d("onRefresh", "refreshed!");
    }
}
