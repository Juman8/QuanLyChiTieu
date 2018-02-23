package com.example.cao.quanlychitieu;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by HP on 2/6/2018.
 */

public class Detail extends AppCompatActivity{
    TextView tv_Title;
    ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietbaibang_layout);

    }
    public void init(){
        tv_Title = (TextView)findViewById(R.id.tv_title);
        listView = (ListView)findViewById(R.id.lv_chitietbaidang);
    }

}
