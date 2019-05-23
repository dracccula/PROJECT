package kireev.ftshw.project.Network;

import kireev.ftshw.project.Network.Model.ConnectionsResponse;
import kireev.ftshw.project.Network.Model.EventsResponse;
import kireev.ftshw.project.Network.Model.HomeworksResponse;
import kireev.ftshw.project.Login.LoginData;
import kireev.ftshw.project.Login.SignInResponse;
import kireev.ftshw.project.Profile.MVP.ProfileData;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static kireev.ftshw.project.Network.Urls.ABOUT;
import static kireev.ftshw.project.Network.Urls.CONNECTIONS;
import static kireev.ftshw.project.Network.Urls.COURSE;
import static kireev.ftshw.project.Network.Urls.EVENTS;
import static kireev.ftshw.project.Network.Urls.HOMEWORKS;
import static kireev.ftshw.project.Network.Urls.SIGNIN;
import static kireev.ftshw.project.Network.Urls.USER;

public interface FintechAPI {
    @POST(SIGNIN)
    Call<SignInResponse> postCredentials(
            @Body LoginData logIn
    );

    @GET(USER)
    Call<ProfileData> getUser();

    @GET(HOMEWORKS)
    Call<HomeworksResponse> getHomeworks();

    @GET(EVENTS)
    Call<EventsResponse> getEventsList();

    @GET(CONNECTIONS)
    Call<ConnectionsResponse> getConnections();

//    @GET(ABOUT)
//    Call<> getAbout();
}
