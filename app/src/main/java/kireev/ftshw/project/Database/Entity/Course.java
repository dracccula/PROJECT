package kireev.ftshw.project.Database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Course {
    @PrimaryKey
    @NonNull
    private String courseUrl;
    private String courseTitle;
    private String courseStatus;
    private String courseDateStart;

    public Course(String courseUrl, String courseTitle, String courseStatus, String courseDateStart) {
        this.courseUrl = courseUrl;
        this.courseTitle = courseTitle;
        this.courseStatus = courseStatus;
        this.courseDateStart = courseDateStart;
    }

    public String getCourseUrl() {
        return courseUrl;
    }

    public void setCourseUrl(String courseUrl) {
        this.courseUrl = courseUrl;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getCourseDateStart() {
        return courseDateStart;
    }

    public void setCourseDateStart(String courseDateStart) {
        this.courseDateStart = courseDateStart;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
}
