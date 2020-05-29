package com.example.app_consulta.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.app_consulta.R;
import com.example.app_consulta.fragment.add.AddMedicoFragment;
import com.example.app_consulta.fragment.listar.ListarMedicoFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MedicoFragment extends Fragment {

    Button btn_listar, btn_add;
    AddMedicoFragment addMedico;
    ListarMedicoFragment listMedico;

    public MedicoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_medico, container, false);

        btn_listar = view.findViewById(R.id.btn_listar_medico);
        btn_add = view.findViewById(R.id.btn_add_medico);

        addMedico = new AddMedicoFragment();
        listMedico = new ListarMedicoFragment();

        btn_listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction;
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.id_frag_medico, listMedico);
                transaction.commit();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction;
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.id_frag_medico, addMedico);
                transaction.commit();
            }
        });

        return view;
    }

}
