package com.example.cadin.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cadin.R;
import com.example.cadin.model.Notify;
import com.example.cadin.model.Vacunas;

import java.util.List;

public class NotifyAdapter extends BaseAdapter {
    Context context;
    List<Notify> listItems;

    public NotifyAdapter(Context context, List<Notify> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Notify Item = (Notify) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.vista_listado_notify, null);

        ((TextView) convertView.findViewById(R.id.txtNotifyItem)).setText(Item.getTxtitem());

        return convertView;
    }
}
