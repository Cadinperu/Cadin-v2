package com.example.cadin.ui.panelsettings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cadin.R;
import com.example.cadin.ui.panelhome.PanelHomeViewModel;

public class PanelSettingFrag extends Fragment {

    private PanelSettingViewModel settingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingViewModel =
                ViewModelProviders.of(this).get(PanelSettingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_panel_setting, container, false);
        final TextView textView = root.findViewById(R.id.text_settings);
        settingViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }

        });
        return root;
    }
}