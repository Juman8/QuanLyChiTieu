package com.example.cao.quanlychitieu.viewcustom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.cao.quanlychitieu.AddGroup;
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

import static com.example.cao.quanlychitieu.MainActivity.LINK;
import static com.example.cao.quanlychitieu.MainActivity.USERNAME;

/**
 * Created by HP on 2/9/2018.
 */

public class BaiDangActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public final static String TITLE = "title";
    baidangAdapter adapter;
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
        wighet();
        getData(name);
        adapter();
        //setAdapter();
        intent = this.getIntent();
        Username = intent.getStringExtra(USERNAME);
        img_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BaiDangActivity.this, AddGroup.class);
                intent.putExtra(USERNAME, Username);
                startActivity(intent);
            }
        });
    }

    public void wighet() {
        img_Add = (ImageView) findViewById(R.id.img_addgroup);
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
    public void getData(String username) {
        listData = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReferenceFromUrl(LINK + "Group");
        Query query = ref.orderByChild("User_Gmail").equalTo(username);
        DatabaseReference statusRef = query.getRef();
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Group group = snapshot.getValue(Group.class);
                    listData.add(group);
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
