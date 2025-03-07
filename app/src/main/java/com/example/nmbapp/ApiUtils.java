package com.example.nmbapp;

import static com.example.nmbapp.env.backendurl;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class ApiUtils {
    static final String loginurl =  "user/login";
    static final String signUpurl =  "user/signup";
    static final String deturl =  "driver/getDriver";
    static final String allurl =  "driver/all";
    static final String allRurl =  "route/getAllRoutes";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(backendurl) // Replace with your base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    LoginPoint loginPoint = retrofit.create(LoginPoint.class);
    DriDetPoint driDetPoint = retrofit.create(DriDetPoint.class);
    SignUpPoint signUpPoint = retrofit.create(SignUpPoint.class);
    AllDriversPoint allDriversPoint = retrofit.create(AllDriversPoint.class);
    AllRoutePoint allRoutePoint = retrofit.create(AllRoutePoint.class);

    public static class PostData{
        String email;
        String password;
        public PostData(String k1, String k2){
            email = k1;
            password = k2;
        }
    }
    public static class SignUpData{
        String email;
        String username;
        String password;
        public SignUpData(String k1, String k2, String k3){
            email = k1;
            password = k2;
            username = k3;
        }
    }
    public interface SignUpPoint{
        @POST(signUpurl)
        Call<ResponseBody> sendData(@Body SignUpData signUpData);

    }
    public interface LoginPoint{
        @POST(loginurl)
        Call<ResponseBody> sendData(@Body PostData postData);
    }

    public interface DriDetPoint{
        @GET(deturl)
        Call<ResponseBody> getDet(@Query("driverId") String paramValue, @Header("Authorization") String headerVal);
    }
    public interface AllDriversPoint{
        @GET(allurl)
        Call<ResponseBody> getAll(@Header("Authorization") String hval);
    }
    public interface AllRoutePoint{
        @GET(allRurl)
        Call<ResponseBody> getAll(@Header("Authorization") String hval);
    }
}
