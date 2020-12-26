package com.example.cadin.model;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cadin.PanelPrincipal;
import com.example.cadin.R;
import com.example.cadin.ui.adapter.VacunasAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class Cartilla extends PanelPrincipal {
ListView lw;
List<Vacunas> listaA;
JsonObjectRequest jobs;
RequestQueue requestQueue;
Context contexto;
String url="https://cadin.000webhostapp.com/";

    public Cartilla(Context contexto) {
        this.contexto = contexto;
    }


    public List<Vacunas> listadoJson(String url, final String key) {
        Log.w("urlInJsonRequest", url);
        Log.w("keyInJsonRequest", key);
        final List<Vacunas> lista = new ArrayList<>();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrayList = response.getJSONArray(key);
                    Log.w("JSONARRAY", arrayList.toString());

                    for (int f=0;f<arrayList.length();f++) {
                        JSONObject fila=(JSONObject) arrayList.getJSONObject(f);
                        int fil=f + 1;
                        Vacunas vac= new Vacunas();
                        vac.setNom(fila.getString("nombreVac"));
                        vac.setFecha(fila.getString("fechaApl"));
                        vac.setCantdos(fila.getString("cantDos"));
                        lista.add(vac);
                        Log.w("LISTADOintoPARSE", lista.toString());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplication(),e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplication(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue= Volley.newRequestQueue(this.contexto);
        requestQueue.add(request);

        Log.w("listaInCartilla", lista.toString());

        return lista;
    }




    private List<Vacunas> listado(JSONArray jsonArray) {
        List<Vacunas> lista = new ArrayList<>();
        JSONArray vacunas = jsonArray;
        int cantVacunas = jsonArray.length();
        try {
            for (int f=0;f<cantVacunas;f++) {
                JSONObject fila=(JSONObject) vacunas.get(f);
                int fil=f + 1;
                Vacunas vac= new Vacunas();
                vac.setNom(fila.getString("nombreVac"));
                vac.setFecha(fila.getString("fechaApl"));
                vac.setCantdos(fila.getString("cantDos"));
                lista.add(vac);
                Log.w("dataLista", lista.toString());
                Log.w("dataListya", lista.get(0).toString());
            }


            RecyclerView recy= findViewById(R.id.listaHistorialVac);
            VacunasAdapter vacAd = new VacunasAdapter(lista, getApplicationContext());
            recy.setLayoutManager( new LinearLayoutManager(getParent()));
            recy.setAdapter(vacAd);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.w("listaAAAAAAAAAA", String.valueOf(lista.size()));

        Log.w("listaAAAAAAAAAA", lista.toString());
        return lista;

    }


    public void generateList(String url, final String key){

        jobs= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // Obtenemos la data de listado de vacunas
                    JSONArray vacunas= response.getJSONArray(key);
                    Log.w("testData", vacunas.toString());
                    listado(vacunas);

                    // Seteamos en el visorTextview la cantidad de Vacunas
//                    String cantVacunasTxt = ""+cantVacunas + " registrados";
//                    txtVacunas.setText(cantVacunasTxt);
//                    if (cantVacunas<10){
//                        txtContVac.setText("0"+cantVacunas);
//                    } else {
//                        txtContVac.setText(""+cantVacunas);
//                    }

                    // Seteamos la lista por Adaptador


                } catch (Exception ex) {
                    Log.w("testData", ex.getMessage());
                    //Toast.makeText(getApplicationContext(),ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue cola= Volley.newRequestQueue(this.contexto);
        cola.add(jobs);
    }



}
