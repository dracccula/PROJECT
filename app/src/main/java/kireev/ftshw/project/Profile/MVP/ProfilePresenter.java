package kireev.ftshw.project.Profile.MVP;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface ProfilePresenter extends MvpPresenter<ProfileView> {

    void refresh();
}
