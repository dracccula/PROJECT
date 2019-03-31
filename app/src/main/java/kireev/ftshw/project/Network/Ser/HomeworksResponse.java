package kireev.ftshw.project.Network.Ser;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeworksResponse {

    @SerializedName("homeworks")
    @Expose
    private List<Homework> homeworks = null;

    public List<Homework> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(List<Homework> homeworks) {
        this.homeworks = homeworks;
    }

    public class Homework {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("tasks")
        @Expose
        private List<Tasks> tasks = null;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Tasks> getTasks() {
            return tasks;
        }

        public void setTasks(List<Tasks> tasks) {
            this.tasks = tasks;
        }

    }

    public class Tasks {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("task")
        @Expose
        private Task task;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("mark")
        @Expose
        private String mark;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Task getTask() {
            return task;
        }

        public void setTask(Task task) {
            this.task = task;
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

    }

    public class Task {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("task_type")
        @Expose
        private String taskType;
        @SerializedName("max_score")
        @Expose
        private String maxScore;
        @SerializedName("deadline_date")
        @Expose
        private String deadlineDate;
        @SerializedName("contest_info")
        @Expose
        private Object contestInfo;
        @SerializedName("short_name")
        @Expose
        private String shortName;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTaskType() {
            return taskType;
        }

        public void setTaskType(String taskType) {
            this.taskType = taskType;
        }

        public String getMaxScore() {
            return maxScore;
        }

        public void setMaxScore(String maxScore) {
            this.maxScore = maxScore;
        }

        public String getDeadlineDate() {
            return deadlineDate;
        }

        public void setDeadlineDate(String deadlineDate) {
            this.deadlineDate = deadlineDate;
        }

        public Object getContestInfo() {
            return contestInfo;
        }

        public void setContestInfo(Object contestInfo) {
            this.contestInfo = contestInfo;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

    }
}