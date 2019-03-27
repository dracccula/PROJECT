package kireev.ftshw.project.Network;

import kireev.ftshw.project.Network.Ser.SignIn;
import kireev.ftshw.project.Network.Ser.SignInResponse;
import kireev.ftshw.project.Network.Ser.UserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static kireev.ftshw.project.Network.Urls.SIGNIN;
import static kireev.ftshw.project.Network.Urls.USER;

public interface FintechAPI {
    @POST(SIGNIN)
    Call<SignInResponse> postCredentials(
            @Body SignIn signIn
    );

    @GET(USER)
    Call<UserResponse> getUser(
    );
}
