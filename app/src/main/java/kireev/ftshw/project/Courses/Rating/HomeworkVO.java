package kireev.ftshw.project.Courses.Rating;

public class HomeworkVO {
    private int id;
    private String title;

    public HomeworkVO (int sId, String sTitle){
        id = sId;
        title = sTitle;
    }

    public HomeworkVO() {

    }

    public int getHomeworkId(){
        return id;
    }

    public void setHomeworkId(int sId){
        id = sId;
    }

    public String getHomeworkTitle() {
        return title;
    }

    public void setHomeworkTitle(String sTitle) {
        title = sTitle;
    }

}