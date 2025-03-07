package com.example.nmbapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.example.nmbapp.env.jwt;

public class SignUpTabFragment extends Fragment {
    EditText email, username, pass;
    Button btn;
    Context context;
    public SignUpTabFragment(Context context) {
        this.context = context;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up_tab, container, false);
        email = view.findViewById(R.id.signup_email);
        username = view.findViewById(R.id.signup_username);
        pass = view.findViewById(R.id.signup_pass);
        btn = view.findViewById(R.id.signup_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiUtils au = new ApiUtils();
                String em = email.getText().toString();
                String pw = pass.getText().toString();
                String uname = username.getText().toString();
                ApiUtils.SignUpData data = new ApiUtils.SignUpData(em, pw, uname);
                Call<ResponseBody> call = au.signUpPoint.sendData(data);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            JSONObject res = new JSONObject(response.body().string());
                            Log.d("json", res.toString());
                            if(res.getString("success").equals("true")) {
                                env.email = em;
                                jwt = res.getJSONObject("data").getString("token");
                                String[] p = listRequestedPermissions();
                                if(p == null){
                                    Log.d("Permissions", "No Perms Required");
                                    launchMaps();
                                    return;
                                }
                                PermManger2(context, p);
                            }else{
                                Toast.makeText(context, "Invalid Cred", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        Log.d("sigh", call.toString());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("sigh", t.toString());
                    }
                });
            }
        });
        return view;
    }
    private void PermManger2(Context context, String[] perm) {
        Boolean[] granted = new Boolean[perm.length];
        int ungrantedPermsCount = 0;

        // Loop through permissions and check each
        for (int i = 0; i < perm.length; i++) {
            if (ContextCompat.checkSelfPermission(context, perm[i]) == PackageManager.PERMISSION_GRANTED) {
                granted[i] = true;
            } else {
                granted[i] = false;
                ungrantedPermsCount++;
            }
        }

        // If all permissions are already granted
        if (ungrantedPermsCount == 0) {
            Toast.makeText(context, "All permissions granted", Toast.LENGTH_SHORT).show();
            launchMaps(); // Launch the dial pad activity here
            return;
        }

        // Collect ungranted permissions
        String[] reqPerms = new String[ungrantedPermsCount];
        int index = 0;
        for (int i = 0; i < perm.length; i++) {
            if (!granted[i]) {
                reqPerms[index++] = perm[i];
            }
        }

        // Request the ungranted permissions
        mulReqLauncher.launch(reqPerms);
    }
    private ActivityResultLauncher<String[]> mulReqLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                boolean allGranted = true;

                // Check the result of each permission
                for (Map.Entry<String, Boolean> entry : result.entrySet()) {
                    if (!entry.getValue()) {
                        allGranted = false;
                        Toast.makeText(context, "Permission " + entry.getKey() + " not granted", Toast.LENGTH_SHORT).show();
                    }
                }

                // If all permissions are granted, proceed to the next activity
                if (allGranted) {
                    launchMaps();
                } else {
                    Toast.makeText(context, "Some permissions are not granted", Toast.LENGTH_SHORT).show();
                }
            }
    );

    private String[] listRequestedPermissions() {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);
            String[] requestedPermissions = packageInfo.requestedPermissions;
            if (requestedPermissions != null) {
                for (String permission : requestedPermissions) {
                    System.out.println("Requested permission: " + permission);
                }
            }
            return requestedPermissions;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private  void launchMaps(){
        Intent intent = new Intent(context, Home_Main.class);
        startActivity(intent);
    }
}