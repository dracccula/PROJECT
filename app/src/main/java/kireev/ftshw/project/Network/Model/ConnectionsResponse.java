package kireev.ftshw.project.Network.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConnectionsResponse {

    @SerializedName("active")
    @Expose
    private List<Object> active = null;
    @SerializedName("archive")
    @Expose
    private List<Object> archive = null;
    @SerializedName("courses")
    @Expose
    private List<Course> courses = null;

    public List<Object> getActive() {
        return active;
    }

    public void setActive(List<Object> active) {
        this.active = active;
    }

    public List<Object> getArchive() {
        return archive;
    }

    public void setArchive(List<Object> archive) {
        this.archive = archive;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public class Course {

        @SerializedName("is_teacher")
        @Expose
        private Boolean isTeacher;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("pending_tasks")
        @Expose
        private PendingTasks pendingTasks;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("event_date_start")
        @Expose
        private String eventDateStart;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("diploma_url")
        @Expose
        private String diplomaUrl;

        public Boolean getIsTeacher() {
            return isTeacher;
        }

        public void setIsTeacher(Boolean isTeacher) {
            this.isTeacher = isTeacher;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public PendingTasks getPendingTasks() {
            return pendingTasks;
        }

        public void setPendingTasks(PendingTasks pendingTasks) {
            this.pendingTasks = pendingTasks;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getEventDateStart() {
            return eventDateStart;
        }

        public void setEventDateStart(String eventDateStart) {
            this.eventDateStart = eventDateStart;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDiplomaUrl() {
            return diplomaUrl;
        }

        public void setDiplomaUrl(String diplomaUrl) {
            this.diplomaUrl = diplomaUrl;
        }

    }

    public class PendingTasks {

        @SerializedName("lecture_tests")
        @Expose
        private List<Object> lectureTests = null;
        @SerializedName("tasks")
        @Expose
        private List<Object> tasks = null;

        public List<Object> getLectureTests() {
            return lectureTests;
        }

        public void setLectureTests(List<Object> lectureTests) {
            this.lectureTests = lectureTests;
        }

        public List<Object> getTasks() {
            return tasks;
        }

        public void setTasks(List<Object> tasks) {
            this.tasks = tasks;
        }

    }

}
