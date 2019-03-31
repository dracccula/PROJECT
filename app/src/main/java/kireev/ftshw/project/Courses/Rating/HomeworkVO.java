package kireev.ftshw.project.Courses.Rating;

public class HomeworkVO {
    private String HomeworkTitle;

    public HomeworkVO (String sTitle){
        HomeworkTitle = sTitle;
    }

    public HomeworkVO() {

    }

    public String getContactName() {
        return HomeworkTitle;
    }

    public void setContactName(String sTitle) {
        HomeworkTitle = sTitle;
    }

}