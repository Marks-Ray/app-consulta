package com.example.app_consulta.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.app_consulta.R;

public class Tela_edicaoP extends AppCompatActivity {

    SQLiteDatabase db;
    EditText etNome,etCidade,etLogradouro,etCasa,etCelular,etFixo;
    Spinner spUF,spSangue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_edicao_p);

        etCasa = findViewById(R.id.numCasaPacienteP);
        etNome = findViewById(R.id.nomePacienteP);
        etCidade = findViewById(R.id.cidadePacienteP);
        etLogradouro = findViewById(R.id.logradouroPacienteP);
        etCelular = findViewById(R.id.celularPacienteP);
        etFixo = findViewById(R.id.teleFixoPacienteP);
        spUF = findViewById(R.id.ufPacienteP);
        spSangue = findViewById(R.id.gprSanguineoP);

        Intent valores = getIntent();

        etCasa.setText(valores.getStringExtra("casa"));
        etNome.setText(valores.getStringExtra("nome"));
        etCidade.setText(valores.getStringExtra("cidade"));
        etLogradouro.setText(valores.getStringExtra("logradouro"));
        etCelular.setText(valores.getStringExtra("celular"));
        etFixo.setText(valores.getStringExtra("fixo"));

        final String id = valores.getStringExtra("id");

        Button salvar = findViewById(R.id.btn_salvar_pacienteP);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome, sangue, logradouro, cidade, celular, fixo, numero,uf;
                nome = etNome.getText().toString().trim();
                logradouro = etLogradouro.getText().toString().trim();
                cidade = etCidade.getText().toString().trim();
                celular = etCelular.getText().toString().trim();
                fixo = etFixo.getText().toString().trim();
                numero = etCasa.getText().toString().trim();
                uf = spUF.getSelectedItem().toString();
                sangue = spSangue.getSelectedItem().toString();

                if(nome.equals("")||logradouro.equals("")||cidade.equals("")||celular.equals("")||fixo.equals("")||numero.equals("")){
                    Toast.makeText(v.getContext(),"Todos os campos deve ser preenchidos",Toast.LENGTH_LONG).show();
                }else {
                    db = openOrCreateDatabase("appConsulta.db", Context.MODE_PRIVATE,null);
                    StringBuilder sql = new StringBuilder();
                    sql.append("UPDATE paciente SET ");
                    sql.append("nome = '" + nome + "', ");
                    sql.append("grp_sanguineo = '" + sangue + "', ");
                    sql.append("logradouro = '" + logradouro + "', ");
                    sql.append("numero = '" + numero + "', ");
                    sql.append("cidade = '" + cidade + "', ");
                    sql.append("uf = '" + uf + "', ");
                    sql.append("celular = '" + celular + "', ");
                    sql.append("fixo = '" + fixo + "' ");
                    sql.append("WHERE _id = " + id + ";");

                    try {
                        db.execSQL(sql.toString());
                        Toast.makeText(v.getContext(), "Paciente atualizado", Toast.LENGTH_LONG).show();
                        finish();
                    } catch (SQLException e) {
                        Toast.makeText(v.getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        Button excluir = findViewById(R.id.btn_excluir_pacienteP);
        excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openOrCreateDatabase("appConsulta.db", Context.MODE_PRIVATE, null);
                StringBuilder sql = new StringBuilder();
                sql.append("DELETE FROM paciente ");
                sql.append("WHERE _id = " + id + ";");
                try {
                    db.execSQL(sql.toString());
                    Toast.makeText(getApplicationContext(), "Paciente excluído", Toast.LENGTH_LONG).show();
                    finish();
                } catch (SQLException e) {
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
