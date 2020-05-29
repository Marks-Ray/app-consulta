package com.example.app_consulta.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.app_consulta.R;
import com.example.app_consulta.fragment.add.AddConsultaFragment;
import com.example.app_consulta.fragment.listar.ListarConsultaFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultaFragment extends Fragment {

    Button btn_listar, btn_add;
    AddConsultaFragment addConsulta;
    ListarConsultaFragment listConsultra;

    public ConsultaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_consulta, container, false);

        btn_listar = view.findViewById(R.id.btn_listar_consulta);
        btn_add = view.findViewById(R.id.btn_add_consulta);

        addConsulta = new AddConsultaFragment();
        listConsultra = new ListarConsultaFragment();

        btn_listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction;
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frag_conteudo_consulta, listConsultra);
                transaction.commit();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction;
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frag_conteudo_consulta, addConsulta);
                transaction.commit();
            }
        });

        return view;
    }
}
