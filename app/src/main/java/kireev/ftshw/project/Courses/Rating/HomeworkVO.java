package kireev.ftshw.project.Courses.Rating;

public class HomeworkVO {
    private String HomeworkTitle;

    public HomeworkVO (String sTitle){
        HomeworkTitle = sTitle;
    }

    public HomeworkVO() {

    }

    public String getHomeworkTitle() {
        return HomeworkTitle;
    }

    public void setHomeworkTitle(String sTitle) {
        HomeworkTitle = sTitle;
    }

}