package kireev.ftshw.project.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

import okhttp3.CookieJar;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
        //If condition to ensure we don't create multiple retrofit instances in a single application
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder().cookieJar(CookieJar.NO_COOKIES).build();

            //Defining the Retrofit using Builder
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) //This is the only mandatory call on Builder object.
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create()) // Convertor library used to convert response into POJO
                    .build();
        }
        return retrofit;
    }
}
