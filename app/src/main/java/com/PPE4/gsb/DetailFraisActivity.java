package com.PPE4.gsb;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.PPE4.gsb.objects.Frais;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailFraisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<String> frais = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            extras.getStringArrayList("fraisList");
        }


        for(int i=0; i < frais.size(); i++) {
            Log.e("frais :", frais.get(i));
        }
    }

    /*private void afficheFraisParMois() {
        fraisList.clear();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                "http://monserveur/PPE4/visiteList.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsnarray = new JSONArray(response);

                            for(int i=0; i < jsnarray.length(); i++) {

                                JSONObject jsonobject = jsnarray.getJSONObject(i);

                                if(jsonobject.getString("succes").equals("1")) {
                                    fraisList.add(
                                            new Frais(
                                                    jsonobject.getString("id"),
                                                    jsonobject.getDouble("date"),
                                                    jsonobject.getInt("quantite")
                                            )
                                    );
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        }
                        catch(JSONException e) {
                            Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("user", String.valueOf(MainActivity.getCurrentUser().get_id()));

                return params;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(getActivity().getApplicationContext());
        rq.add(stringRequest);
    }*/
}
