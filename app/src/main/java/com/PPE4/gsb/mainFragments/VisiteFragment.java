package com.PPE4.gsb.mainFragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.PPE4.gsb.DetailFraisActivity;
import com.PPE4.gsb.MainActivity;
import com.PPE4.gsb.R;
import com.PPE4.gsb.objects.CustomAdapter;
import com.PPE4.gsb.objects.Frais;
import com.PPE4.gsb.objects.Visite;
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

public class VisiteFragment extends Fragment {
    private ArrayList<Visite> visiteList = new ArrayList<>();

    ListView visiteListView;
    private CustomAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.main_fragment_visite, container, false);

        visiteListView = view.findViewById(R.id.listViewVisite);
        adapter = new CustomAdapter(visiteList, getActivity().getApplicationContext());
        visiteListView.setAdapter(adapter);

        afficheVisites();


        return view;
    }


    private void afficheVisites() {
        visiteList.clear();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                "http://monserveur/PPE4/visiteList.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            ArrayList<Frais> fraisList = new ArrayList<>();
                            JSONArray jsnarray = new JSONArray(response);

                            for(int i=0; i < jsnarray.length(); i++) {
                                JSONObject jsonobject = jsnarray.getJSONObject(i);

                                JSONArray jsnarray2 = jsonobject.getJSONArray("frais");

                                for(int j=0; j < jsnarray2.length(); j++) {

                                    JSONObject jsonobject2 = jsnarray2.getJSONObject(j);
                                    fraisList.add(new Frais(
                                            jsonobject2.getString("id"),
                                            jsonobject2.getDouble("montant"),
                                            jsonobject2.getInt("quantite")
                                    ));
                                }

                                visiteList.add(new Visite(
                                        jsonobject.getInt("id"),
                                        jsonobject.getString("date"),
                                        jsonobject.getString("description"),
                                        fraisList
                                ));

                                adapter.notifyDataSetChanged();
                            }
                        }
                        catch(JSONException e) {
                            Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            Log.e("Error : ", e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("ErrorListenner : ", error.getMessage());
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
