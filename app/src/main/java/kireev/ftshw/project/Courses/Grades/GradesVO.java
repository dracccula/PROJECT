package kireev.ftshw.project.Courses.Grades;

public class GradesVO{

    private int id;
    private String name;
    private int points;
    private int color;
    private boolean isActiveUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isActiveUser() {
        return isActiveUser;
    }

    public void setActiveUser(boolean activeUser) {
        isActiveUser = activeUser;
    }
}
