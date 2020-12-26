package com.example.cadin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cadin.model.RegistroUsuario;

import java.util.HashMap;
import java.util.Map;

public class Registro2 extends AppCompatActivity implements Spinner.OnItemSelectedListener, View.OnClickListener {
JsonObjectRequest jobs;
Spinner spPre1;
Button btnRegistro;
EditText fotoName, edApg, edDnip, edDnim, edTel, edCod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro2);

        btnRegistro= findViewById(R.id.buttonRegistrarse);
        btnRegistro.setOnClickListener(this);

        edTel=findViewById(R.id.edPhone_Reg);
        edCod=findViewById(R.id.edCod_Reg);

        //crea un adaptador con el xml
//        spPre1=findViewById(R.id.spinnerPreguntas_Reg);
//        ArrayAdapter ad=ArrayAdapter.createFromResource(this, R.array.preguntasReg,
//                android.R.layout.simple_list_item_1);
//                spPre1.setAdapter(ad); //asociar con spinner
//                spPre1.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        //Datos del Registro pg1
        final String nom = getIntent().getStringExtra("nom");
        final String apep = getIntent().getStringExtra("apep");
        final String apem = getIntent().getStringExtra("apem");
        final String fch = getIntent().getStringExtra("fch");
        final String dni = getIntent().getStringExtra("dni");

        //Datos del Registro pg2

        RegistroUsuario regUsr = new RegistroUsuario();
        String email = ""; //edEmail.getText().toString()
        final String foto = ""; //fotoName.getText().toString()
        final String apg = ""; //edApg.getText().toString()
        final String dnip = ""; //edDnip.getText().toString()
        final String dnim = ""; //edDnim.getText().toString()
        final String tel = edTel.getText().toString();
        final String cod = edCod.getText().toString();

        //regUsr.setEmail(email);
        regUsr.setFoto(foto);
        regUsr.setApg(apg);
        regUsr.setDnip(dnip);
        regUsr.setDnim(dnim);
        regUsr.setTel(tel);
        regUsr.setCod(cod);

        String urlAddUser="https://cadin.000webhostapp.com/addusuario.php";
        // String urlAddUser="http://10.0.2.2/utp/cadin/addusuario.php";



        StringRequest stringRequest= new StringRequest(Request.Method.POST, urlAddUser, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nom", nom);
                params.put("apep", apep);
                params.put("apem", apem);
                params.put("fch", fch);
                params.put("dni", dni);
                params.put("foto", foto);
                params.put("apg", apg);
                params.put("dnip", dnip);
                params.put("dnim", dnim);
                params.put("tel", tel);
                params.put("cod", cod);
                return params;
            }
        };

        RequestQueue cola= Volley.newRequestQueue(this);
        cola.add(stringRequest);
        /*
        jobs= new JsonObjectRequest(Request.Method.POST, urlAddUser, postDataUser, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.w("respuesta", error);
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue cola= Volley.newRequestQueue(this);
        cola.add(jobs);

        Log.w("JSONDATABODY", postDataUser.toString());*/

        Intent intRegistrarse = new Intent(Registro2.this, LoginActivity.class);
        startActivity(intRegistrarse);
    }
}