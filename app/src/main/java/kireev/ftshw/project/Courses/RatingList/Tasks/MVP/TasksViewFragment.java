package kireev.ftshw.project.Courses.RatingList.Tasks.MVP;

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

import java.util.Objects;

import kireev.ftshw.project.Courses.RatingList.Adapters.TaskAdapter;
import kireev.ftshw.project.Courses.RatingList.Adapters.TaskVO;
import kireev.ftshw.project.Courses.RatingList.Tasks.TasksActivity;
import kireev.ftshw.project.R;

import static kireev.ftshw.project.Courses.RatingList.Tasks.TasksActivity.HOMEWORK_ID;
import static kireev.ftshw.project.Courses.RatingList.Tasks.TasksActivity.HOMEWORK_TITLE;

public class TasksViewFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, TasksView {
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView rvTasks;
    TaskAdapter taskAdapter;
    TextView homeworkTitle;

    TasksPresenter presenter;


    public TasksViewFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tasks, container, false);
        homeworkTitle = v.findViewById(R.id.tvHomeworkTitle);
        homeworkTitle.setText(Objects.requireNonNull(getActivity()).getIntent().getStringExtra(HOMEWORK_TITLE).toUpperCase());
        rvTasks = v.findViewById(R.id.rvTasks);
        rvTasks.setLayoutManager(new LinearLayoutManager(getContext()));
        mSwipeRefreshLayout = v.findViewById(R.id.refreshTasksRV);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        TaskAdapter.OnClickListener onClickListener = new TaskAdapter.OnClickListener() {
            @Override
            public void onClick(TaskVO taskVO) {
            }
        };
        taskAdapter = new TaskAdapter(onClickListener, getContext());
        TasksModel tasksModel = new TasksModel();
        presenter = new TasksPresenter(tasksModel);
        presenter.attachView(this);
        Log.e("Tasks onCreateView", "view attached");
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.showFromDatabase();
    }



    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
        Log.d("onRefresh", "refreshed!");
    }

    @Override
    public TaskAdapter getTaskAdapter() {
        return taskAdapter;
    }

    @Override
    public RecyclerView getRecyclerViewTasks() {
        return rvTasks;
    }

    @Override
    public int getHomeworkId() {
        int homeworkId = Objects.requireNonNull(getActivity()).getIntent().getIntExtra(String.valueOf(TasksActivity.HOMEWORK_ID), HOMEWORK_ID);
        return homeworkId;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
        Log.e("Tasks onDestroyView", "view detached");
    }
}
