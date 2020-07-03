package com.PPE4.gsb.mainFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.PPE4.gsb.MainActivity;
import com.PPE4.gsb.R;
import com.PPE4.gsb.objects.CustomAdapter;
import com.PPE4.gsb.objects.CustomAdapterFrais;
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

public class FraisFragment extends Fragment {
    private ArrayList<Frais> fraisList = new ArrayList<>();

    ListView fraisListView;
    private CustomAdapterFrais adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.main_fragment_frais, container, false);

        fraisListView = view.findViewById(R.id.listViewFrais);
        adapter = new CustomAdapterFrais(fraisList, getActivity().getApplicationContext());
        fraisListView.setAdapter(adapter);

        afficheFraisParMois();

        return view;
    }


    private void afficheFraisParMois() {
        fraisList.clear();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                "http://monserveur/PPE4/fraisList.php",
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
                                                    jsonobject.getString("info"),
                                                    jsonobject.getDouble("montant"),
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
    }

}
