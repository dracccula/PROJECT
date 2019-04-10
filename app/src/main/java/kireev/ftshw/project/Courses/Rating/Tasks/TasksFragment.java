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

import java.util.ArrayList;
import java.util.List;

import kireev.ftshw.project.App;
import kireev.ftshw.project.Courses.Rating.Adapters.TaskAdapter;
import kireev.ftshw.project.Courses.Rating.Adapters.TaskVO;
import kireev.ftshw.project.Database.Dao.TasksDao;
import kireev.ftshw.project.Database.Entity.Tasks;
import kireev.ftshw.project.Database.ProjectDatabase;
import kireev.ftshw.project.R;

import static kireev.ftshw.project.Courses.Rating.Tasks.TasksActivity.HOMEWORK_ID;
import static kireev.ftshw.project.Courses.Rating.Tasks.TasksActivity.HOMEWORK_TITLE;

public class TasksFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView rvTasks;
    TaskAdapter taskAdapter;
    TextView homeworkTitle;
    ProjectDatabase db;
    int homeworkId;
    public final List<TaskVO> taskVOList = new ArrayList<>();

    public TasksFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tasks, container, false);
        homeworkTitle = v.findViewById(R.id.tvHomeworkTitle);
        homeworkTitle.setText(getActivity().getIntent().getStringExtra(HOMEWORK_TITLE).toUpperCase());
        homeworkId = getActivity().getIntent().getIntExtra(String.valueOf(TasksActivity.HOMEWORK_ID), HOMEWORK_ID);
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
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showFromDatabase();
    }

    private void showFromDatabase() {
        db = App.getInstance().getDatabase();
        TasksDao tasksDao = db.tasksDao();
        List<Tasks> tasksList;
        tasksList = tasksDao.getByHomeworkId(homeworkId);
        for (int i = 0; i < tasksList.size(); i++) {
            TaskVO taskVO = new TaskVO();
            taskVO.setTaskId((int) tasksList.get(i).getId());
            taskVO.setTasksId((int) tasksList.get(i).getTasksId());
            taskVO.setTasksStatus(tasksList.get(i).status);
            taskVO.setTasksMark(tasksList.get(i).mark);
            taskVO.setTaskTitle(tasksList.get(i).getTitle());
            taskVO.setTaskTask_type(tasksList.get(i).getTask_type());
            taskVO.setTaskMax_score(tasksList.get(i).getMax_score());
            taskVO.setTaskDeadline_date(tasksList.get(i).getDeadline_date());
            taskVO.setTaskShort_name(tasksList.get(i).getShort_name());
            taskVOList.add(taskVO);
        }
        taskAdapter.setItems(taskVOList);
        rvTasks.setAdapter(taskAdapter);
        Log.i("taskAdapter", "attached! " + taskVOList.toString());
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
        Log.d("onRefresh", "refreshed!");
    }
}
