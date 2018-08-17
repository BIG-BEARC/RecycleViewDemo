package com.example.yhadmin.recycleviewdemo.base;


import com.example.yhadmin.recycleviewdemo.list.ViewHolder;

/**
 * Created by zhy on 16/6/22.
 */
public interface ItemViewDelegate<T> {

      int getItemViewLayoutId();
      //是否是同一种类型的 view
      boolean isForViewType(T item, int position);

      void convert(ViewHolder holder, T t, int position);


}
