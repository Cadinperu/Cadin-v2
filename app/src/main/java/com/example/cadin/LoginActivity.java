package com.example.cadin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
JSONArray vector;
JsonObjectRequest jobs;
SmsManager smsManager;
SmsMessage smsMessage;
FragmentTransaction transaction;
Fragment fragmentLoginDni, fragmentLoginAcceso;
EditText edDni;
Button btnLogin;
TextView txtRegistro, txtRes;
    //http://10.0.2.2/utp/cadin/session.php/https://cadin.000webhostapp.com/session.php
    String url="https://cadin.000webhostapp.com/session.php";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edDni= findViewById(R.id.edNumDni);
        btnLogin= findViewById(R.id.buttonLogin);
        txtRegistro= findViewById(R.id.txtLinkRegistro);
        txtRes= findViewById(R.id.textViewRes);

        txtRegistro.setOnClickListener(this);


        //fragmentLoginDni= new LoginDniFragment();
        //fragmentLoginAcceso= new LoginAccesoFragment();

        //getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragment,fragmentLoginDni).commit();
    }

    @Override
    public void onClick(View v) {
        Intent txtRegistro = new Intent(LoginActivity.this, Registro.class);
        startActivity(txtRegistro);
    }

    public void loginCheckPanel(View v) {
        //transaction=getSupportFragmentManager().beginTransaction();

        //Verifica si DNI esta registrado
        String urlSession=url+"?tag=checklogin&dni="+edDni.getText().toString();

        jobs= new JsonObjectRequest(Request.Method.GET, urlSession, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //txtRes.setText("Response: " + response.toString());
                try {
                    JSONObject DatoCheckLogin = response.getJSONObject("dato");
                    Boolean CheckDato = DatoCheckLogin.getBoolean("check");
                    String DniDato = DatoCheckLogin.getString("dni");

                    Log.w("dato", DatoCheckLogin.toString());

                    if (CheckDato) {
                        String dni= DniDato;
                        //txtRes.setText(DniCheck.toString());
                        //transaction.replace(R.id.contenedorFragment,fragmentLoginAcceso);
                        //transaction.addToBackStack(null);
                        //transaction.commit();
                        Intent intBtnLogin = new Intent(LoginActivity.this, LoginCodeAccess.class);
                        intBtnLogin.putExtra("dni",dni);
                        startActivity(intBtnLogin);
                        finish();
                    } else {
                        txtRes.setText("Debe Registrarse");
                    }

                } catch (Exception ex) {
                    Toast.makeText(getApplication(),ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                //txtRes.setText("ERROR, No hay datos");
                Toast.makeText(getApplication(), error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        //Access the RequestQueue throught you singleton class.
        RequestQueue cola= Volley.newRequestQueue(this);
        cola.add(jobs);
    }

}