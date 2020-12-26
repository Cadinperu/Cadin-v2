package com.example.cadin.ui.panelnotify;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cadin.R;
import com.example.cadin.model.Notify;
import com.example.cadin.ui.adapter.NotifyAdapter;
import com.example.cadin.ui.fragmentviews.VacunaFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PanelNotifyFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PanelNotifyFrag extends Fragment {

    //private PanelNotifyViewModel notifyViewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView lwNotify;
    private List<Notify> lista;


    public PanelNotifyFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PanelNotifyFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static PanelNotifyFrag newInstance(String param1, String param2) {
        PanelNotifyFrag fragment = new PanelNotifyFrag();
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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Bundle datosArguments = getArguments();
//        notifyViewModel =
//                ViewModelProviders.of(this).get(PanelNotifyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_panel_notify, container, false);
        //        final TextView textView = root.findViewById(R.id.text_notify);
        //        notifyViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
        //            @Override
        //            public void onChanged(@Nullable String s) {
        //                textView.setText(s);
        //            }
        //        });


        lista= new ArrayList<>();
        Resources res = getResources();
        String[] datos = res.getStringArray(R.array.str_notify_array);

        for (int f=0;f<datos.length;f++) {

            String data = datos[f];
            Notify noty = new Notify();
            noty.setTxtitem(data);
            lista.add(noty);
        }

        lwNotify=root.findViewById(R.id.listviewNotify);
        //Seteamos al Adaptador
        NotifyAdapter notyAd= new NotifyAdapter(getActivity().getApplication(), lista);
        lwNotify.setAdapter(notyAd);
        lwNotify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                irnoti(position);
            }
        });

        return root;
    }

    void irnoti(int position){
        //Cuando se elige un elemento de la lista
        //Log.w("position", String.valueOf(position));

        //POSITION ITEM
        Notify item=lista.get(position);

        String texto=item.getTxtitem();

//                ImageView statusNotifyIcon = root.findViewById(R.id.iconStatusNotify);

//                ImageView statusNotifyIcon = item.getIconitem();
//
//                statusNotifyIcon.getBackground()

//                img=item.getIconitem();
//                img.setBackgroundColor(Color.parseColor("#3bb85c"));

        //((TextView) convertView.findViewById(R.id.txtNotifyItem)).setText(Item.getTxtitem());


        //FRAGMENT
        PanelNotifyInfoFrag fragmento= new PanelNotifyInfoFrag();

        Bundle result = new Bundle();
        result.putString("texto", texto);

        fragmento.setArguments(result);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerFragments, fragmento);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }




}