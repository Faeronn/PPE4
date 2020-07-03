package com.PPE4.gsb.mainFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.PPE4.gsb.MainActivity;
import com.PPE4.gsb.R;
import com.PPE4.gsb.objects.CustomAdapter;
import com.PPE4.gsb.objects.Frais;
import com.PPE4.gsb.objects.User;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewFraisFragment extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_newfrais, container, false);

        Button valider = view.findViewById(R.id.ValidationButton);
        valider.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ValidationButton:
                final EditText infoText = getActivity().findViewById(R.id.infos_box);
                final EditText quantiteText = getActivity().findViewById(R.id.quantite_box);
                final EditText montantText = getActivity().findViewById(R.id.montant_box);
                final int userID = MainActivity.getCurrentUser().get_id();

                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST,
                        "http://monserveur/PPE4/fraisRegister.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsno = new JSONObject(response);
                                    if (jsno.getString("message").equals("1")) {
                                        BottomNavigationView bottomNav = getActivity().findViewById(R.id.activity_main_bottom_navigation);
                                        View v = bottomNav.findViewById(R.id.action_checkFrais);
                                        v.performClick();

                                    } else {
                                        Toast.makeText(getActivity().getApplicationContext(), "Informations incomplètes.", Toast.LENGTH_LONG).show();

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getActivity().getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                                Log.e("Error : ", error.getMessage());
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();

                        params.put("info", infoText.getText().toString());
                        params.put("montant", montantText.getText().toString());
                        params.put("quantite", quantiteText.getText().toString());
                        params.put("userID", String.valueOf(userID));

                        return params;
                    }
                };

                RequestQueue rq = Volley.newRequestQueue(getActivity().getApplicationContext());
                rq.add(stringRequest);
                break;
        }
    }
}
