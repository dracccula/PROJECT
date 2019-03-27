package kireev.ftshw.project.Network.Ser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static kireev.ftshw.project.Network.Urls.SIGNIN;

public interface SignInAPI {
    @POST(SIGNIN)
    Call<SignInResponse> postCredentials(
            @Body SignIn signIn
    );
}
