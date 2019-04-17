package kireev.ftshw.project.Database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity//(foreignKeys = @ForeignKey(entity = Homeworks.class, parentColumns = "id", childColumns = "homeworkId", onDelete = CASCADE, onUpdate = CASCADE, deferred = true))
public class Tasks {

    @PrimaryKey
    private long id;

    private long tasksId;

    public long homeworkId;

    public String status;

    public String mark;

    private String title;

    private String task_type;

    private String max_score;

    private String deadline_date;

    private String short_name;

    public Tasks(long id, long tasksId, long homeworkId, String status, String mark, String title, String task_type, String max_score, String deadline_date, String short_name) {
        this.id = id;
        this.tasksId = tasksId;
        this.homeworkId = homeworkId;
        this.status = status;
        this.mark = mark;
        this.title = title;
        this.task_type = task_type;
        this.max_score = max_score;
        this.deadline_date = deadline_date;
        this.short_name = short_name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTasksId() {
        return tasksId;
    }

    public void setTasksId(long tasksId) {
        this.tasksId = tasksId;
    }

    public long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTask_type() {
        return task_type;
    }

    public void setTask_type(String task_type) {
        this.task_type = task_type;
    }

    public String getMax_score() {
        return max_score;
    }

    public void setMax_score(String max_score) {
        this.max_score = max_score;
    }

    public String getDeadline_date() {
        return deadline_date;
    }

    public void setDeadline_date(String deadline_date) {
        this.deadline_date = deadline_date;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }
}