package com.example.app_consulta.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.app_consulta.R;
import com.example.app_consulta.fragment.ConsultaFragment;
import com.example.app_consulta.fragment.MedicoFragment;
import com.example.app_consulta.fragment.PacienteFragment;

public class MainActivity extends AppCompatActivity {

    private Button btn_paciente, btn_consulta, btn_medico;
    private MedicoFragment med_frag;
    private ConsultaFragment cons_frag;
    private PacienteFragment paci_frag;

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
}
