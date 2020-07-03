package com.PPE4.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.PPE4.gsb.objects.User;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        Button loginButton = findViewById(R.id.loginValidationButton);
        loginButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
                case R.id.loginValidationButton:
                final EditText loginText = findViewById(R.id.username_box);
                final EditText passText = findViewById(R.id.password_box);

                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST,
                        "http://monserveur/PPE4/userLogin.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsno = new JSONObject(response);
                                    if (jsno.getString("message").equals("1")) {
                                        MainActivity.changeCurrentUser(
                                                new User(
                                                        jsno.getInt("idUser"),
                                                        jsno.getString("nom"),
                                                        jsno.getString("prenom"),
                                                        jsno.getInt("adminLevel")
                                                )
                                        );
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Identifiants incorrects.", Toast.LENGTH_LONG).show();

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                                Log.e("Error : ", error.getMessage());
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();

                        params.put("login", loginText.getText().toString());
                        params.put("pass", passText.getText().toString());

                        return params;
                    }
                };

                RequestQueue rq = Volley.newRequestQueue(this);
                rq.add(stringRequest);
                break;
        }
    }

}
