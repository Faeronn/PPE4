package com.PPE4.gsb.objects;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.PPE4.gsb.DetailFraisActivity;
import com.PPE4.gsb.LoginActivity;
import com.PPE4.gsb.MainActivity;
import com.PPE4.gsb.R;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Visite> implements View.OnClickListener{

    private ArrayList<Visite> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView visite_description;
        TextView visite_date;
    }

    public CustomAdapter(ArrayList<Visite> data, Context context) {
        super(context, R.layout.row_itemvisite, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Object object = getItem(position);

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Visite visite = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_itemvisite, parent, false);
            viewHolder.visite_description = (TextView) convertView.findViewById(R.id.visite_description);
            viewHolder.visite_date = (TextView) convertView.findViewById(R.id.visite_date);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }


        viewHolder.visite_description.setText(visite.get_description());
        viewHolder.visite_date.setText(visite.get_date());
        // Return the completed view to render on screen
        return convertView;
    }
}