package com.example.sam.anotalk.atys;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sam.anotalk.R;
import com.example.sam.anotalk.net.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 2016/3/1.
 */
public class AtyTimelineListAdapter extends BaseAdapter{

    private Context context=null;
    private List<Message> data=new ArrayList<Message>();

    public Context getContext() {
        return context;
    }

    public AtyTimelineListAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.aty_timeline_list_cell,null);
            convertView.setTag(new ListCell((TextView) convertView.findViewById(R.id.tvCellLabel)));
        }
        ListCell lc= (ListCell) convertView.getTag();
        Message msg= (Message) getItem(position);
        lc.getTvCellLabel().setText(msg.getMsg());
        return convertView;
    }

    public void addAll(List<Message> data){
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void clear(){
        data.clear();
        notifyDataSetChanged();
    }

    public static class ListCell{

        public ListCell(TextView tvCellLabel){
            this.tvCellLabel=tvCellLabel;
        }

        public TextView getTvCellLabel() {
            return tvCellLabel;
        }

        private TextView tvCellLabel;

    }

}
