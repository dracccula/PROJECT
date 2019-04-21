package kireev.ftshw.project.Profile.Login;

public interface LoginView {

    void authorize(SignInResponse signInResponse);

    void showError(String message);
}
