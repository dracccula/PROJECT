package kireev.ftshw.project.Network;

import kireev.ftshw.project.Network.Model.HomeworksResponse;
import kireev.ftshw.project.Profile.Login.LoginData;
import kireev.ftshw.project.Profile.Login.SignInResponse;
import kireev.ftshw.project.Profile.MVP.ProfileData;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static kireev.ftshw.project.Network.Urls.HOMEWORKS;
import static kireev.ftshw.project.Network.Urls.SIGNIN;
import static kireev.ftshw.project.Network.Urls.SIGNOUT;
import static kireev.ftshw.project.Network.Urls.USER;

public interface FintechAPI {
    @POST(SIGNIN)
    Call<SignInResponse> postCredentials(
            @Body LoginData logIn
    );

    @POST(SIGNOUT)
    Call<SignInResponse> postEmpty();

    @GET(USER)
    Call<ProfileData> getUser();

    @GET(HOMEWORKS)
    Call<HomeworksResponse> getHomeworks();
}
