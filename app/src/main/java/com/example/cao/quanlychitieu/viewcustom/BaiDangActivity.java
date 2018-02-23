package com.example.cao.quanlychitieu.viewcustom;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.cao.quanlychitieu.AddGroup;
import com.example.cao.quanlychitieu.Edit_detail;
import com.example.cao.quanlychitieu.adapter.baidangAdapter;
import com.example.cao.quanlychitieu.model.Group;
import com.example.cao.quanlychitieu.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import static com.example.cao.quanlychitieu.MainActivity.LINK;
import static com.example.cao.quanlychitieu.MainActivity.USERNAME;

/**
 * Created by HP on 2/9/2018.
 */

public class BaiDangActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public final static String TITLE = "title";
    ListView lv_data;
    List<Group> listbaidang;
    baidangAdapter adapter;
    DatabaseReference database;
    ImageView img_Add;
    Intent intent;
    String Username;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlybaidang_layout);
        wighet();
        setAdapter();
        getdata();
        intent = this.getIntent();
        Username = intent.getStringExtra(USERNAME);
        img_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BaiDangActivity.this, AddGroup.class);
                intent.putExtra(USERNAME,Username);
                startActivity(intent);
            }
        });
    }

    public void wighet() {
        lv_data = (ListView) findViewById(R.id.lv_danhsachchitieu);
        img_Add = (ImageView) findViewById(R.id.img_addgroup);
        listbaidang = new ArrayList<>();
        adapter = new baidangAdapter(BaiDangActivity.this, R.layout.custom_layout_quanlybaidang, listbaidang);
        lv_data.setAdapter(adapter);
        // checkAndRequesPermission();
        database = FirebaseDatabase.getInstance().getReference();
    }

    public void setAdapter() {
        if (adapter == null) {
            adapter = new baidangAdapter(this, R.layout.custom_layout_quanlybaidang, listbaidang);
        } else {
            adapter.notifyDataSetChanged();
            lv_data.setSelection(adapter.getCount() - 1);
        }
    }

    public void getdata() {
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReferenceFromUrl(LINK + "Group");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Group group = dataSnapshot.getValue(Group.class);
                listbaidang.add(group);
                adapter.notifyDataSetChanged();
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
        Intent intent = new Intent(BaiDangActivity.this, Edit_detail.class);
        Group group = listbaidang.get(position);
        intent.putExtra(TITLE, group.getGroup_Title());
        intent.putExtra(USERNAME,Username);
        startActivity(intent);
    }
}
