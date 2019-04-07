package kireev.ftshw.project.Courses.Rating.Adapters;

public class TaskVO {
    private int id;
    private int tasksId;
    private String tasksStatus;
    private String tasksMark;
    private String title;
    private String task_type;
    private String max_score;
    private String deadline_date;
    private String short_name;

    public TaskVO(int sId, int sTasksId, String sTasksStatus, String sTasksMark, String sTitle, String sTask_type, String sMax_score, String sDeadline_date, String sShort_name){
        id = sId;
        tasksId = sTasksId;
        tasksStatus = sTasksStatus;
        tasksMark = sTasksMark;
        title = sTitle;
        task_type = sTask_type;
        max_score = sMax_score;
        deadline_date = sDeadline_date;
        short_name = sShort_name;
    }

    public TaskVO() {

    }

    public int getTaskId() {
        return id;
    }

    public void setTaskId(int id) {
        this.id = id;
    }

    public int getTasksId() {
        return tasksId;
    }

    public void setTasksId(int tasksId) {
        this.tasksId = tasksId;
    }

    public String getTasksStatus() {
        return tasksStatus;
    }

    public void setTasksStatus(String tasksStatus) {
        this.tasksStatus = tasksStatus;
    }

    public String getTasksMark() {
        return tasksMark;
    }

    public void setTasksMark(String tasksMark) {
        this.tasksMark = tasksMark;
    }

    public String getTaskTitle() {
        return title;
    }

    public void setTaskTitle(String title) {
        this.title = title;
    }

    public String getTaskTask_type() {
        return task_type;
    }

    public void setTaskTask_type(String task_type) {
        this.task_type = task_type;
    }

    public String getTaskMax_score() {
        return max_score;
    }

    public void setTaskMax_score(String max_score) {
        this.max_score = max_score;
    }

    public String getTaskDeadline_date() {
        return deadline_date;
    }

    public void setTaskDeadline_date(String deadline_date) {
        this.deadline_date = deadline_date;
    }

    public String getTaskShort_name() {
        return short_name;
    }

    public void setTaskShort_name(String short_name) {
        this.short_name = short_name;
    }
}