package kireev.ftshw.project.Courses.Rating.Adapters;

public class TasksVO {
    private int id;
    private String status;
    private String mark;

    public TasksVO(int sId, String sStatus, String sMark){
        id = sId;
        status = sStatus;
        mark = sMark;

    }

    public TasksVO() {

    }

    public int getTasksId() {
        return id;
    }

    public void setTasksId(int id) {
        this.id = id;
    }

    public String getTasksStatus() {
        return status;
    }

    public void setTasksStatus(String status) {
        this.status = status;
    }

    public String getTasksMark() {
        return mark;
    }

    public void setTasksMark(String mark) {
        this.mark = mark;
    }
}