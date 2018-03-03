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
import com.example.cao.quanlychitieu.model.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 2/9/2018.
 */

public class baidangAdapter extends BaseAdapter {
    ArrayList<Group> listData;
    LayoutInflater inflater;

    public baidangAdapter(ArrayList<Group> listData, Context context) {
        this.listData = listData;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<Group> getListData() {
        return listData;
    }

    public void setListData(ArrayList<Group> listData) {
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Group getItem(int position) {
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
            convertView = inflater.inflate(R.layout.custom_layout_quanlybaidang, null);
            viewHolder.img_Avata = (ImageView) convertView.findViewById(R.id.img_anhgroup);
            viewHolder.tv_Title = (TextView) convertView.findViewById(R.id.tv_customtitle);
            viewHolder.tv_Fund = (TextView) convertView.findViewById(R.id.tv_fund);
            viewHolder.tv_Somuc = (TextView) convertView.findViewById(R.id.tv_somuc);
            viewHolder.tv_Tongchitieu = (TextView) convertView.findViewById(R.id.tv_tongchitieu);
            viewHolder.tv_Trangthai = (TextView) convertView.findViewById(R.id.tv_trangthai);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Group group = listData.get(position);
        viewHolder.tv_Title.setText(group.getGroup_Title().toString());
        viewHolder.tv_Fund.setText(group.getGroup_SoDu());
        viewHolder.tv_Tongchitieu.setText(group.getGroup_TongChiTieu());
        viewHolder.tv_Trangthai.setText(group.getGroup_Thuoctinh());

        return convertView;
    }

    public class ViewHolder {
        ImageView img_Avata;
        TextView tv_Title, tv_Fund, tv_Somuc, tv_Tongchitieu, tv_Trangthai;
    };
}
