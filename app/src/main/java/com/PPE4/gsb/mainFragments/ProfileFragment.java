package com.PPE4.gsb.mainFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.PPE4.gsb.MainActivity;
import com.PPE4.gsb.R;

public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_profile, container, false);

        TextView tag = view.findViewById(R.id.fragment_profile_tag);
        String texte = MainActivity.getCurrentUser().get_prenom() + " " + MainActivity.getCurrentUser().get_nom();
        tag.setText(texte);

        return view;
    }
}
