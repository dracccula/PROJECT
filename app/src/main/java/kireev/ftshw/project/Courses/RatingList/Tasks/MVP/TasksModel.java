package kireev.ftshw.project.Courses.RatingList.Tasks.MVP;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import kireev.ftshw.project.App;
import kireev.ftshw.project.Courses.RatingList.Adapters.TaskAdapter;
import kireev.ftshw.project.Courses.RatingList.Adapters.TaskVO;
import kireev.ftshw.project.Database.Dao.TasksDao;
import kireev.ftshw.project.Database.Entity.Tasks;
import kireev.ftshw.project.Database.ProjectDatabase;

class TasksModel {

    private List<TaskVO> taskVOList = new ArrayList<>();

    void getTasks(int homeworkId, TaskAdapter taskAdapter, RecyclerView recyclerView) {
        ProjectDatabase db = App.getInstance().getDatabase();
        TasksDao tasksDao = db.tasksDao();
        List<Tasks> tasksList = tasksDao.getByHomeworkId(homeworkId);
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
        recyclerView.setAdapter(taskAdapter);
        Log.i("taskAdapter", "attached! " + taskVOList.toString());
    }
}
