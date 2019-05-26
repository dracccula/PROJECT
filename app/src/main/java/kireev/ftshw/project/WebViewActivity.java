package kireev.ftshw.project;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Objects;

import kireev.ftshw.project.Network.Connector;
import kireev.ftshw.project.Network.FintechAPI;
import kireev.ftshw.project.Network.Model.AboutResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static kireev.ftshw.project.MainActivity.spStorage;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;
    String activityTitle;
    String html;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activityTitle = "О курсе ";
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new MyWebViewClient());
        setTitle(activityTitle);
//        webView.getSettings().setJavaScriptEnabled(true);
//        getActionBar().setTitle(activityTitle);
//        webView.loadUrl("http://fintech.tinkoff.ru/course/" + spStorage.getString("courseUrl", "") + "/about");
        webView.loadDataWithBaseURL(null, html, "text/html",null,null);
        getAboutResponse();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d("Back", "clicked!");
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private class MyWebViewClient extends WebViewClient {
        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        // Для старых устройств
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


    public void getAboutResponse(){
        Retrofit retrofit = Connector.getRetrofitClient();
        FintechAPI fintechAPI = retrofit.create(FintechAPI.class);
        Call<AboutResponse> call = fintechAPI.getAbout(spStorage.getString("courseUrl", ""));
        call.enqueue(new Callback<AboutResponse>() {
            @Override
            public void onResponse(Call<AboutResponse> call, Response<AboutResponse> response) {
                if (getSupportFragmentManager().findFragmentById(R.id.ErrorFragment) != null){
                    //finish();
                    //recreate();
                }
                getSupportFragmentManager().popBackStack();
                AboutResponse aboutResponse = response.body();
                activityTitle = activityTitle + Objects.requireNonNull(aboutResponse).getTitle();
                setTitle(activityTitle);
                html = aboutResponse.getHtml();
                webView.loadDataWithBaseURL(null, html, "text/html",null,null);
            }

            @Override
            public void onFailure(Call<AboutResponse> call, Throwable t) {
                getSupportFragmentManager().beginTransaction().replace(R.id.webView,
                        new ErrorFragment()).commitNow();
            }
        });
    }
}
