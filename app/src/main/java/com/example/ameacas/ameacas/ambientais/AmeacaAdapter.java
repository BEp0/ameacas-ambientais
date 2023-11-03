package com.example.ameacas.ameacas.ambientais;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AmeacaAdapter extends BaseAdapter {

    LayoutInflater inflater;
    AmeacaDataBase db;

    public AmeacaAdapter(Context ctx, AmeacaDataBase db) {
        inflater = LayoutInflater.from(ctx);
        this.db = db;
    }

    @Override
    public int getCount() {
        return db.getAmeacas().size();
    }

    @Override
    public Object getItem(int position) {
        return db.getAmeacas().get(position);
    }

    @Override
    public long getItemId(int position) {
        return db   .getAmeacas().get(position).getId();
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.activity_item_ameaca, null);
        TextView descricao = view.findViewById(R.id.descricao);
        descricao.setText(db.getAmeacas().get(position).getDescricao());

        TextView data = view.findViewById(R.id.data);
        data.setText(db.getAmeacas().get(position).getData());

        return view;
    }
}
