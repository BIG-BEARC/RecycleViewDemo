package com.example.yhadmin.recycleviewdemo.base;

/*
 *  @项目名：  Gank 
 *  @包名：    com.example.yhadmin.gank.adapter
 *  @文件名:   BaseRecyclerViewAdapter
 *  @创建者:   YHAdmin
 *  @创建时间:  2017/11/9 18:20
 *  @描述：    TODO
 */

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

// 抽象方法，让子类必须实现 onCreateViewHolder 方法
public abstract class BaseRecyclerViewAdapter<T>
        extends RecyclerView.Adapter<BaseRecyclerViewHolder>
{

    private List<T> datas     = new ArrayList<>();//数据源，数据类型未知
    private boolean isLoading = false;//是否正在加载标志位
    private View mHeadView;//头
    //RecycleView 头、普通、加载更多三种布局
    private final static int TYPE_HEADVIEW = 100;
    private final static int TYPE_ITEM     = 101;
    private final static int TYPE_PROGRESS = 102;

    private OnItemClickListener<T>     onItemClickListener;
    private OnItemLongClickListener<T> onItemLongClickListener;
    private OnLoadMoreListener         mOnLoadMoreListener;
    private TextView                   mLoadingState;
    private ProgressBar                mPb;

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        return null;
    }
    //加载更多 holder

    //创建普通条目ViewHolder
    protected abstract BaseRecyclerViewHolder createNomalViewHolder(ViewGroup parent);

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, final int position) {
        holder.onBaseBindViewHolder(datas.get(position), position);
        // 如果设置了回调，则设置点击事件
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(datas.get(position), position, v);
                }
            });
        }
        if (onItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return onItemLongClickListener.onItemLongClick(datas.get(position),
                                                                   position,
                                                                   v);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
      /*  if (mHeadView!=null){
            if (position==getItemCount()-1){
                return TYPE_PROGRESS;
            }else if (position==0){
                return TYPE_HEADVIEW;
            }else {
                return TYPE_ITEM;
            }
        }else {*/
        if (position == getItemCount() - 1) {
            return TYPE_PROGRESS;
        } else {
            return TYPE_ITEM;
        }
        //        }
    }

    public void add(T t) {
        this.datas.add(t);
    }

    public void addFirst(T t) {
        this.datas.add(0, t);
    }

    public void addAll(List<T> datas) {
        this.datas.addAll(datas);
    }

    public void remove(int position) {
        this.datas.remove(position);
    }

    public void clear() {
        this.datas.clear();
    }

    public int getDataSize() {
        return this.datas == null
               ? 0
               : this.datas.size();
    }

    public List<T> getDatas() {
        return datas;
    }

    /*   //添加头布局
       public void addHeadView(View headView){
           mHeadView=headView;
       }*/
    public void setLoading(boolean b) {
        isLoading = b;
    }

    public void loadMoreFail() {
        if (mLoadingState != null && mPb != null) {
            mPb.setVisibility(View.GONE);
            mLoadingState.setText("加载失败");
        }
    }

    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        this.onItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<T> listener) {
        this.onItemLongClickListener = listener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener, RecyclerView recyclerView) {

        //mRecyclerView添加滑动事件监听
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager     = (LinearLayoutManager) recyclerView.getLayoutManager();
                int                 totalItemCount          = linearLayoutManager.getItemCount();
                int                 lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && dy > 0 && lastVisibleItemPosition >= totalItemCount - 1) {
                    //此时是刷新状态
                    if (mOnLoadMoreListener != null) {
                        mOnLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
        this.mOnLoadMoreListener = listener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }


}
