package com.example.cadin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cadin.model.RegistroUsuario;

import java.util.Calendar;

public class Registro extends AppCompatActivity implements View.OnClickListener {
JsonObjectRequest jobs;
TextView edNom, edApep, edApem, edFch, edDni;
Button btnContinuar;
    String url="https://cadin.000webhostapp.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edNom=findViewById(R.id.edNombre_Reg);
        edApep=findViewById(R.id.edApellidoPat_Reg);
        edApem=findViewById(R.id.edApellidoMat_Reg);
        edFch=findViewById(R.id.edDate_Reg);
        edDni=findViewById(R.id.edDni_Reg);

        btnContinuar=findViewById(R.id.buttonContinuar_Reg);
        btnContinuar.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        String urlRegistro= url+"registro.php";

        RegistroUsuario regUsr = new RegistroUsuario();
        String nom = edNom.getText().toString();
        String apep = edApep.getText().toString();
        String apem = edApem.getText().toString();
        String fch = edFch.getText().toString();
        String dni = edDni.getText().toString();

        regUsr.setNom(nom);
        regUsr.setApeP(apep);
        regUsr.setApeM(apem);
        regUsr.setFch(fch);
        regUsr.setDni(dni);

        Intent itnContinuarReg = new Intent(Registro.this, Registro2.class);
        itnContinuarReg.putExtra("nom", nom);
        itnContinuarReg.putExtra("apep", apep);
        itnContinuarReg.putExtra("apem", apem);
        itnContinuarReg.putExtra("fch", fch);
        itnContinuarReg.putExtra("dni", dni);

        startActivity(itnContinuarReg);
    }
}