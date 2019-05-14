package kireev.ftshw.project.Profile.Login;

public interface LoginView {

    void closeActivity();
    void showError(String message);
    void showProgress();
    void hideProgress();
}
