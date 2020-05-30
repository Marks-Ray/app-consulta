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
public class AddMedicoFragment extends Fragment {

    TextView nomeM, crmM, logradouroM, cidadeM, celularM, fixoM, numeroM;
    Spinner ufM;
    SQLiteDatabase db;

    Button btn_salvar;
    public AddMedicoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_add_medico, container, false);

        nomeM = view.findViewById(R.id.nomeMedico);
        crmM = view.findViewById(R.id.crm);
        logradouroM = view.findViewById(R.id.logradouroMedico);
        cidadeM = view.findViewById(R.id.cidadeMedico);
        celularM = view.findViewById(R.id.celularMedico);
        fixoM = view.findViewById(R.id.teleFixoMedico);
        numeroM = view.findViewById(R.id.numCasaMedico);
        ufM = view.findViewById(R.id.ufMedico);
        btn_salvar = view.findViewById(R.id.btn_salvar_medico);


        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome, crm, logradouro, cidade, celular, fixo, numero,uf;
                nome = nomeM.getText().toString().trim();
                crm = crmM.getText().toString().trim();
                logradouro = logradouroM.getText().toString().trim();
                cidade = cidadeM.getText().toString().trim();
                celular = celularM.getText().toString().trim();
                fixo = fixoM.getText().toString().trim();
                numero = numeroM.getText().toString().trim();
                uf = ufM.getSelectedItem().toString();

                if(nome.equals("")||crm.equals("")||logradouro.equals("")||cidade.equals("")||celular.equals("")||fixo.equals("")||numero.equals("")){
                    Toast.makeText(v.getContext(),"Todos os campos deve ser preenchidos",Toast.LENGTH_LONG).show();
                }else {
                    db = getActivity().getApplicationContext().openOrCreateDatabase("appConsulta.db",Context.MODE_PRIVATE,null);
                    StringBuilder sql = new StringBuilder();
                    sql.append("INSERT INTO medico(nome,crm,logradouro,numero,cidade,uf,celular,fixo) VALUES (");
                    sql.append("'" + nome + "', ");
                    sql.append("'" + crm + "', ");
                    sql.append("'" + logradouro + "', ");
                    sql.append("'" + numero + "', ");
                    sql.append("'" + cidade + "', ");
                    sql.append("'" + uf + "', ");
                    sql.append("'" + celular + "', ");
                    sql.append("'" + fixo + "'");
                    sql.append(");");

                    try {
                        db.execSQL(sql.toString());
                        Toast.makeText(v.getContext(), "Medico inserido", Toast.LENGTH_LONG).show();
                    } catch (SQLException e) {
                        Toast.makeText(v.getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
               // db.close();
            }
        });

        return view;
    }
}
