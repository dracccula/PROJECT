package kireev.ftshw.project.Courses.ui.gradeslist;

public class ContactVO {
    private String ContactName;

    public ContactVO (String sContactName){
        ContactName = sContactName;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

}