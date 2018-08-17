package com.example.yhadmin.recycleviewdemo.base;

import android.view.View;

/**
 * Created by YH on 2017-01-16.
 */

public interface OnItemClickListener<T> {
  void onItemClick(T t, int position, View view);
}
