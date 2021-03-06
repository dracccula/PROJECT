package kireev.ftshw.project.Profile.MVP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ProfileData {

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String status) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class User {

        @SerializedName("birthday")
        @Expose
        private String birthday;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("middle_name")
        @Expose
        private String middleName;
        @SerializedName("phone_mobile")
        @Expose
        private String phoneMobile;
        @SerializedName("t_shirt_size")
        @Expose
        private String tShirtSize;
        @SerializedName("is_client")
        @Expose
        private Boolean isClient;
        @SerializedName("skype_login")
        @Expose
        private String skypeLogin;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("region")
        @Expose
        private String region;
        @SerializedName("school")
        @Expose
        private String school;
        @SerializedName("school_graduation")
        @Expose
        private Integer schoolGraduation;
        @SerializedName("university")
        @Expose
        private String university;
        @SerializedName("faculty")
        @Expose
        private String faculty;
        @SerializedName("university_graduation")
        @Expose
        private Integer universityGraduation;
        @SerializedName("grade")
        @Expose
        private String grade;
        @SerializedName("department")
        @Expose
        private String department;
        @SerializedName("current_work")
        @Expose
        private String currentWork;
        @SerializedName("resume")
        @Expose
        private String resume;
        @SerializedName("notifications")
        @Expose
        private List<Object> notifications = null;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("admin")
        @Expose
        private Boolean admin;
        @SerializedName("avatar")
        @Expose
        private String avatar;

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

        public String getTShirtSize() {
            return tShirtSize;
        }

        public void setTShirtSize(String tShirtSize) {
            this.tShirtSize = tShirtSize;
        }

        public Boolean getIsClient() {
            return isClient;
        }

        public void setIsClient(Boolean isClient) {
            this.isClient = isClient;
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

        public Integer getSchoolGraduation() {
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

        public Integer getUniversityGraduation() {
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

        public List<Object> getNotifications() {
            return notifications;
        }

        public void setNotifications(List<Object> notifications) {
            this.notifications = notifications;
        }

        public Integer getId() {
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
}
