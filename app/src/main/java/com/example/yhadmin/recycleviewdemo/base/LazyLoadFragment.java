package com.example.yhadmin.recycleviewdemo.base;

/*
 *  @项目名：  Ahbottomnavigation 
 *  @包名：    com.chuxiong.ahbottomnavigation
 *  @文件名:   LazyLoadFragment
 *  @创建者:   YHDN
 *  @创建时间:  2017-08-28 18:31
 *  @描述：    TODO
 */

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.components.support.RxFragment;

public abstract class LazyLoadFragment<SV extends ViewDataBinding>
        extends RxFragment
{
    public boolean isInit = false;//视图是否已经初始化
    public boolean isLoad = false;//视图是否已经加载过
    protected SV   bindingFragmentView;
    private   View view;
    private boolean isUIVisible;

    //    public final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject = PublishSubject.create();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(setContentView(), container, false);
        bindingFragmentView = DataBindingUtil.bind(view);
        isInit = true;
        /**初始化的时候去加载数据**/
        init();
        isCanLoadData();
        Logger.t("LazyLoadFragment").d(getClass().getSimpleName());
        return view;
    }

    /**
     * 初始化
     */
    protected abstract void init();


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见

//        if (isVisibleToUser) {
        //            isUIVisible = true;
        //            lazyLoad();
        //        } else {
        //            isUIVisible = false;
        //        }
        isCanLoadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isInit = false;
        isLoad = false;
    }

    /**
     *  设置fragment视图
     * @return 布局的layoutId
     */
    public abstract int setContentView();

    /**
     * 获取设置的布局
     *
     * @return
     */
    protected View getContentView() {
        return view;
    }

    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     * 1.视图已经初始化
     * 2.视图对用户可见
     */
    private void isCanLoadData() {
        if (!isInit) {
            return;
        }

        if (getUserVisibleHint()) {
            lazyLoad();
            isLoad = true;
            //数据加载完毕,恢复标记,防止重复加载
            isInit = false;
        } else {
            if (isLoad) {
                stopLoad();
//                isInit = false;
            }
        }
    }
/*
    *//**
     * 找出对应的控件
     *
     * @param id
     * @param <T>
     * @return
     *//*
    protected <T extends View> T findViewById(int id) {

        return (T) getContentView().findViewById(id);
    }*/

    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    protected abstract void lazyLoad();

    /**
     * 当视图已经对用户不可见并且加载过数据，如果需要在切换到其他页面时停止加载数据，可以覆写此方法
     */
    protected void stopLoad() {
    }
}
