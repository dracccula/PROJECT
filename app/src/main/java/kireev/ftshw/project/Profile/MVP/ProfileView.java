package kireev.ftshw.project.Profile.MVP;

public interface ProfileView {

    void showUser(ProfileData.User user);

    void showError(String message);
}
