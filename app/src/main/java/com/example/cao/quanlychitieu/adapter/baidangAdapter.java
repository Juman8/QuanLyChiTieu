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
import com.example.cao.quanlychitieu.model.Group;

import java.util.List;

/**
 * Created by HP on 2/9/2018.
 */

public class baidangAdapter extends ArrayAdapter {
    private int resource;
    private Context context;
    private List<Group> lvDataGroup;

    public baidangAdapter(@NonNull Context context, int resource, @NonNull List objects) {
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
            viewHolder.img_Avata = (ImageView) convertView.findViewById(R.id.img_anhgroup);
            viewHolder.tv_Title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tv_Fund = (TextView) convertView.findViewById(R.id.tv_fund);
            viewHolder.tv_Somuc = (TextView) convertView.findViewById(R.id.tv_somuc);
            viewHolder.tv_Tongchitieu = (TextView) convertView.findViewById(R.id.tv_tongchitieu);
            viewHolder.tv_Trangthai = (TextView) convertView.findViewById(R.id.tv_trangthai);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Group group = lvDataGroup.get(position);
        viewHolder.tv_Title.setText(group.getGroup_Title());
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
