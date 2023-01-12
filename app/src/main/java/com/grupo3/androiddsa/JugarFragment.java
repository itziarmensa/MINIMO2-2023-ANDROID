package com.grupo3.androiddsa;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class JugarFragment extends Fragment {

    Button btnJugar;
    Button denunciarAbuso;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_jugar, container, false);

        btnJugar = rootView.findViewById(R.id.btnJugar);
        denunciarAbuso = rootView.findViewById(R.id.denunciarAbuso);

        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),EscogerObjetoActivity.class);
                startActivity(i);
            }
        });

        denunciarAbuso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MainAbuso.class);
                startActivity(i);
            }
        });

        return rootView;
    }

}