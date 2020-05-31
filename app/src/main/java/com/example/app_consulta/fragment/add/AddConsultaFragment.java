package com.example.app_consulta.fragment.add;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_consulta.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddConsultaFragment extends Fragment {

    TextView obs;
    Spinner nomeMedico, nomePaciente;
    Button btn_consulta;

    SQLiteDatabase db;

    public AddConsultaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_consulta, container, false);

        obs = view.findViewById(R.id.consulta_obs);
        nomeMedico = view.findViewById(R.id.consulta_medico);
        nomePaciente = view.findViewById(R.id.consulta_paciente);

        btn_consulta = view.findViewById(R.id.btn_salvar_consulta);

        btn_consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String observ, Medico, paciente;
                observ = obs.getText().toString().trim();
                Medico = nomeMedico.getSelectedItem().toString();
                paciente = nomePaciente.getSelectedItem().toString();

                if (observ.equals("")) {
                    Toast.makeText(v.getContext(), "Todos os campos deve ser preenchidos", Toast.LENGTH_LONG).show();
                } else {
                    db = getActivity().getApplicationContext().openOrCreateDatabase("appConsulta.db", Context.MODE_PRIVATE, null);
                    StringBuilder sql = new StringBuilder();
                    sql.append("INSERT INTO paciente(nome,grp_sanguineo, crm,logradouro,numero,cidade,uf,celular,fixo) VALUES (");

                    sql.append(");");

                    try {
                        db.execSQL(sql.toString());
                        Toast.makeText(v.getContext(), "Medico inserido", Toast.LENGTH_LONG).show();
                    } catch (SQLException e) {
                        Toast.makeText(v.getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        return  view;
    }
}
