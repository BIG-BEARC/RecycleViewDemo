package com.example.yhadmin.recycleviewdemo.base;

import android.view.View;

/**
 * Created by YH on 2017-01-16.
 */

public interface OnItemLongClickListener<T>{
  boolean onItemLongClick(T t, int position, View view);
}
