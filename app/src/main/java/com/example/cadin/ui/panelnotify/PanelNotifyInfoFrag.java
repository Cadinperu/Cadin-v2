package com.example.cadin.ui.panelnotify;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cadin.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PanelNotifyInfoFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PanelNotifyInfoFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PanelNotifyInfoFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PanelNotifyInfoFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static PanelNotifyInfoFrag newInstance(String param1, String param2) {
        PanelNotifyInfoFrag fragment = new PanelNotifyInfoFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle datosArguments = getArguments();
        if (datosArguments != null) {
            mParam1 = datosArguments.getString(ARG_PARAM1);
            mParam2 = datosArguments.getString(ARG_PARAM2);
        }

        String textoa = datosArguments.getString("texto");
        Log.w("textoa", textoa);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle datosArguments = getArguments();
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_panel_notify_info, container, false);


        String text = datosArguments.getString("texto");
        Log.w("text", text);
        TextView dataText = root.findViewById(R.id.txtDataListNotify);
        dataText.setText(text);

        return root;
    }
}