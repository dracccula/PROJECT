package kireev.ftshw.project.Database.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Profile {

    private String birthday;
    private String email;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phoneMobile;
    private String shirtSize;
    private Boolean isClient;
    private String skypeLogin;
    private String description;
    private String region;
    private String school;
    private long schoolGraduation;
    private String university;
    private String faculty;
    private long universityGraduation;
    private String grade;
    private String department;
    private String currentWork;
    private String resume;
    @PrimaryKey
    private long id;
    private Boolean admin;
    private String avatar;

    public Profile(long id, String firstName, String lastName, String middleName, String email, String birthday, String phoneMobile, String description, String region, String school, long schoolGraduation, String university, String faculty, long universityGraduation, String grade, String department, String currentWork, String avatar, String resume, String skypeLogin, Boolean isClient, String shirtSize, Boolean admin){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.birthday = birthday;
        this.phoneMobile = phoneMobile;
        this.description = description;
        this.region = region;
        this.school = school;
        this.schoolGraduation = schoolGraduation;
        this.university = university;
        this.faculty = faculty;
        this.universityGraduation = universityGraduation;
        this.grade = grade;
        this.department = department;
        this.currentWork = currentWork;
        this.avatar = avatar;
        this.resume = resume;
        this.skypeLogin = skypeLogin;
        this.isClient = isClient;
        this.shirtSize = shirtSize;
        this.admin = admin;
    }
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public String getShirtSize() {
        return shirtSize;
    }

    public void setShirtSize(String tShirtSize) {
        this.shirtSize = shirtSize;
    }

    public Boolean getClient() {
        return isClient;
    }

    public void setClient(Boolean client) {
        isClient = client;
    }

    public String getSkypeLogin() {
        return skypeLogin;
    }

    public void setSkypeLogin(String skypeLogin) {
        this.skypeLogin = skypeLogin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public long getSchoolGraduation() {
        return schoolGraduation;
    }

    public void setSchoolGraduation(Integer schoolGraduation) {
        this.schoolGraduation = schoolGraduation;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public long getUniversityGraduation() {
        return universityGraduation;
    }

    public void setUniversityGraduation(Integer universityGraduation) {
        this.universityGraduation = universityGraduation;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCurrentWork() {
        return currentWork;
    }

    public void setCurrentWork(String currentWork) {
        this.currentWork = currentWork;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
