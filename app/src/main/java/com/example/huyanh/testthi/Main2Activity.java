package com.example.huyanh.testthi;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    Database database;
    ArrayList<SinhVien> sinhVienList;
    ArrayAdapter arrayAdapter;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = (ListView) findViewById(R.id.listViewSV);
        database = new Database(this);
        sinhVienList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(sinhVienList, this, R.layout.dong_sinh_vien);
        listView.setAdapter(arrayAdapter);
        GetData();
    }
    public void GetData() {
        Cursor sv = database.GetData("SELECT * FROM sinhvien");
        sinhVienList.clear();
        while (sv.moveToNext()) {
            int a = sv.getInt(0);
            String b = sv.getString(1);
            String c = sv.getString(2);
            int d = sv.getInt(3);
            int e = sv.getInt(4);
            sinhVienList.add(new SinhVien(b, c,d,e));
        }
        arrayAdapter.notifyDataSetChanged();
    }
}
