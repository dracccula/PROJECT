package kireev.ftshw.project.Courses.Rating.Tasks.MVP;

import android.support.v7.widget.RecyclerView;

import kireev.ftshw.project.Courses.Rating.Adapters.TaskAdapter;

public interface TasksView {

    TaskAdapter getTaskAdapter();

    RecyclerView getRecyclerViewTasks();

    int getHomeworkId();
}
