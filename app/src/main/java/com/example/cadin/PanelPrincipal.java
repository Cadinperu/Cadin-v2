package com.example.cadin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cadin.ui.fragmentviews.VacunaFragment;
import com.example.cadin.ui.fragmentviews.VacunaRegistroFrag;
import com.example.cadin.ui.panelhistorial.PanelHistorialFrag;
import com.example.cadin.ui.panelhome.PanelHomeFrag;
import com.example.cadin.ui.panelnotify.PanelNotifyFrag;
import com.example.cadin.ui.panelsettings.PanelSettingFrag;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PanelPrincipal extends AppCompatActivity {

    String url="https://cadin.000webhostapp.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_principal);

        BottomNavigationView bottomNav=findViewById(R.id.nav_view);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // Seteamos la pantalla de Inicio del Panel
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFragments, new PanelHomeFrag()).commit();

//        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.panelHomeFrag, R.id.panelNotifyFrag, R.id.panelHistorialFrag, R.id.panelSettingFrag).build();
//
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);

    }

    //Navigation Bar
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.panelHomeFrag:
                            selectedFragment = new PanelHomeFrag();
                            break;
                        case R.id.panelNotifyFrag:
                            selectedFragment = new PanelNotifyFrag();
                            break;
                        case R.id.panelHistorialFrag:
                            selectedFragment = new PanelHistorialFrag();
                            break;
                        case R.id.panelSettingFrag:
                            selectedFragment = new PanelSettingFrag();
                    }
                    /*
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    if (selectedFragment.isAdded()) {
                        transaction
                                .hide(currentFragment)
                                .show(selectedFragment);
                    } else {
                        transaction
                                .hide(currentFragment)
                                .add(R.id.containerFragments, selectedFragment);
                    }*/

                    //Begin Transaction

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containerFragments,
                                    selectedFragment)
                            .commit();
                    return true;
                }

            };

    /* ACCIONES DE BOTONES ----------------------------------------------------------*/

    public void retornarFrag(View view)
    {
        FragmentManager fm = this.getSupportFragmentManager();
        fm.popBackStack();
    }



}