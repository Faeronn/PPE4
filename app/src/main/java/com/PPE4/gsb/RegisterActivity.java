package com.PPE4.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        Button registerButton = findViewById(R.id.loginValidationButton);
        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginValidationButton:
                final EditText nameText = findViewById(R.id.name_box);
                final EditText surnameText = findViewById(R.id.surname_box);
                final EditText usernameText = findViewById(R.id.username_box);
                final EditText passwordText = findViewById(R.id.password_box);

                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST,
                        "http://monserveur/PPE4/userRegister.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsno = new JSONObject(response);
                                    if (jsno.getString("message").equals("1")) {
                                        startActivity(new Intent(RegisterActivity.this, WelcomeActivity.class));
                                    }
                                    else if(jsno.getString("message").equals("2")) {
                                        Toast.makeText(getApplicationContext(), "Nom de compte déjà existant.", Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Informations incompletes.", Toast.LENGTH_LONG).show();
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
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();

                        params.put("nom", surnameText.getText().toString());
                        params.put("prenom", nameText.getText().toString());
                        params.put("login", usernameText.getText().toString());
                        params.put("pass", passwordText.getText().toString());

                        return params;
                    }
                };

                RequestQueue rq = Volley.newRequestQueue(this);
                rq.add(stringRequest);
                break;

        }
    }
}
