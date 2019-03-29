package kireev.ftshw.project.Profile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import kireev.ftshw.project.Courses.ui.gradeslist.GradesListFragment;
import kireev.ftshw.project.MainActivity;
import kireev.ftshw.project.Network.Connector;
import kireev.ftshw.project.Network.FintechAPI;
import kireev.ftshw.project.Network.Ser.SignInResponse;
import kireev.ftshw.project.Network.Ser.UserResponse;
import kireev.ftshw.project.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static kireev.ftshw.project.Network.Urls.BASE_URL;
import static kireev.ftshw.project.Profile.ProfileEditFragment.STUDENT_NAME;
import static kireev.ftshw.project.Profile.ProfileEditFragment.STUDENT_PATRONYMIC;
import static kireev.ftshw.project.Profile.ProfileEditFragment.STUDENT_SURNAME;
import static kireev.ftshw.project.Profile.ProfileEditFragment.sPrefProfile;


public class ProfileFragment extends Fragment {

    Button editButton;
    ImageView avatar;
    static EditText name, surname, patronymic;
    private OnProfileFragmentListener listener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ((MainActivity) getActivity())
                .setActionBarTitle(getString(R.string.title_profile));
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        name = v.findViewById(R.id.textName);
        surname = v.findViewById(R.id.textSurname);
        patronymic = v.findViewById(R.id.textPatronymic);
//        loadText();
        editButton = v.findViewById(R.id.buttonEditFullname);
        avatar = v.findViewById(R.id.ivAvatar);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("onViewClicked", "yehhh!!");
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ProfileEditFragment())
                        .addToBackStack(null)
                        .commitNow();
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnProfileFragmentListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        getUserData();
//        loadText();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_menu, menu);
        MenuItem item = menu.findItem(R.id.logout);
        item.setVisible(true);
    }

    public interface OnProfileFragmentListener {
        void onOpenEditProfile();
    }

    void loadText() {
        sPrefProfile = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        String savedName = sPrefProfile.getString(STUDENT_NAME,"");
        String savedSurname = sPrefProfile.getString(STUDENT_SURNAME,"");
        String savedPatronymic = sPrefProfile.getString(STUDENT_PATRONYMIC,"");
        name.setText(savedName);
        surname.setText(savedSurname);
        patronymic.setText(savedPatronymic);
    }

    private void getUserData() {
        Retrofit retrofit = Connector.getRetrofitClient();
        FintechAPI fintechAPI = retrofit.create(FintechAPI.class);
        Call<UserResponse> call = fintechAPI.getUser();
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call call, Response response) {
                UserResponse userResponse = (UserResponse) response.body();
                UserResponse.User user = userResponse.getUser();
                String headers = response.headers().toString();
                String cookie = response.headers().get("Set-Cookie");
                if (response.isSuccessful()) {
                    if (userResponse.getStatus().equals("Ok")){
                        name.setText(user.getFirstName());
                        surname.setText(user.getLastName());
                        patronymic.setText(user.getMiddleName());
                        Glide.with(getContext())
                                .load("https://fintech.tinkoff.ru" + user.getAvatar())
                                .apply(RequestOptions.circleCropTransform())
                                .into(avatar);
                        Toast.makeText(getContext(), "Status: " + userResponse.getStatus(), Toast.LENGTH_SHORT).show();
                    }
                    Log.i("getUserData Response", "body: " + userResponse);
                    Log.i("getUserData Response", "headers: " + headers);
                    Log.i("getUserData Response", "cookie: " + cookie);
                    if (userResponse.getStatus().equals("Error")){
                        Toast.makeText(getContext(), "Message: " + userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("getUserData onFailure", "ooops!");
                Toast.makeText(getContext(), "getUserData went wrong!", Toast.LENGTH_SHORT).show();
            }


        });
    }
}
