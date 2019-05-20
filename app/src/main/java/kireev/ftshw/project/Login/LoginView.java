package kireev.ftshw.project.Login;

public interface LoginView {

    void closeActivity();
    void showError(String message);
    void showProgress();
    void hideProgress();
}
