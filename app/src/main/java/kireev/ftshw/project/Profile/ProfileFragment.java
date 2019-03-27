package kireev.ftshw.project.Profile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kireev.ftshw.project.MainActivity;
import kireev.ftshw.project.Network.Connector;
import kireev.ftshw.project.Network.FintechAPI;
import kireev.ftshw.project.Network.Ser.UserResponse;
import kireev.ftshw.project.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static kireev.ftshw.project.Profile.ProfileEditFragment.STUDENT_NAME;
import static kireev.ftshw.project.Profile.ProfileEditFragment.STUDENT_PATRONYMIC;
import static kireev.ftshw.project.Profile.ProfileEditFragment.STUDENT_SURNAME;
import static kireev.ftshw.project.Profile.ProfileEditFragment.sPrefProfile;


public class ProfileFragment extends Fragment {

    Button editButton;
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
                String headers = response.headers().toString();
                String cookie = response.headers().get("Set-Cookie");
                if (response.isSuccessful()) {
                    Log.i("Response", "body: " + userResponse);
                    Log.i("Response", "headers: " + headers);
                    Log.i("Response", "cookie: " + cookie);
                    Toast.makeText(getContext(), "Status: " + userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("onFailure", "ooops!");
                Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
            }


        });
    }
}
