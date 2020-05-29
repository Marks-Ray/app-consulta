package com.example.app_consulta.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.app_consulta.R;
import com.example.app_consulta.fragment.add.AddPacienteFragment;
import com.example.app_consulta.fragment.listar.ListarPacienteFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PacienteFragment extends Fragment {

    Button btn_add, btn_listar;
    AddPacienteFragment addPaciente;
    ListarPacienteFragment listPaciente;

    public PacienteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_paciente, container, false);

        btn_add = view.findViewById(R.id.btn_add_paciente);
        btn_listar = view.findViewById(R.id.btn_add_paciente);

        addPaciente = new AddPacienteFragment();
        listPaciente = new ListarPacienteFragment();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction;
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.id_frag_paciente, addPaciente);
                transaction.commit();
            }
        });

        btn_listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction;
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.id_frag_paciente, listPaciente);
                transaction.commit();
            }
        });

        return view;
    }
}
