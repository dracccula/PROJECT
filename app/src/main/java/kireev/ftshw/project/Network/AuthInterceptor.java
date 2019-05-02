package kireev.ftshw.project.Network;


import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.annotation.NonNull;

import java.io.IOException;

import kireev.ftshw.project.MainActivity;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private SharedPreferences preferences;

    public AuthInterceptor(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        //        Задержка запроса на 2 секунды
        //       SystemClock.sleep(3000);
        Request request = chain.request();
        if (preferences == null) {
            return chain.proceed(request);
        } else {
            String anygen = preferences.getString("anygenCookie", "");
            Request newRequest = request.newBuilder().addHeader("Cookie", anygen).build();
            return chain.proceed(newRequest);
        }
    }
}
