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
public class AddPacienteFragment extends Fragment {

    TextView nomeP, logradouroP, cidadeP, celularP, fixoP, numeroP;
    Spinner ufP, sangueP;
    SQLiteDatabase db;

    Button btn_salvar_paciente;

    public AddPacienteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_paciente, container, false);

        nomeP = view.findViewById(R.id.nomePaciente);
        logradouroP = view.findViewById(R.id.logradouroPaciente);
        cidadeP = view.findViewById(R.id.cidadePaciente);
        celularP = view.findViewById(R.id.celularPaciente);
        fixoP = view.findViewById(R.id.teleFixoPaciente);
        numeroP = view.findViewById(R.id.numCasaPaciente);
        ufP = view.findViewById(R.id.ufPaciente);
        sangueP = view.findViewById(R.id.gprSanguineo);

        btn_salvar_paciente = view.findViewById(R.id.btn_salvar_paciente);

        btn_salvar_paciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome, sangue, logradouro, cidade, celular, fixo, numero, uf;
                nome = nomeP.getText().toString().trim();
                logradouro = logradouroP.getText().toString().trim();
                cidade = cidadeP.getText().toString().trim();
                celular = celularP.getText().toString().trim();
                fixo = fixoP.getText().toString().trim();
                numero = numeroP.getText().toString().trim();
                uf = ufP.getSelectedItem().toString();
                sangue = sangueP.getSelectedItem().toString();
                int sangue1 = 0;
                if (sangue == "A+"){
                    sangue1 = 0;
                }
                else if(sangue == "A-"){
                    sangue1 = 1;
                }
                else if(sangue == "B+"){
                    sangue1 = 2;
                }

                else if(sangue == "B-"){
                    sangue1 = 3;
                }

                else if(sangue == "O+"){
                    sangue1 = 4;
                }
                else if(sangue == "O-"){
                    sangue1 = 5;
                }

                else if(sangue == "AB+"){
                    sangue1 = 6;
                }

                else if(sangue == "AB-"){
                    sangue1 = 7;
                }

                if (nome.equals("") || logradouro.equals("") || cidade.equals("") || celular.equals("") || fixo.equals("") || numero.equals("")) {
                    Toast.makeText(v.getContext(), "Todos os campos deve ser preenchidos", Toast.LENGTH_LONG).show();
                } else {
                    db = getActivity().getApplicationContext().openOrCreateDatabase("appConsulta.db", Context.MODE_PRIVATE, null);
                    StringBuilder sql = new StringBuilder();
                    sql.append("INSERT INTO paciente(nome,grp_sanguineo,logradouro,numero,cidade,uf,celular,fixo) VALUES (");
                    sql.append("'" + nome + "', ");
                    sql.append("'" + sangue1 + "', ");
                    sql.append("'" + logradouro + "', ");
                    sql.append("'" + numero + "', ");
                    sql.append("'" + cidade + "', ");
                    sql.append("'" + uf + "', ");
                    sql.append("'" + celular + "', ");
                    sql.append("'" + fixo + "'");
                    sql.append(");");

                    try {
                        db.execSQL(sql.toString());
                        Toast.makeText(v.getContext(), "paciente inserido", Toast.LENGTH_LONG).show();
                    } catch (SQLException e) {
                        Toast.makeText(v.getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


        return view;
    }
}
