package com.example.app_consulta.fragment.listar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app_consulta.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListarConsultaFragment extends Fragment {

    public ListarConsultaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listar_consulta, container, false);
    }
}