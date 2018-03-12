package com.example.cao.quanlychitieu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cao.quanlychitieu.Fragment_quanlybaidang;
import com.example.cao.quanlychitieu.R;
import com.example.cao.quanlychitieu.model.Group;

import java.util.ArrayList;

/**
 * Created by HP on 3/12/2018.
 */

public class testCustom extends BaseAdapter {

    ArrayList<Group> listData;
    String[] name2;
    Context context;
    int[] imgid;
    private static LayoutInflater inf = null;

    public testCustom(Fragment_quanlybaidang onstage, ArrayList<Group> prgmNameList,Context context) {
        // TODO Auto-generated constructor stub
        listData = prgmNameList;
        this.context = context;
        inf = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listData.size();
    }


    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }


    public class Holder {
        TextView tv1, tv2;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inf.inflate(R.layout.custom_layout_quanlybaidang, null);
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