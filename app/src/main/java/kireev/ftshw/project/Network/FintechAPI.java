package kireev.ftshw.project.Network;

import java.util.List;

import kireev.ftshw.project.Network.Model.HomeworksResponse;
import kireev.ftshw.project.Network.Model.SignIn;
import kireev.ftshw.project.Network.Model.SignInResponse;
import kireev.ftshw.project.Network.Model.UserResponse;
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
            @Body SignIn signIn
    );

    @POST(SIGNOUT)
    Call<SignInResponse> postEmpty();

    @GET(USER)
    Call<UserResponse> getUser();

    @GET(HOMEWORKS)
    Call<HomeworksResponse> getHomeworks();
}
