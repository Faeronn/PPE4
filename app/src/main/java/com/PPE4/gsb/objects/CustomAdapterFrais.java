package com.PPE4.gsb.objects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.PPE4.gsb.R;

import java.util.ArrayList;

public class CustomAdapterFrais extends ArrayAdapter<Frais> implements View.OnClickListener{

    private ArrayList<Frais> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView frais_description;
        TextView frais_montant;
    }

    public CustomAdapterFrais(ArrayList<Frais> data, Context context) {
        super(context, R.layout.row_itemfrais, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Frais frais=(Frais)object;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Frais frais = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_itemfrais, parent, false);
            viewHolder.frais_description = (TextView) convertView.findViewById(R.id.frais_description);
            viewHolder.frais_montant = (TextView) convertView.findViewById(R.id.frais_montant);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }


        viewHolder.frais_description.setText(frais.get_info());
        viewHolder.frais_montant.setText(Double.toString(frais.get_montant()*frais.get_quantite()));
        // Return the completed view to render on screen
        return convertView;
    }
}