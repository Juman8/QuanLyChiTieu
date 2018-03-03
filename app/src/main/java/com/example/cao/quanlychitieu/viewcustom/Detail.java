package com.example.cao.quanlychitieu.viewcustom;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.TextView;

        import com.example.cao.quanlychitieu.R;
        import com.example.cao.quanlychitieu.adapter.ChitietAdapter;
        import com.example.cao.quanlychitieu.adapter.baidangAdapter;
        import com.example.cao.quanlychitieu.model.ChiTiet;
        import com.example.cao.quanlychitieu.model.Group;
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
 * Created by HP on 2/6/2018.
 */

public class Detail extends AppCompatActivity implements AdapterView.OnItemClickListener{
    TextView tv_Title;

    ListView listView;
    ListView listViewNguoidung;

    ChitietAdapter adapter;
    ArrayList<ChiTiet> listData;

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

        initlistdetail(key);
        initlistnguoidung(key);
        //setAdapter();
        listView = (ListView) findViewById(R.id.lv_danhsachchitieu);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringList);
        listViewNguoidung.setAdapter(itemsAdapter);

    }
    public void setAdapter() {
        if (adapter == null) {
            adapter = new ChitietAdapter(listData, Detail.this);
        } else {
            adapter.notifyDataSetChanged();
            listView.setSelection(adapter.getCount() - 1);
        }
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
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
                    String s1 = dataSnapshot1.getValue(String.class);
                    stringList.add(s1);
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
    public void initlistdetail(String name){
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReferenceFromUrl(LINK+"Group"+name+"Detail");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
                    ChiTiet s1 = dataSnapshot1.getValue(ChiTiet.class);
                    listData.add(s1);
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
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}

