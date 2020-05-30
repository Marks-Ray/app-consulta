package com.example.app_consulta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.app_consulta.R;
import com.example.app_consulta.fragment.ConsultaFragment;
import com.example.app_consulta.fragment.MedicoFragment;
import com.example.app_consulta.fragment.PacienteFragment;

public class MainActivity extends AppCompatActivity {

    private Button btn_paciente, btn_consulta, btn_medico;
    private MedicoFragment med_frag;
    private ConsultaFragment cons_frag;
    private PacienteFragment paci_frag;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_consulta = findViewById(R.id.btn_consulta);
        btn_medico = findViewById(R.id.btn_medico);
        btn_paciente = findViewById(R.id.btn_paciente);

        //removendo o elevation da ActionBar
        getSupportActionBar().setElevation(0);

        med_frag = new MedicoFragment();
        cons_frag = new ConsultaFragment();
        paci_frag = new PacienteFragment();

        criardb();

        btn_medico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction;
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_conteudo, med_frag);
                transaction.commit();
            }
        });

        btn_paciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction;
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_conteudo, paci_frag);
                transaction.commit();
            }
        });

        btn_consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction;
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_conteudo, cons_frag);
                transaction.commit();
            }
        });

    }

    private void criardb(){

        db = openOrCreateDatabase("appConsulta.db", Context.MODE_PRIVATE, null);
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS medico (");
        sql.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sql.append("nome VARCHAR(50), ");
        sql.append("crm VARCHAR(20), ");
        sql.append("logradouro VARCHAR(100), ");
        sql.append("numero MEDIUMINT(8), ");  sql.append("cidade VARCHAR(30), ");
        sql.append("uf VARCHAR(2), ");
        sql.append("celular VARCHAR(20), ");
        sql.append("fixo VARCHAR(20)");
        sql.append(");");

        sql.append("CREATE TABLE IF NOT EXISTS paciente (");
        sql.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sql.append("nome VARCHAR(50), ");
        sql.append("grp_sanguineo TINYINT(1), ");
        sql.append("logradouro VARCHAR(100), ");
        sql.append("numero MEDIUMINT(8), ");
        sql.append("cidade VARCHAR(30), ");
        sql.append("uf VARCHAR(2), ");
        sql.append("celular VARCHAR(20), ");
        sql.append("fixo VARCHAR(20)");
        sql.append(");");

        sql.append("CREATE TABLE IF NOT EXISTS consulta (");
        sql.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sql.append("paciente_id INTEGER NOT NULL, ");
        sql.append("medico_id INTEGER NOT NULL, ");
        sql.append("data_hora_inicio DATETIME, ");
        sql.append("data_hora_fim DATETIME, ");
        sql.append("observacao VARCHAR(200), ");
        sql.append("FOREIGN KEY(paciente_id) REFERENCES paciente(id), ");
        sql.append("FOREIGN KEY(medico_id) REFERENCES medico(id)");
        sql.append(");");

        try {
            //Executar (execSQL) um comando SQL de criação de tabela por vez.
            String[] queries = sql.toString().split(";");
            for(String query : queries){
                db.execSQL(query);
            }
        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        db.close();

    }
}
