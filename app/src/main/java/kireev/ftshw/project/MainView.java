package kireev.ftshw.project;

import android.view.View;

import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface MainView extends MvpView {

    void setActionBarTitle(String title);
    void gradesButtonClick(View view);
    void ratingButtonClick(View view);
    void openLoginActivity(View view);
}
