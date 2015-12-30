package com.osblog.loadmoreview.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.osblog.loadmoreview.R;

import java.util.ArrayList;
import java.util.List;


public class DataAdapter extends BaseAdapter {
    private Activity mActivity;
    private List<String> dataList;

    public DataAdapter(Activity mActivity) {
        this.mActivity = mActivity;
        this.dataList = new ArrayList<>();
    }

    public void addData(List<String> list){
        dataList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(mActivity).inflate(R.layout.item_showname_layout,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.nameTextView.setText(dataList.get(position));
        return convertView;
    }

    static class ViewHolder{
        TextView nameTextView;

        public ViewHolder(View view) {
            nameTextView = (TextView) view.findViewById(R.id.name_textview);
        }
    }
}
