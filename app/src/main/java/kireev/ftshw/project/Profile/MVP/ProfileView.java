package kireev.ftshw.project.Profile.MVP;

public interface ProfileView {

    void showProfile(ProfileData.User user, String status);

    void showError(String message);
}
