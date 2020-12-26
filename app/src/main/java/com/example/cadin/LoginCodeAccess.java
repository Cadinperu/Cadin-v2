package com.example.cadin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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

import java.io.Serializable;

public class LoginCodeAccess extends AppCompatActivity implements View.OnClickListener, Serializable {
JSONArray vector;
JsonObjectRequest jobs;
SmsManager smsManager;
SmsMessage smsMessage;
EditText edCod;
Button btnLoginAccess;
TextView txtRegistro, txtRes;
    //http://10.0.2.2/utp/cadin/session.php/https://cadin.000webhostapp.com/session.php
    String url="https://cadin.000webhostapp.com/session.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_code_access);

        btnLoginAccess=findViewById(R.id.buttonLoginAccess);
        edCod=findViewById(R.id.edCodUsuario);

    }

    @Override
    public void onClick(View v) {

    }

    public void loginPanelAccess(View v) {

        //captura el valor enviado del login
        String dniUsuario=getIntent().getStringExtra("dni");

        //Verifica si DNI esta registrado
        String urlSessionAccess=url+"?tag=validatecode&cod="+edCod.getText().toString()+"&dni="+dniUsuario  ;

        jobs= new JsonObjectRequest(Request.Method.GET, urlSessionAccess, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject DataUser = response.getJSONObject("dato");

                    if (DataUser.length() > 0){
                        Intent intBtnLoginAccess = new Intent(LoginCodeAccess.this, PanelPrincipal.class);
                        ///Bundle bundle=new Bundle();
                        //bundle.putSerializable("DataUser", DataUser.toString());
                        //intBtnLoginAccess.putExtras(bundle);
                        intBtnLoginAccess.putExtra("DataUser", DataUser.toString());
                        intBtnLoginAccess.putExtra("Id", DataUser.getInt("id"));
                        intBtnLoginAccess.putExtra("Dni", DataUser.getString("dni"));
                        startActivity(intBtnLoginAccess);
                        finish();
                    }

                } catch (Exception ex){
                    Toast.makeText(getApplication(),ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplication(), error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue cola= Volley.newRequestQueue(this);
        cola.add(jobs);
    }
}