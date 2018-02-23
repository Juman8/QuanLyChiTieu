package com.example.cao.quanlychitieu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.cao.quanlychitieu.model.Group;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import static com.example.cao.quanlychitieu.MainActivity.LINK;
import static com.example.cao.quanlychitieu.MainActivity.USERNAME;

/**
 * Created by HP on 2/2/2018.
 */

public class AddGroup extends AppCompatActivity {
    private final static String GROUP = "group";
    DatabaseReference database;
    private Button btn_Add;
    private EditText edt_Title;
    private EditText edt_Direction;
    private EditText edt_Sodu;
    private RadioButton rd_Only;
    private RadioButton rd_pub;
    private int value;
    public final static String STATUS = "status";
    public final static String LINKs = "https://quanlychitieu-9316c.firebaseio.com/Group/";
    Intent intent;
    String Username;
    String username;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgroup_layout);

        database = FirebaseDatabase.getInstance().getReference();
        initwighet();
        getUserKey();
        setcheck();
        setOnclicked(AddGroup.this);
    }

    public void initwighet() {
        btn_Add = (Button) findViewById(R.id.btn_add);
        edt_Title = (EditText) findViewById(R.id.edt_title);
        edt_Direction = (EditText) findViewById(R.id.edt_direction);
        edt_Sodu = (EditText) findViewById(R.id.edt_sodu);
        rd_Only = (RadioButton) findViewById(R.id.rd_only);
        rd_pub = (RadioButton) findViewById(R.id.rd_pub);
    }
    private void getUserKey() {
        //Khởi tạo SharedPreferences có tên "MyShare"
        SharedPreferences share = getSharedPreferences("Profile", MODE_PRIVATE);
        username = share.getString("Gmail", "");
    }


    public void setOnclicked(final Context context) {

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()) {
                    final String Title = edt_Title.getText().toString();
                    final String keyID = FirebaseDatabase.getInstance().getReference().push().getKey();
                    Group group = new Group(keyID,edt_Title.getText().toString(), Integer.parseInt(edt_Sodu.getText().toString()), edt_Direction.getText().toString(), 0, 0,value);

                    database.child(GROUP).push().setValue(group, new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            database.child(GROUP).child(keyID).child("1").child("Gmail").push().setValue(username);
                            Toast.makeText(context, "Sucessfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, Edit_detail.class);
                            intent.putExtra(STATUS, Title);
                            startActivity(intent);
                            finish();
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

        });

    }

    public boolean checkname(String edt_Title) {
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReferenceFromUrl(LINKs+Username);

        Query query = ref.orderByChild("Group_Title").equalTo(edt_Title);
        if (query.equals(edt_Title)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean check() {
        String title = edt_Title.getText().toString().trim();
        if (title.isEmpty())
            return false;
        return true;
    }

    public void setcheck() {
        if (rd_Only.isChecked()) {
            value = 1;
        } else {
            value = 2;
        }
    }

    private void add_group() {

    }
}
