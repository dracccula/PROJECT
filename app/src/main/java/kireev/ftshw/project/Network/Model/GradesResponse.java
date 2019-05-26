package kireev.ftshw.project.Network.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GradesResponse {

    @SerializedName("grouped_tasks")
    @Expose
    private List<List<GroupedTask>> groupedTasks = null;
    @SerializedName("grades")
    @Expose
    private List<Grades> grades = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mentor")
    @Expose
    private Object mentor;
    @SerializedName("mentor_id")
    @Expose
    private Object mentorId;
    @SerializedName("id")
    @Expose
    private Integer id;

    public List<List<GroupedTask>> getGroupedTasks() {
        return groupedTasks;
    }

    public void setGroupedTasks(List<List<GroupedTask>> groupedTasks) {
        this.groupedTasks = groupedTasks;
    }

    public List<Grades> getGrades() {
        return grades;
    }

    public void setGrades(List<Grades> grades) {
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getMentor() {
        return mentor;
    }

    public void setMentor(Object mentor) {
        this.mentor = mentor;
    }

    public Object getMentorId() {
        return mentorId;
    }

    public void setMentorId(Object mentorId) {
        this.mentorId = mentorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public class Grades {

        @SerializedName("student")
        @Expose
        private String student;
        @SerializedName("student_id")
        @Expose
        private Integer studentId;
        @SerializedName("grades")
        @Expose
        private List<StudentGrade> grades = null;
        @SerializedName("group_id")
        @Expose
        private Integer groupId;

        public String getStudent() {
            return student;
        }

        public void setStudent(String student) {
            this.student = student;
        }

        public Integer getStudentId() {
            return studentId;
        }

        public void setStudentId(Integer studentId) {
            this.studentId = studentId;
        }

        public List<StudentGrade> getGrades() {
            return grades;
        }

        public void setGrades(List<StudentGrade> grades) {
            this.grades = grades;
        }

        public Integer getGroupId() {
            return groupId;
        }

        public void setGroupId(Integer groupId) {
            this.groupId = groupId;
        }

    }

    public class StudentGrade {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("mark")
        @Expose
        private Double mark;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("task_type")
        @Expose
        private String taskType;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Double getMark() {
            return mark;
        }

        public void setMark(Double mark) {
            this.mark = mark;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTaskType() {
            return taskType;
        }

        public void setTaskType(String taskType) {
            this.taskType = taskType;
        }

    }

    public class GroupedTask {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("short_name")
        @Expose
        private String shortName;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("max_score")
        @Expose
        private Double maxScore;
        @SerializedName("contest__status")
        @Expose
        private Integer contestStatus;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Double getMaxScore() {
            return maxScore;
        }

        public void setMaxScore(Double maxScore) {
            this.maxScore = maxScore;
        }

        public Integer getContestStatus() {
            return contestStatus;
        }

        public void setContestStatus(Integer contestStatus) {
            this.contestStatus = contestStatus;
        }

    }
}