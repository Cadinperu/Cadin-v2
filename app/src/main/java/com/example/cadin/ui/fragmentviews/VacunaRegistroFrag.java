package com.example.cadin.ui.fragmentviews;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cadin.R;
import com.example.cadin.model.Licenciado;
import com.example.cadin.model.Triaje;
import com.example.cadin.model.Vacunas;
import com.example.cadin.ui.panelhome.PanelHomeFrag;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VacunaRegistroFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VacunaRegistroFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<String> listItems=new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayAdapter<Licenciado> listLicenciado;
    private Button buttonregvac;
    private Spinner sprgvidvac, sprgvidtri, sprgvidlic;
    private EditText edrgvnom, edrgvdni, edrgvfchvac, edrgvcantdos, edrgvmotvac;
    private TextView txtrgvidcli, txtrgvidvac, txtrgvidtri, txtrgvidlic;
    private String strgvidcli;

    public VacunaRegistroFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VacunaRegistroFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static VacunaRegistroFrag newInstance(String param1, String param2) {
        VacunaRegistroFrag fragment = new VacunaRegistroFrag();
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
        final View root= inflater.inflate(R.layout.fragment_vacuna_registro, container, false);

        //INFO-USUARIO
        String nomuser = datosArguments.getString("nomuser");
        String iduser = datosArguments.getString("iduser");
        String dniuser = datosArguments.getString("dniuser");
        EditText edNomUser = root.findViewById(R.id.edRegvac_NomUsr);
        EditText edDniUser = root.findViewById(R.id.edRegvac_Dni);
        edNomUser.setText(nomuser);
        edDniUser.setText(dniuser);


        edrgvcantdos= root.findViewById(R.id.edRegvac_CantDos);
        edrgvfchvac= root.findViewById(R.id.edRegVac_Date);
        edrgvmotvac=root.findViewById(R.id.edRegvac_MotVac);

        //NUMBER PICKER
        NumberPicker numberPicker = root.findViewById(R.id.numbpkRegVac_CantDos);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(3);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                edrgvcantdos.setText("0"+newVal);
            }
        });

        //CALENDARIO
        edrgvfchvac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        //SPINNER HIDE ID
        txtrgvidcli=root.findViewById(R.id.txtClienteId);
        txtrgvidcli.setText(iduser);
        txtrgvidvac=root.findViewById(R.id.txtSpinnerVacId);
        txtrgvidtri=root.findViewById(R.id.txtSpinnerTriajeId);
        txtrgvidlic=root.findViewById(R.id.txtSpinnerLicId);

        //SPINNER
        sprgvidvac=root.findViewById(R.id.spinnerVac);
        sprgvidtri=root.findViewById(R.id.spinnerTriaje);
        sprgvidlic=root.findViewById(R.id.spinnerLicenciado);
        dataLicenciado();
        dataTriaje();
        dataVac();

        sprgvidvac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //String dataText=sprgvidvac.getSelectedItem().toString();
                int pos=position+1;
                txtrgvidvac.setText(""+pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sprgvidlic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //String dataText=sprgvidlic.getSelectedItem().toString();
                //lic.setNombre(dataText);
                int pos=position+1;
                txtrgvidlic.setText(""+pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        sprgvidtri.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //String dataText=sprgvidtri.getSelectedItem().toString();
                //lic.setNombre(dataText);
                int pos=position+1;
                txtrgvidtri.setText(""+pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonregvac=root.findViewById(R.id.buttonRegVac1);
        buttonregvac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarVacuna();
            }
        });

        return root;
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 porque Enero es cero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                edrgvfchvac.setText(selectedDate);
            }
        });

        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    public void dataVac(){
        String urlConsulta="https://cadin.000webhostapp.com/vacunas.php?tag=listavacunas";
        //String urlConsulta="http://10.0.2.2/utp/cadin/vacunas.php?tag=listavacunas";

        final ArrayList<String> listVacunas = new ArrayList<>();
        JsonObjectRequest jobs= new JsonObjectRequest(Request.Method.GET, urlConsulta, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray= response.getJSONArray("dato");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject fila = (JSONObject) jsonArray.get(i);

                        Vacunas vc=new Vacunas();
                        vc.setCod(fila.getString("codvac"));
                        String codvac = vc.getCod();

                        listVacunas.add(codvac);
                    }

                    sprgvidvac.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listVacunas)); //asociar con spinner

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue cola= Volley.newRequestQueue(getContext());
        cola.add(jobs);
    }

    public void dataLicenciado(){
        String urlConsulta="https://cadin.000webhostapp.com/vacunas.php?tag=listalicenciado";
        //String urlConsulta="http://10.0.2.2/utp/cadin/vacunas.php?tag=listalicenciado";

        final ArrayList<String> list = new ArrayList<>();
        JsonObjectRequest jobs= new JsonObjectRequest(Request.Method.GET, urlConsulta, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray= response.getJSONArray("dato");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject fila = (JSONObject) jsonArray.get(i);

                        Licenciado lic=new Licenciado();
                        lic.setNomlic(fila.getString("nomlic"));
                        lic.setApeplic(fila.getString("apeplic"));
                        lic.setApemlic(fila.getString("apemlic"));
                        String nombreLic = lic.nombrelic();
                        String idlic = fila.getString("idlic");
                        String dnilic = fila.getString("dnilic");

                        list.add(nombreLic);

                        Log.w("licenciado", nombreLic);
                    }

                    Log.w("listadoLlice", list.toString());
                    sprgvidlic.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, list)); //asociar con spinner

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue cola= Volley.newRequestQueue(getContext());
        cola.add(jobs);
    }

    public void dataTriaje(){
        String urlConsulta="https://cadin.000webhostapp.com/vacunas.php?tag=listatriaje";
        //String urlConsulta="http://10.0.2.2/utp/cadin/vacunas.php?tag=listatriaje";

        final ArrayList<String> listTri = new ArrayList<>();
        JsonObjectRequest jobs= new JsonObjectRequest(Request.Method.GET, urlConsulta, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray= response.getJSONArray("dato");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject fila = (JSONObject) jsonArray.get(i);

                        Triaje tri=new Triaje();
                        tri.setIdtri(fila.getString("idtri"));
                        String idtri = tri.getIdtri();

                        listTri.add(idtri);
                    }

                    sprgvidtri.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listTri)); //asociar con spinner

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue cola= Volley.newRequestQueue(getContext());
        cola.add(jobs);
    }

    public void registrarVacuna(){

        final String idcli = txtrgvidcli.getText().toString();
        final String idvac = txtrgvidvac.getText().toString();
        final String fchvac= edrgvfchvac.getText().toString();
        final String cantdos = edrgvcantdos.getText().toString();
        final String motvac = edrgvmotvac.getText().toString();
        final String idtri = txtrgvidtri.getText().toString();
        final String idlic = txtrgvidlic.getText().toString();

        String urlAddVacuna="https://cadin.000webhostapp.com/addvacunas.php";
        //String urlAddVacuna="http://10.0.2.2/utp/cadin/addvacunas.php";


        //POSTEAR
        StringRequest stringRequest= new StringRequest(Request.Method.POST, urlAddVacuna, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity().getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("idcli", idcli);
                params.put("idvac", idvac);
                params.put("fchvac", fchvac);
                params.put("cantdos", cantdos);
                params.put("motvac", motvac);
                params.put("idtri", idtri);
                params.put("idlic", idlic);

                return params;
            }
        };

        RequestQueue cola= Volley.newRequestQueue(getContext());
        cola.add(stringRequest);

        //FRAGMENT
        PanelHomeFrag panelHomeFrag= new PanelHomeFrag();
        //Bundle result = new Bundle();
        //result.putString("id", id);

        // panelHomeFrag.setArguments(result);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerFragments, panelHomeFrag);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}