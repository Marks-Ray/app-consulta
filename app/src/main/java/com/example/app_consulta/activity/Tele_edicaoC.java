package com.example.app_consulta.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.app_consulta.R;

import java.util.Calendar;

public class Tele_edicaoC extends AppCompatActivity {

    TextView obs;
    TextView nomeMedico, nomePaciente,Data,Hora,dataFinal,horaFinal;
    Button btn_consulta,btn_data,btn_hora,btn_hora_final, btn_data_final, btn_excluir;
    Calendar horaI = Calendar.getInstance();
    Calendar horaF = Calendar.getInstance();


    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tele_edicao_c);

        obs = findViewById(R.id.consulta_obs);
        nomeMedico = findViewById(R.id.medicoConsulta);
        nomePaciente = findViewById(R.id.pacienteConsulta);
        Data = findViewById(R.id.data_inicial_consulta);
        Hora = findViewById(R.id.hora_inicial_consulta);
        dataFinal = findViewById(R.id.data_final_consulta);
        horaFinal = findViewById(R.id.hora_final_consulta);

        btn_data_final = findViewById(R.id.btn_final_data);
        btn_hora_final = findViewById(R.id.btn_final_hora);
        btn_excluir = findViewById(R.id.btn_excluirC);

        btn_data = findViewById(R.id.btn_inicio_data);
        btn_hora = findViewById(R.id.btn_inicio_hora);
        btn_data = findViewById(R.id.btn_inicio_data);
        btn_hora = findViewById(R.id.btn_inicio_hora);
        btn_consulta = findViewById(R.id.btn_salvar_consulta);
        btn_data_final = findViewById(R.id.btn_final_data);
        btn_hora_final = findViewById(R.id.btn_final_hora);

        Intent valores = getIntent();

        final String id = valores.getStringExtra("id");

        btn_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateButton();
            }
        });
        btn_hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeButton();
            }
        });

        btn_hora_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeButtonFinal();
            }
        });

        btn_data_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateButtonFinal();
            }
        });

        btn_consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeMedico2, nomePaciente2, dataInicio, horaInicio, dataFim, horaFim;

        btn_excluir.setOnClickListener(new View.OnClickListener() {
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

                nomeMedico2 = nomeMedico.getText().toString().trim();
                nomePaciente2 = nomePaciente.getText().toString().trim();
                dataInicio = Data.getText().toString().trim();
                horaInicio = Hora.getText().toString().trim();

                dataFim = dataFinal.getText().toString().trim();
                horaFim = horaFinal.getText().toString().trim();

                String inicio = dataInicio + horaInicio;
                String fim = dataFim + horaFim;

                if (horaI.after(horaF)){
                    Toast.makeText(v.getContext(),"Hora inicial invalida",Toast.LENGTH_LONG).show();
                }
                else if(horaI.before("8:00 AM")|| horaI.before("1:30 PM")||horaF.after("12:00 PM")||horaF.after("5:30 PM")){
                    Toast.makeText(v.getContext(),"Horario de funcionamento:  08:00AM até 12:00PM; 1:30PM até 17:30PM",Toast.LENGTH_LONG).show();
                }
                else if(dataInicio.equals("")||horaInicio.equals("")){
                    Toast.makeText(v.getContext(),"Todos os campos deve ser preenchidos",Toast.LENGTH_LONG).show();
                }else {
                    db = getApplicationContext().openOrCreateDatabase("appConsulta.db", Context.MODE_PRIVATE,null);
                    StringBuilder sql = new StringBuilder();
                    sql.append("INSERT INTO consulta(paciente_id,medico_id,data_hora_inicio,data_hora_fim,observacao) VALUES (");
                    sql.append("'" + 1 + "', ");
                    sql.append("'" + 1 + "', ");
                    sql.append("'" + inicio + "', ");
                    sql.append("'" + fim + "', ");
                    sql.append("'" + obs + "'");
                    sql.append(");");

                    try {
                        db.execSQL(sql.toString());
                        Toast.makeText(v.getContext(), "consulta inserido", Toast.LENGTH_LONG).show();
                    } catch (SQLException e) {
                        Toast.makeText(v.getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                // db.close();
            }
        });
    }

    private void DateButton() {
        Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getApplicationContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);
                String dateText = DateFormat.format("EEEE, MMM d, yyyy", calendar1).toString();

                int diaSemana = calendar1.get(Calendar.DAY_OF_WEEK);

                if (diaSemana == 1 || diaSemana == 7){
                    Toast.makeText(getApplicationContext(),"Nao esta aberto finais de semana",Toast.LENGTH_LONG).show();
                }else {
                    Data.setText(dateText);
                }

            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();
    }

    private void DateButtonFinal() {
        final Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getApplicationContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);
                String dateText = DateFormat.format("EEEE, MMM d, yyyy", calendar1).toString();

                int diaSemana = calendar1.get(Calendar.DAY_OF_WEEK);

                if (diaSemana == 1 || diaSemana == 7){
                    Toast.makeText(getApplicationContext(),"Nao esta aberto finais de semana",Toast.LENGTH_LONG).show();
                }else {
                    dataFinal.setText(dateText);
                }
            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();
    }

    private void TimeButton() {
        final Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);
        boolean is24HourFormat = DateFormat.is24HourFormat(getApplicationContext());

        TimePickerDialog timePickerDialog = new TimePickerDialog(getApplicationContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR, hour);
                calendar1.set(Calendar.MINUTE, minute);
                String dateText = DateFormat.format("HH:mm h", calendar1).toString();
                horaI.set(Calendar.HOUR,hour);
                horaI.set(Calendar.MINUTE,minute);
                int horaEspediente = calendar1.get(Calendar.HOUR_OF_DAY);
                int minutosEspediente = calendar1.get(Calendar.MINUTE);
                if (horaEspediente < 8){
                    Toast.makeText(getApplicationContext(),"Abre as 8AM",Toast.LENGTH_LONG).show();
                }
                else if(horaEspediente == 12 || horaEspediente == 13 && minutosEspediente < 31){
                    Toast.makeText(getApplicationContext(),"Nao atende do 12-13:30",Toast.LENGTH_LONG).show();
                }
                else if (horaEspediente == 17 && minutosEspediente > 30){
                    Toast.makeText(getApplicationContext(),"Fecha as 17:30",Toast.LENGTH_LONG).show();
                }
                else {
                    Hora.setText(dateText);
                }
            }
        }, HOUR, MINUTE, is24HourFormat);

        timePickerDialog.show();

    }

    private void TimeButtonFinal() {
        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);
        boolean is24HourFormat = DateFormat.is24HourFormat(getApplicationContext());

        TimePickerDialog timePickerDialog = new TimePickerDialog(getApplicationContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR, hour);
                calendar1.set(Calendar.MINUTE, minute);
                String dateText = DateFormat.format("HH:mm h", calendar1).toString();

                horaF.set(Calendar.HOUR,hour);
                horaF.set(Calendar.MINUTE, minute);

                int horaEspediente = calendar1.get(Calendar.HOUR_OF_DAY);
                int minutosEspediente = calendar1.get(Calendar.MINUTE);
                if (horaEspediente < 8){
                    Toast.makeText(getApplicationContext(),"Abre as 8AM",Toast.LENGTH_LONG).show();
                }
                else if(horaEspediente == 12 || horaEspediente == 13 && minutosEspediente < 31){
                    Toast.makeText(getApplicationContext(),"Nao atende do 12-13:30",Toast.LENGTH_LONG).show();
                }
                else if (horaEspediente == 17 && minutosEspediente > 30){
                    Toast.makeText(getApplicationContext(),"Fecha as 17:30",Toast.LENGTH_LONG).show();
                }
                else {
                    horaFinal.setText(dateText);
                }

            }
        }, HOUR, MINUTE, is24HourFormat);

        timePickerDialog.show();
    }
}
