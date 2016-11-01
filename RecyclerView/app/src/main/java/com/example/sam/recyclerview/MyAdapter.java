package com.example.sam.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by sam on 2016/1/19.
 */
class MyAdapter extends RecyclerView.Adapter {

    private CellData[] data=new CellData[]{new CellData("sam","123"),new CellData("lisa","321")};

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView root;
        private TextView tvTitle,tvContent;
        public ViewHolder(View root) {
            super(root);
            tvTitle= (TextView) root.findViewById(R.id.tvTitle);
            tvContent= (TextView) root.findViewById(R.id.tvContent);
        }
        public TextView getTvTitle(){
            return tvTitle;
        }
        public TextView getTvContent(){
            return tvContent;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        CellData cd=data[position];
        vh.getTvTitle().setText(cd.title);
        vh.getTvContent().setText(cd.content);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
}
