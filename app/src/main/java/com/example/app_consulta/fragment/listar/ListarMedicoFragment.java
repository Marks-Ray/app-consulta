package com.example.app_consulta.fragment.listar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.app_consulta.R;
import com.example.app_consulta.activity.Tela_edicaoM;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListarMedicoFragment extends Fragment {

    ListView listaMedico;
    SQLiteDatabase db;

    public ListarMedicoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listar_medico, container, false);

        listaMedico = view.findViewById(R.id.lv_medico);

        listarMedico();

        listaMedico.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View v = listaMedico.getChildAt(position);
                TextView tvListId = v.findViewById(R.id.tv_idM);
                TextView tvListNome = v.findViewById(R.id.tv_nomeM);
                TextView tvListCRM = v.findViewById(R.id.tv_crm);
                TextView tvListCelular = v.findViewById(R.id.tv_celularM);
                TextView tvListuf = v.findViewById(R.id.tv_ufM);
                TextView tvListCasa = v.findViewById(R.id.tv_casaNumM);
                TextView tvListCidade = v.findViewById(R.id.tv_cidadeM);
                TextView tvListFixo = v.findViewById(R.id.tv_fixoM);
                TextView tvListLogradouro = v.findViewById(R.id.tv_logradouroM);

                Intent i = new Intent(getContext(), Tela_edicaoM.class);
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

    private void listarMedico(){

    db = getActivity().getApplication().openOrCreateDatabase("appConsulta.db", Context.MODE_PRIVATE,null);
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT * FROM medico;");
        Cursor dados = db.rawQuery(sql.toString(),null);
        String [] from = {"_id","nome", "crm", "celular","uf","numero","cidade","fixo","logradouro"};
        int[] to = {R.id.tv_idM,R.id.tv_nomeM,R.id.tv_crm, R.id.tv_celularM, R.id.tv_ufM, R.id.tv_casaNumM, R.id.tv_cidadeM,R.id.tv_fixoM, R.id.tv_logradouroM};

        SimpleCursorAdapter scAdapter = new SimpleCursorAdapter(getContext(), R.layout.dados_medico,dados,from,to,0);

        listaMedico.setAdapter(scAdapter);

    }
}
