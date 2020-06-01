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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.app_consulta.R;
import com.example.app_consulta.activity.Tela_edicaoM;

/**
 * A simple {@link Fragment} subclass.
 */

public class ListarConsultaFragment extends Fragment {

    SQLiteDatabase db;
    ListView listaConsulta;

    public ListarConsultaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listar_consulta, container, false);

        TextView tvListId = view.findViewById(R.id.tv_idC);

        listaConsulta = view.findViewById(R.id.btn_listar_consulta);

        listarConsultaplmd();

        Intent i = new Intent(getContext(), Tela_edicaoM.class);
        i.putExtra("id",tvListId.getText().toString());

        return view;
    }

    public void listarConsultaplmd(){

        db = getActivity().getApplication().openOrCreateDatabase("appConsulta.db",Context.MODE_PRIVATE,null);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM consulta;");
        Cursor dados = db.rawQuery(sql.toString(),null);
        String [] from = {"_id","paciente_id","medico_id","data_hora_inicio","data_hora_fim","observacao"};
        int [] to = {R.id.tv_idC,R.id.tv_idpacienteC,R.id.tv_idmedicoC,R.id.tv_dataInicio, R.id.tv_dataFinal, R.id.tv_obs};

        SimpleCursorAdapter scAdapter = new SimpleCursorAdapter(getContext(), R.layout.dados_consulta,dados,from,to,0);

        listaConsulta.setAdapter(scAdapter);
    }
}
