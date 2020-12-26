
package com.example.cadin.ui.fragmentviews;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cadin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VacunaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VacunaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView ifvidreg;
    TextView ifvidcli, ifvnom;
    TextView ifvidvac, ifvnomvac, ifvcodvac, ifvfchvac, ifvcantdos, ifvmotvac;
    TextView ifvidlic, ifvnomlic;
    TextView ifvidtri, ifvfchtri, ifvedad, ifvpeso, ifvtalla;

    public VacunaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VacunaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VacunaFragment newInstance(String param1, String param2) {
        VacunaFragment fragment = new VacunaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle datosArguments = getArguments();
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_vacuna, container, false);

        String id = datosArguments.getString("id");
        Log.w("idRecibido", id);

        //Definimos los elementos del layout
        //TextView txtInfo=root.findViewById(R.id.txtInfoVacuna);
        ifvidreg = root.findViewById(R.id.txtInfoVacNroReg);
        //ifvidcli = root.findViewById(R.id.txtInfoVacIdCli);
        //ifvidvac = root.findViewById(R.id.txtInfoVacIdVac);
        ifvidtri = root.findViewById(R.id.txtInfoVacIdTri);
        //ifvidlic = root.findViewById(R.id.txtInfoVacIdLic);

        ifvnomvac = root.findViewById(R.id.txtInfoVacNom);
        ifvcodvac = root.findViewById(R.id.txtInfoVacCod);
        ifvcantdos = root.findViewById(R.id.txtInfoVacCantDos);
        ifvfchvac = root.findViewById(R.id.txtInfoVacFecha);
        ifvmotvac = root.findViewById(R.id.txtInfoVacMotivo);

        ifvnomlic = root.findViewById(R.id.txtInfoVacfoNomLic);

        ifvfchtri = root.findViewById(R.id.txtInfoVacFechaTri);
        ifvedad = root.findViewById(R.id.txtInfoVacEdad);
        ifvpeso = root.findViewById(R.id.txtInfoVacPeso);
        ifvtalla = root.findViewById(R.id.txtInfoVacTalla);

        llenarInfoVac(id);

        ImageButton imageButton2 = root.findViewById(R.id.imageButton2);

        return root;

    }

    void llenarInfoVac(String id) {

        String url="https://cadin.000webhostapp.com/vacunas.php?tag=infovacuna&id="+id;
        JsonObjectRequest jobs = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                   JSONArray jsonArray= response.getJSONArray("dato");

                    //for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject fila= (JSONObject) jsonArray.get(0);

                    Log.w("fila", fila.toString());

                    //Información de Registro
                        String idreg = fila.getString("idreg");
                        String idcli = fila.getString("idcli");
                        String idtri = fila.getString("idtri");
                        String idlic = fila.getString("idlic");

                    //Información de Vacuna
                    JSONObject filaVac= fila.getJSONObject("vac");
                        String codvac = filaVac.getString("codvac");
                        String nomvac = filaVac.getString("nomvac");
                        String fchvac = filaVac.getString("fchvac");
                        String cantdos = filaVac.getString("cantdos");
                        String motvac = filaVac.getString("motvac");

                    //Información de Usuario
                    JSONObject filaUsr= fila.getJSONObject("usr");
                        String nom = filaUsr.getString("nom");
                        String apep = filaUsr.getString("apep");
                        String apem = filaUsr.getString("apem");


                    //Información de Licenciado
                    JSONObject filaLic= fila.getJSONObject("lic");
                        String nomlic = filaLic.getString("nomlic");

                     //Información de Triaje
                    JSONObject filaTri= fila.getJSONObject("tri");
                        String fchtri = filaTri.getString("fchtri");
                        String edad = filaTri.getString("edad");
                        String peso = filaTri.getString("peso");
                        String talla = filaTri.getString("talla");


                    ifvidreg.setText(idreg);
                    ifvnomvac.setText(nomvac);
                    ifvcodvac.setText(codvac);
                    ifvcantdos.setText(cantdos);
                    ifvfchvac.setText(fchvac);
                    ifvmotvac.setText(motvac);

                    //ifvnom.setText(nom);
                    //ifvapep.setText(ifvapep);
                    //ifvapem.setText(ifvapem);

                    ifvnomlic.setText(nomlic);

                    ifvidtri.setText(idtri);
                    ifvfchtri.setText(fchtri);
                    ifvedad.setText(edad);
                    ifvpeso.setText(peso);
                    ifvtalla.setText(talla);
                    //}


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue cola= Volley.newRequestQueue(getContext());
        cola.add(jobs);
    }
}