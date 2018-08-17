package com.example.yhadmin.recycleviewdemo.list;

/*
 *  @项目名：  RecycleViewDemo 
 *  @包名：    com.example.yhadmin.recycleviewdemo.list
 *  @文件名:   CommonAdapter
 *  @创建者:   YHAdmin
 *  @创建时间:  2018/8/17 15:23
 *  @描述：    TODO
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class CommonAdapter<T>
        extends BaseAdapter
{
    private Context mContext;
    private List<T> mData;
    private int     itemLayoutId;

    public CommonAdapter(Context context, List<T> data, int itemLayoutId) {
        mContext = context;
        mData = data;
        this.itemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount() {
        return mData == null
               ? 0
               : mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = getViewHolder(position, convertView, parent);
        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();
    }


    public abstract void convert(ViewHolder viewHolder, T item);

    private ViewHolder getViewHolder(int position, View convertView, ViewGroup parent)
    {
        return ViewHolder.get(mContext, convertView, parent, itemLayoutId, position);
    }
}
