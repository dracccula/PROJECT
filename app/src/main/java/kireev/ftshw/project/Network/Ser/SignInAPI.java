package kireev.ftshw.project.Network.Ser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SignInAPI {
    @FormUrlEncoded
    @POST("/sigin")
    Call<SignIn> postCredentials(
            @Field("email") String email,
            @Field("password") String password
    );
}
