package com.example.cao.quanlychitieu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cao.quanlychitieu.R;
import com.example.cao.quanlychitieu.model.ChiTiet;
import com.example.cao.quanlychitieu.model.Group;

import java.util.List;

/**
 * Created by HP on 2/11/2018.
 */

public class ChitietAdapter extends ArrayAdapter {
    private int resource;
    private Context context;
    private List<ChiTiet> lvDataGroup;

    public ChitietAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.lvDataGroup = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_layout_quanlybaidang, parent, false);
            viewHolder.tv_Thongtin = (TextView) convertView.findViewById(R.id.tv_thongtin);
            viewHolder.tv_Gia = (TextView) convertView.findViewById(R.id.tv_gia);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ChiTiet group = lvDataGroup.get(position);
        viewHolder.tv_Thongtin.setText(group.getChiTiet_MatHang());
        viewHolder.tv_Gia.setText((int) group.getChiTiet_GiaTri());

        return convertView;
    }

    public class ViewHolder {
        TextView tv_Thongtin, tv_Gia;
    }

    ;
}
