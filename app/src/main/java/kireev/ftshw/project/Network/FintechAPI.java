package kireev.ftshw.project.Network;

import java.util.List;

import kireev.ftshw.project.Network.Model.AboutResponse;
import kireev.ftshw.project.Network.Model.ConnectionsResponse;
import kireev.ftshw.project.Network.Model.EventsResponse;
import kireev.ftshw.project.Network.Model.GradesResponse;
import kireev.ftshw.project.Network.Model.HomeworksResponse;
import kireev.ftshw.project.Login.LoginData;
import kireev.ftshw.project.Login.SignInResponse;
import kireev.ftshw.project.Profile.MVP.ProfileData;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


import static kireev.ftshw.project.Network.Urls.ABOUT;
import static kireev.ftshw.project.Network.Urls.CONNECTIONS;

import static kireev.ftshw.project.Network.Urls.COURSE;
import static kireev.ftshw.project.Network.Urls.EVENTS;
import static kireev.ftshw.project.Network.Urls.GRADES;
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

    @GET(COURSE + HOMEWORKS)
    Call<HomeworksResponse> getHomeworks(@Path("course_id") String courseId);

    @GET(EVENTS)
    Call<EventsResponse> getEventsList();

    @GET(CONNECTIONS)
    Call<ConnectionsResponse> getConnections();

    @GET(COURSE + ABOUT)
    Call<AboutResponse> getAbout(@Path("course_id") String courseId);

    @GET(COURSE + GRADES)
    Call<GradesResponse> getGrades(@Path("course_id") String courseId);
}
