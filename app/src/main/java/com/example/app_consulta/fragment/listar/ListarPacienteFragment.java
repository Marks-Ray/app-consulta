package com.example.app_consulta.fragment.listar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.strictmode.SqliteObjectLeakedViolation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.app_consulta.R;
import com.example.app_consulta.activity.Tela_edicaoM;
import com.example.app_consulta.activity.Tela_edicaoP;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListarPacienteFragment extends Fragment {

    SQLiteDatabase db;
    ListView listaPaciente;

    public ListarPacienteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listar_paciente, container, false);

        listaPaciente = view.findViewById(R.id.lv_paciente);

        ListarPaciente();

        listaPaciente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View v = listaPaciente.getChildAt(position);
                TextView tvListId = v.findViewById(R.id.tv_idP);
                TextView tvListNome = v.findViewById(R.id.tv_nomeP);
                TextView tvListCRM = v.findViewById(R.id.tv_sangueP);
                TextView tvListCelular = v.findViewById(R.id.tv_celularP);
                TextView tvListuf = v.findViewById(R.id.tv_ufP);
                TextView tvListCasa = v.findViewById(R.id.tv_casaNumP);
                TextView tvListCidade = v.findViewById(R.id.tv_cidadeP);
                TextView tvListFixo = v.findViewById(R.id.tv_fixoP);
                TextView tvListLogradouro = v.findViewById(R.id.tv_logradouroP);

                Intent i = new Intent(getContext(), Tela_edicaoP.class);
                i.putExtra("id",tvListId.getText().toString());
                i.putExtra("nome",tvListNome.getText().toString());
                i.putExtra("crm",tvListCRM.getText().toString());
                i.putExtra("celular",tvListCelular.getText().toString());
                i.putExtra("UF",tvListuf.getText().toString());
                i.putExtra("casa",tvListCasa.getText().toString());
                i.putExtra("cidade",tvListCidade.getText().toString());
                i.putExtra("fixo",tvListFixo.getText().toString());
                i.putExtra("logradouro",tvListLogradouro.getText().toString());
                startActivity(i);
            }
        });

        return view;
    }

    private void ListarPaciente() {

        db = getActivity().getApplication().openOrCreateDatabase("appConsulta.db",Context.MODE_PRIVATE,null);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM paciente;");
        Cursor dados = db.rawQuery(sql.toString(),null);
        String [] from = {"_id","nome","grp_sanguineo","logradouro","numero", "cidade", "uf", "celular", "fixo"};
        int [] to = {R.id.tv_idP, R.id.tv_nomeP, R.id.tv_sangueP, R.id.tv_logradouroP, R.id.tv_casaNumP, R.id.tv_cidadeP, R.id.tv_ufP, R.id.tv_celularP, R.id.tv_fixoP};

        SimpleCursorAdapter spAdapter = new SimpleCursorAdapter(getContext(), R.layout.dados_paciente,dados,from,to,0);

        listaPaciente.setAdapter(spAdapter);

    }
}

