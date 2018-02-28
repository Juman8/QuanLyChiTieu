package com.example.cao.quanlychitieu;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cao.quanlychitieu.model.Group;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import static com.example.cao.quanlychitieu.LoginActivity.LINKS;
import static com.example.cao.quanlychitieu.MainActivity.LINK;
import static com.example.cao.quanlychitieu.MainActivity.USERNAME;

/**
 * Created by HP on 2/6/2018.
 */

public class Detail extends AppCompatActivity{
    TextView tv_Title;
    ListView listView;
    ListView listViewNguoidung;
    List<Detail> details;
    List<String> stringList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietbaibang_layout);
        init();
    }
    public void init(){
        tv_Title = (TextView)findViewById(R.id.tv_title);
        listView = (ListView)findViewById(R.id.lv_chitietbaidang);
        listViewNguoidung = findViewById(R.id.lv_nguoidung);
        stringList = new ArrayList<String>();
        Intent intent = getIntent();
        String name = intent.getStringExtra(USERNAME);
        String key = getkey(name);
        initlistnguoidung(key);
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringList);
        listViewNguoidung.setAdapter(itemsAdapter);
    }
    private String getkey(String name){
        final String[] sc = new String[1];
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReferenceFromUrl(LINK+"Group");
        Query query = ref.orderByChild("Group_Title").equalTo(name);
        DatabaseReference statusRef = query.getRef();
        statusRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Group group = dataSnapshot.getValue(Group.class);
                sc[0] = group.getID();
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
        return sc[0];
    }
    public void initlistnguoidung(String name){
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReferenceFromUrl(LINK+"Group"+name+"Gmail");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                stringList.add(dataSnapshot.getValue(String.class));
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
    public void initlistdetail(String name){
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReferenceFromUrl(LINK+"Group"+name+"Detail");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                details.add(dataSnapshot.getValue(Detail.class));
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
