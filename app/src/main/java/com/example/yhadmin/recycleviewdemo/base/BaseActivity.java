package com.example.yhadmin.recycleviewdemo.base;

/*
 *  @项目名：  Gank 
 *  @包名：    com.example.yhadmin.gank.base
 *  @文件名:   BaseActivity
 *  @创建者:   YHAdmin
 *  @创建时间:  2017/11/6 10:47
 *  @描述：    TODO
 */

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.Window;


import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

public abstract class BaseActivity<SV extends ViewDataBinding>
        extends RxAppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);

//        ActivityCollector.addActivity(this);//activity 管理集合
        bindView();
//        StatusBarCompat.compat(this);//4.4以上沉浸式适配

        init();
        Logger.t("BaseActivity")
              .d(getClass().getSimpleName());
    }

    protected SV bindingView;

    //实例化BindingView
    protected void bindView() {
        bindingView = DataBindingUtil.setContentView(this, getLayoutResId());
    }

    /**
     *  初始化布局
     * @return
     */
    protected abstract int getLayoutResId();

    /**
     * 初始化操作和数据操作
     */
    public abstract void init();


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ActivityCollector.removeActivity(this);
    }

    // 连续按两次退出
    private              long lastBackKeyDownTick      = 0;
    private static final long MAX_DOUBLE_BACK_DURATION = 1800;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
   /*     if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - lastBackKeyDownTick) > MAX_DOUBLE_BACK_DURATION) {
                ToastHelper.show(getApplicationContext(), "再按一次退出程序");
//                new CustomToast.Buider(getContext()).setText(getString(R.string.exit))
//                                                    .setDrawable(getResources().getDrawable(R.drawable.toast_normal))
//                                                    .show();
                lastBackKeyDownTick = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }*/
        return super.onKeyDown(keyCode, event);
    }
}
