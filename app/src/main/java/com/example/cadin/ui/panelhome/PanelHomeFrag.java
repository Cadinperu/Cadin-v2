package com.example.cadin.ui.panelhome;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cadin.R;
import com.example.cadin.model.Usuario;
import com.example.cadin.model.Vacunas;
import com.example.cadin.ui.adapter.VacunasAdapter;
import com.example.cadin.ui.fragmentviews.VacunaFragment;
import com.example.cadin.ui.fragmentviews.VacunaRegistroFrag;
import com.example.cadin.ui.panelhistorial.PanelHistorialFrag;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PanelHomeFrag extends Fragment {
    JsonObjectRequest jobs;
    RecyclerView recy;
    List<Vacunas> listaVac;
    TextView txtNombre, txtDni, txtVacunas, txtTelefono, txtContVac;
    ImageView imgFoto;
    Button btnregvac, btndetails;
    int idUser;
    String dniUser;
    String url="https://cadin.000webhostapp.com/vacunas.php";
    private PanelHomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        /*
        homeViewModel =
                 ViewModelProviders.of(this).get(PanelHomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_panel_home, container, false);

        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        final View root= inflater.inflate(R.layout.fragment_panel_home, container, false);

        txtNombre=root.findViewById(R.id.txtNomUsr);
        txtDni=root.findViewById(R.id.txtNumDni);
        txtVacunas=root.findViewById(R.id.txtCantVacunas);
        txtContVac=root.findViewById(R.id.txtContadorVac);
        //lwVac=root.findViewById(R.id.listaHistorialVac);
        recy=root.findViewById(R.id.listaHistorialVac);
        imgFoto=root.findViewById(R.id.imageFotoUsuario);

        //Acciones de Botones
        btndetails= root.findViewById(R.id.buttonDetailsVac);
        btnregvac= root.findViewById(R.id.buttonRegistrarVac);

        btndetails.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                detallesvac();
            }
        });

        btnregvac.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                registrarvac();
            }
        });


        //Captura el valor enviado del LoginCodeAccess
        idUser= getActivity().getIntent().getIntExtra("Id", 0);
        dniUser= getActivity().getIntent().getStringExtra("Dni");
        dataUsuario();
        dataVacunas(idUser,dniUser);
        return root;
    }

    public void dataUsuario(){
        JSONObject DataUser = null;
        try {
            // Generamos los datos del Usuario
            DataUser = new JSONObject(getActivity().getIntent().getStringExtra("DataUser"));

            Usuario usr= new Usuario();
            usr.setNom(DataUser.getString("nombre"));
            usr.setApellP(DataUser.getString("apellidoP"));
            usr.setApellM(DataUser.getString("apellidoM"));
            usr.setDniUser(DataUser.getString("dni"));
            usr.setTel(DataUser.getString("telefono"));

            // Seteamos los valores en el visorTexTView
            txtNombre.setText(usr.nombre());
            txtDni.setText(usr.dni());

            String urlFoto= "https://cadin.000webhostapp.com/fotos/"+DataUser.getString("foto")+".jpg";
            Picasso.with(getContext()).load(urlFoto).into(imgFoto);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void dataVacunas(int id, final String dni) {
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
                    String cantVacunasTxt = ""+cantVacunas + " registrados";
                    txtVacunas.setText(cantVacunasTxt);
                    if (cantVacunas<10){
                        txtContVac.setText("0"+cantVacunas);
                    } else {
                        txtContVac.setText(""+cantVacunas);
                    }

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
                            fragmentTransaction.replace(R.id.containerFragments, vacfragmento);
                            fragmentTransaction.addToBackStack(null);
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


    /* Acciones de Botones */


    public void detallesvac() {
        PanelHistorialFrag historialFrag = new PanelHistorialFrag();

        String nomUser=txtNombre.getText().toString();

        Bundle result = new Bundle();
        result.putString("nomuser", nomUser);
        result.putString("iduser", ""+idUser);
        result.putString("dniuser", dniUser);

        historialFrag.setArguments(result);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerFragments, historialFrag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void registrarvac() {
        VacunaRegistroFrag vacunaRegistroFrag= new VacunaRegistroFrag();

        String nomUser=txtNombre.getText().toString();

        Bundle result = new Bundle();
        result.putString("nomuser", nomUser);
        result.putString("iduser", ""+idUser);
        result.putString("dniuser", dniUser);

        vacunaRegistroFrag.setArguments(result);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerFragments, vacunaRegistroFrag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }




}