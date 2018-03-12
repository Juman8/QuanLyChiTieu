package com.example.cao.quanlychitieu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cao.quanlychitieu.adapter.baidangAdapter;
import com.example.cao.quanlychitieu.adapter.testCustom;
import com.example.cao.quanlychitieu.model.Group;
import com.example.cao.quanlychitieu.model.Group_Gmail;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.example.cao.quanlychitieu.MainActivity.LINK;
public class Fragment_quanlybaidang extends Fragment implements AdapterView.OnItemClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public final static String TITLE = "title";
    testCustom adapter;
    ArrayList<String> listGroupID_Data;
    ArrayList<Group> listData;
    ListView lvData;
    ImageView img_Add;
    Intent intent;
    String Username;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences share = this.getActivity().getSharedPreferences("Profile", MODE_PRIVATE);
        String name = share.getString("Gmail", "");
        String ID = share.getString("UsernameID","");
        laydanhsachIDGroup(ID);
        layradanhsachGroup(listGroupID_Data);
        //setAdapter();

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_quanlybaidang_layout, container, false);
        lvData=(ListView) rootView.findViewById(R.id.lv_danhsachchitieu);

        adapter=new testCustom(this, listData,getActivity());
        lvData.setAdapter(adapter);
        return rootView;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

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
}
