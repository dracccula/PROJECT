package kireev.ftshw.project.Database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Task {

    @PrimaryKey
    private long id;

    private long tasksId;

    private String title;

    private String task_type;

    private String max_score;

    private String deadline_date;

    private String short_name;

    public Task(long id, long tasksId, String title, String task_type, String max_score, String deadline_date, String short_name) {
        this.id = id;
        this.tasksId = tasksId;
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
