package com.example.cadin.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadin.R;
import com.example.cadin.model.Vacunas;

import java.util.List;

public class VacunasAdapter extends RecyclerView.Adapter<VacunasAdapter.MyHolder> implements View.OnClickListener {
    List<Vacunas> lista;
    Context contexto;
    private View.OnClickListener listener;

    public VacunasAdapter(List<Vacunas> lista, Context contexto) {
        this.lista = lista;
        this.contexto = contexto;
    }

    @NonNull
    @Override
    public VacunasAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater infla=LayoutInflater.from(contexto);
        View vis = infla.inflate(R.layout.vista_listado_vac,parent,false);

        vis.setOnClickListener(this);

        return new VacunasAdapter.MyHolder(vis);
    }

    @Override
    public void onBindViewHolder(@NonNull VacunasAdapter.MyHolder holder, int position) {
        Vacunas v=lista.get(position);
        holder.tNom.setText(v.getNom());
        holder.tFecha.setText(v.getFecha());
        holder.tDos.setText(v.getCantdos());
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener=listener;
    }

    @Override
    public void onClick(View v) {
        if (listener!=null) {
            listener.onClick(v);
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tNom, tFecha, tDos;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tNom=itemView.findViewById(R.id.itemNombreVac);
            tFecha=itemView.findViewById(R.id.itemFechaVac);
            tDos=itemView.findViewById(R.id.itemDosisVac);
        }
    }

}
