package kireev.ftshw.project.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.riversun.okhttp3.OkHttp3CookieHelper;


import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static kireev.ftshw.project.Network.Urls.BASE_URL;

public class Connector {

    public Connector() {
    }


    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static Retrofit retrofit;

    /*
    This public static method will return Retrofit client
    anywhere in the appplication
    */
    public static Retrofit getRetrofitClient() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            //AuthInterceptor authInterceptor = new AuthInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //CookieHandler cookieHandler = new CookieManager();
            OkHttp3CookieHelper cookieHelper = new OkHttp3CookieHelper();
            //cookieHelper.setCookie(BASE_URL, "anygen", anygenCookie);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addNetworkInterceptor(interceptor)
                    //.addInterceptor(authInterceptor)
                    .cookieJar(cookieHelper.cookieJar())
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
