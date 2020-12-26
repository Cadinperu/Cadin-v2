package com.example.cadin.ui.panelhistorial;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cadin.R;
import com.example.cadin.model.Vacunas;
import com.example.cadin.ui.adapter.VacunasAdapter;
import com.example.cadin.ui.fragmentviews.VacunaFragment;
import com.example.cadin.ui.panelnotify.PanelNotifyViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PanelHistorialFrag extends Fragment {
    private JsonObjectRequest jobs;
    private RecyclerView recy;
    private List<Vacunas> listaVac;
    private TextView txtNombre, txtDni, txtVacunas, txtTelefono, txtContVac;
    private ImageView imgFoto;
    private Button btnregvac, btndetails;
    private String idUser;
    private String dniUser;
    String url="https://cadin.000webhostapp.com/vacunas.php";

    private PanelHistorialViewModel historialViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        historialViewModel =
                ViewModelProviders.of(this).get(PanelHistorialViewModel.class);

        Bundle datosArguments = getArguments();
        View root = inflater.inflate(R.layout.fragment_panel_historial, container, false);
        //final TextView textView = root.findViewById(R.id.text_historial);
//        historialViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        if (datosArguments != null) {

            idUser = datosArguments.getString("iduser");
            dniUser = datosArguments.getString("dniuser");
            dataVacunas(idUser,dniUser);
        } else {
            idUser="1";
            dniUser="44447777";
            dataVacunas(idUser,dniUser);
        }



        //TextView dataText = root.findViewById(R.id.txtDataListNotify);
        //dataText.setText(text);


        //Captura el valor enviado del HOME
        //idUser= getActivity().getIntent().getIntExtra("iduser", 0);
       // dniUser= getActivity().getIntent().getStringExtra("Dni");


//        txtNombre=root.findViewById(R.id.txtNomUsr1);
//        txtDni=root.findViewById(R.id.txtNumDni1);
//        txtVacunas=root.findViewById(R.id.txtCantVacunas1);
//        txtContVac=root.findViewById(R.id.txtContadorVac1);
        //lwVac=root.findViewById(R.id.listaHistorialVac);
        recy=root.findViewById(R.id.listaHistorialVacPage);

        return root;
    }

    public void dataVacunas(String id, final String dni) {
        //Realizamos la consulta JSON por Url
        String urlVacunas=url+"?tag=regvacunas&id="+id+"&dni="+dni;
        listaVac=new ArrayList<>();

        jobs= new JsonObjectRequest(Request.Method.GET, urlVacunas, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // Obtenemos la data de listado de vacunas
                    JSONArray vacunas= response.getJSONArray("dato");
                    int cantVacunas = vacunas.length();
                    for (int f=0;f<cantVacunas;f++) {
                        JSONObject fila=(JSONObject) vacunas.get(f);
                        int fil=f + 1;
                        Vacunas vac= new Vacunas();
                        vac.setId(fila.getInt("idReg"));
                        vac.setNom(fila.getString("nombreVac"));
                        vac.setFecha(fila.getString("fechaApl"));
                        vac.setCantdos(fila.getString("cantDos"));
                        listaVac.add(vac);
                    }

                    // Seteamos en el visorTextview la cantidad de Vacunas
//                    String cantVacunasTxt = ""+cantVacunas + " registrados";
//                    txtVacunas.setText(cantVacunasTxt);
//                    if (cantVacunas<10){
//                        txtContVac.setText("0"+cantVacunas);
//                    } else {
//                        txtContVac.setText(""+cantVacunas);
//                    }

                    // Seteamos la lista por Adaptador

                    recy.setLayoutManager( new LinearLayoutManager(getContext()));
                    final VacunasAdapter vacAd = new VacunasAdapter(listaVac, getContext());

                    vacAd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //POSITION ITEM
//                            int itemvacpos=recy.getChildAdapterPosition(v);
//                            Vacunas itemvac= listaVac.get(itemvacpos);
                            int idv=listaVac.get(recy.getChildAdapterPosition(v)).getId();
                            String id=""+idv;

                            //FRAGMENT
                            VacunaFragment vacfragmento= new VacunaFragment();
                            Bundle result = new Bundle();
                            result.putString("id", id);

                            vacfragmento.setArguments(result);
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.add(R.id.containerFragments, vacfragmento);
                            //fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();

                            Toast.makeText(getContext(), "Registro Nro.:"+listaVac.get(recy.getChildAdapterPosition(v)).getFecha(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    recy.setAdapter(vacAd);

                } catch (Exception ex) {
                    Log.w("testData", ex.getMessage());
                    Toast.makeText(getContext(),ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue cola= Volley.newRequestQueue(getContext());
        cola.add(jobs);
    }


}