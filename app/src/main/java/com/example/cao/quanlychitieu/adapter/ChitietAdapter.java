package com.example.cao.quanlychitieu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cao.quanlychitieu.R;
import com.example.cao.quanlychitieu.model.ChiTiet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 2/11/2018.
 */

public class ChitietAdapter extends BaseAdapter {
    ArrayList<ChiTiet> listData;
    LayoutInflater inflater;

    public ChitietAdapter(ArrayList<ChiTiet> listData, Context context) {
        this.listData = listData;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<ChiTiet> getListData() {
        return listData;
    }

    public void setListData(ArrayList<ChiTiet> listData) {
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public ChiTiet getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.custom_chitietchitieu_layout, null);
            viewHolder.tv_Thongtin = (TextView) convertView.findViewById(R.id.tv_thongtin);
            viewHolder.tv_Gia = (TextView) convertView.findViewById(R.id.tv_gia);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ChiTiet group = listData.get(position);
        viewHolder.tv_Thongtin.setText(group.getChiTiet_MatHang());
        viewHolder.tv_Gia.setText((int) group.getChiTiet_GiaTri());

        return convertView;
    }

    public class ViewHolder {
        TextView tv_Thongtin, tv_Gia;
    }

    ;
}
