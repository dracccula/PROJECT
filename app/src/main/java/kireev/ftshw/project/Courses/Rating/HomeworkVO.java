package kireev.ftshw.project.Courses.Rating;

import kireev.ftshw.project.Network.Model.HomeworksResponse;

public class HomeworkVO {
    private int HomeworkId;
    private String HomeworkTitle;

    public HomeworkVO (int sId, String sTitle){
        HomeworkId = sId;
        HomeworkTitle = sTitle;
    }

    public HomeworkVO() {

    }

    public int getHomeworkId(){
        return HomeworkId;
    }

    public void setHomeworkId(int sId){
        HomeworkId = sId;
    }

    public String getHomeworkTitle() {
        return HomeworkTitle;
    }

    public void setHomeworkTitle(String sTitle) {
        HomeworkTitle = sTitle;
    }

}