package com.example.sam.getphonecontact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by sam on 2016/1/18.
 */
public class MyAdapter extends BaseAdapter {

    private List<PhoneInfo> list;
    private Context context;
    private LinearLayout layout;

    public MyAdapter(List<PhoneInfo> list,Context context){
        this.list=list;
        this.context=context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater inflater=LayoutInflater.from(context);
//        layout= (LinearLayout) inflater.inflate(R.layout.contact_cell,null);
//        TextView nameTv= (TextView) layout.findViewById(R.id.name);
//        TextView numberTv= (TextView) layout.findViewById(R.id.number);
//        nameTv.setText(list.get(position).getName());
//        numberTv.setText(list.get(position).getNumber());

        //listview的优化 防止加载列表时卡顿
        ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.contact_cell,null);
            holder=new ViewHolder();
            holder.nametv= (TextView) convertView.findViewById(R.id.name);
            holder.numbertv= (TextView) convertView.findViewById(R.id.number);
            holder.nametv.setText(list.get(position).getName());
            holder.numbertv.setText(list.get(position).getNumber());
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
            holder.nametv.setText(list.get(position).getName());
            holder.numbertv.setText(list.get(position).getNumber());
        }
        return layout;
    }
    private static class ViewHolder{
        TextView nametv;
        TextView numbertv;
    }
}
