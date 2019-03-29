package kireev.ftshw.project.Network;

import android.content.Context;
import android.content.SharedPreferences;
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
        Request request = chain.request();
        if (preferences == null) {
            return chain.proceed(request);
        } else {
            //preferences = getBaseContext().getPreferences(Context.MODE_PRIVATE);
            //Request newRequest = request.newBuilder().addHeader().build();
            return chain.proceed(request);
        }
    }
}
