package com.example.huyanh.testthi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



import java.util.List;

public class ArrayAdapter extends BaseAdapter {
    private List<SinhVien> sinhVienList;
    private Context context;
    private int layout;

    public ArrayAdapter(List<SinhVien> sinhVienList, Context context, int layout) {
        this.sinhVienList = sinhVienList;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return sinhVienList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder {
        TextView masv, malop , diemtoan , diemvan;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ArrayAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            viewHolder.masv = (TextView)view.findViewById(R.id.ma_SV);
            viewHolder.malop = (TextView)view.findViewById(R.id.ma_Lop) ;
            viewHolder.diemtoan = (TextView)view.findViewById(R.id.diem_Toan);
            viewHolder.diemvan = (TextView)view.findViewById(R.id.diem_Van);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)view.getTag();
        }

        SinhVien sv = sinhVienList.get(i);
        viewHolder.diemtoan.setText(String.valueOf(sv.getDiemtoan()));
        viewHolder.diemvan.setText(String.valueOf(sv.getDiemvan()));
        viewHolder.masv.setText(sv.getMasv());
        viewHolder.malop.setText(sv.getMalop());
        return view;
    }
}
