package kireev.ftshw.project.Courses.Rating.Adapters;

public class TaskVO {
    private int id;
    private String title;
    private String task_type;
    private String max_score;
    private String deadline_date;
    private String short_name;


    public TaskVO(int sId, String sTitle, String sTask_type, String sMax_score, String sDeadline_date, String sShort_name){
        id = sId;
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