package kireev.ftshw.project.Network.Model;


import com.google.gson.annotations.SerializedName;

public class SignIn {

    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SignIn(String email, String password){
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString(){
        return "Post{" +
                "email='" + email + '\'' +
                ", password='" + password  +
                '}';
    }
}