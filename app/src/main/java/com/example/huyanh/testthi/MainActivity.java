package com.example.huyanh.testthi;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Database database;
    ArrayList<SinhVien> sinhVienList;
    ArrayAdapter arrayAdapter;
    ListView listView;
    EditText masv, diemtoan , malop , diemvan;
    EditText PT , PH , TOAN , VAN;
    Button btnThem,btnFrag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnThem = (Button) findViewById(R.id.btnThem);
        masv = (EditText) findViewById(R.id.maSV);
        malop = (EditText)findViewById(R.id.maLop) ;
        diemtoan = (EditText)findViewById(R.id.diemToan) ;
        diemvan = (EditText)findViewById(R.id.diemVan) ;
        database = new Database(this);
        sinhVienList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(sinhVienList, this, R.layout.dong_sinh_vien);

        database.QueryData("DELETE FROM SINHVIEN");
        btnFrag = (Button)findViewById(R.id.fragment2);
        btnFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma = masv.getText().toString().trim();
                String lop = malop.getText().toString().trim();
                String toan = diemtoan.getText().toString().trim();
                String van = diemvan.getText().toString().trim();

                database.QueryData("INSERT INTO SINHVIEN values(null,'" + ma + "','" + lop + "','" + toan + "','" + van + "')");
                GetData();

                PT = (EditText) findViewById(R.id.diem_PT);
                PH = (EditText)findViewById(R.id.diem_PH);
                TOAN = (EditText)findViewById(R.id.diem_Toan);
                VAN = (EditText)findViewById(R.id.diem_Van);


                Double pt , ph , dtbtoan , dtbvan,  dtoan ,dvan;
                dtoan = Double.parseDouble(toan);
                dvan  = Double.parseDouble(van);
                dtbtoan = (dtoan+dvan)/2;
                dtbvan = (dtoan + dvan + dvan)/3;
                pt = (dtoan + dvan + dvan + dtoan)/4;
                ph = (dtoan + dvan + dvan + dtoan + dvan)/5;
                TOAN.setText(String.valueOf(dtbtoan));
                VAN.setText(String.valueOf(dtbvan));
                PT.setText(String.valueOf(pt));
                PH.setText(String.valueOf(ph));
            }
        });

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
