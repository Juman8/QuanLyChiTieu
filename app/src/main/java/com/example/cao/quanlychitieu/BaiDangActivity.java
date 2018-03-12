package com.example.cao.quanlychitieu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cao.quanlychitieu.adapter.baidangAdapter;
import com.example.cao.quanlychitieu.model.Group;
import com.example.cao.quanlychitieu.model.Group_Gmail;
import com.example.cao.quanlychitieu.viewcustom.Detail;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import static com.example.cao.quanlychitieu.MainActivity.LINK;
import static com.example.cao.quanlychitieu.MainActivity.USERNAME;

/**
 * Created by HP on 2/9/2018.
 */

public class BaiDangActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public final static String TITLE = "title";
    baidangAdapter adapter;
    ArrayList<String> listGroupID_Data;

    ArrayList<Group> listData;
    ListView lvData;
    ImageView img_Add;
    Intent intent;
    String Username;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlybaidang_layout);
        SharedPreferences share = getSharedPreferences("Profile", MODE_PRIVATE);
        String name = share.getString("Gmail", "");
        String ID = share.getString("UsernameID","");
        wighet();
        laydanhsachIDGroup(ID);

        layradanhsachGroup(listGroupID_Data);
        Toast.makeText(BaiDangActivity.this,listData.size()+"",Toast.LENGTH_SHORT).show();
        adapter();
        setAdapter();
    }

    public void wighet() {
        //img_Add = (ImageView) findViewById(R.id.img_addgroup);
        listData = new ArrayList<>();
    }
    public void adapter(){
        adapter = new baidangAdapter(listData, BaiDangActivity.this);
        lvData = (ListView) findViewById(R.id.lv_danhsachchitieu);
        lvData.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void setAdapter() {
        if (adapter == null) {
            adapter = new baidangAdapter(listData, BaiDangActivity.this);
        } else {
            adapter.notifyDataSetChanged();
            lvData.setSelection(adapter.getCount() - 1);
        }
    }

    public void layradanhsachGroup(ArrayList username) {
        listData = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReferenceFromUrl(LINK + "Group");
        for (int i = 0; i < username.size(); i++) {
            String ID = (String) username.get(i);
            Query query = ref.orderByChild("ID").equalTo(ID);
            DatabaseReference statusRef = query.getRef();
            ref.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Group group = snapshot.getValue(Group.class);
                        listData.add(group);
                    }
                    Log.d("Size Listdata", listData.size()+"");
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
    private void laydanhsachIDGroup(String ID){
        listGroupID_Data = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReferenceFromUrl(LINK + "Group_Gmail/"+ID);
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Group_Gmail groupGmail = dataSnapshot1.getValue(Group_Gmail.class);
                    listGroupID_Data.add(groupGmail.getGmail());
                    Log.d("Size Listdata", listGroupID_Data.size()+"");
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(BaiDangActivity.this, Detail.class);
        Group group = listData.get(position);
        intent.putExtra(TITLE, group.getGroup_Title());
        intent.putExtra(USERNAME,Username);
        startActivity(intent);
    }
}
