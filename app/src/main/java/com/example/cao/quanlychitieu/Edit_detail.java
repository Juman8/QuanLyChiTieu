package com.example.cao.quanlychitieu;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.cao.quanlychitieu.adapter.ChitietAdapter;
import com.example.cao.quanlychitieu.model.ChiTiet;
import com.example.cao.quanlychitieu.model.Group;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.cao.quanlychitieu.MainActivity.LINK;
import static com.example.cao.quanlychitieu.MainActivity.USERNAME;
import static com.example.cao.quanlychitieu.viewcustom.BaiDangActivity.TITLE;

/**
 * Created by HP on 2/6/2018.
 */

public class Edit_detail extends AppCompatActivity implements AdapterView.OnItemLongClickListener{
    Button btn_Savedata;
    Button btn_Updatedata;
    ListView lv_data;
    DatabaseReference database;
    ArrayList<ChiTiet> details;
    ChitietAdapter adapter;
    Intent intent;
    String Title;
    String Username;
    Group group;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_detail_layout);
        group = new Group();

        intent = this.getIntent();
        Title = intent.getStringExtra(TITLE);//lay ra tieu de cua group
        Username = intent.getStringExtra(USERNAME);//lay ra username

        wighet();
        String sr = getkey(Title);
        getdata(sr);
        database = FirebaseDatabase.getInstance().getReference();
        setSavedata();

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        return false;
    }
    public void wighet(){
        btn_Savedata = (Button)findViewById(R.id.btn_savedatadetail);
        btn_Updatedata = (Button)findViewById(R.id.btn_updatedata);
        details = new ArrayList<ChiTiet>();
    }
    public String getkey(String ii){
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReferenceFromUrl(LINK+"Group");
        Query query = ref.orderByChild("Group_Title").equalTo(ii);
        DatabaseReference statusRef = query.getRef().child("ID");
        statusRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                group = dataSnapshot.getValue(Group.class);
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
        return group.getID();
        }
    public void getdata(String KeyID){
      //title group

        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReferenceFromUrl(LINK+"Group/"+KeyID+"/Detail");

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                details.add((ChiTiet) dataSnapshot.getValue());

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
    public void setSavedata(){
        btn_Savedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //thêm dữ liệu
            }
        });
        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                details.add((ChiTiet) dataSnapshot.getValue());
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
