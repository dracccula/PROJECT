package kireev.ftshw.project.Courses.GradesList;

public class ContactVO {
    private String ContactName;

    public ContactVO (String sContactName){
        ContactName = sContactName;
    }

    public ContactVO() {

    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

}