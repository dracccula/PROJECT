package kireev.ftshw.project.Network.Ser;


import com.google.gson.annotations.SerializedName;

public class SignIn {

    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;


    public SignIn(String email, String password) {
        this.email = email;
        this.password = password;
    }

}