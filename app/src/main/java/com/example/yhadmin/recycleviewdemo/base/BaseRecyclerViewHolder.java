package com.example.yhadmin.recycleviewdemo.base;

/*
 *  @项目名：  Gank 
 *  @包名：    com.example.yhadmin.gank.adapter
 *  @文件名:   BaseRecyclerViewHolder
 *  @创建者:   YHAdmin
 *  @创建时间:  2017/11/9 18:35
 *  @描述：    TODO
 */

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
//T 数据类型 D 条目布局自动生成的 binding 类
public abstract class BaseRecyclerViewHolder <T, D extends ViewDataBinding> extends RecyclerView.ViewHolder {

    public D binding;

    public BaseRecyclerViewHolder(ViewGroup viewGroup, @LayoutRes int layoutId) {
        super(DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), layoutId, viewGroup, false).getRoot());
        binding = DataBindingUtil.getBinding(itemView);//在RecyclerView 的源码中，创建 ViewHolder 时被赋值 在 代码 findContainingItemView中
    }

    public void onBaseBindViewHolder(T t,  int position) {
        onBindViewHolder(t, position);
        binding.executePendingBindings();//解决数据闪烁问题
    }
    //数据和控件绑定
    protected abstract void onBindViewHolder(T t, int position);
}