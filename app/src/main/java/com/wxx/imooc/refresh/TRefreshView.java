package com.wxx.imooc.refresh;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wxx.imooc.R;


/**
 * 作者: Tangren on 2017-11-02
 * 包名：com.wxx.view.refresh
 * 邮箱：996489865@qq.com
 * TODO:自定义RecyclerView+下拉刷新+上拉加载更多
 */

public class TRefreshView extends FrameLayout implements SwipeRefreshLayout.OnRefreshListener {
    //空数据view
    private View emptyView;
    //空数据提示
    private TextView emptyTip;
    //网络错误view
    private View errorView;
    private Button retryButton;

    private FrameLayout blankView;

    private BaseFootView baseFootView;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView.LayoutManager manager;

    public OnLoadListener listener;

    /**
     * 是否展示emptyView
     */
    private boolean isEmpty = false;
    /**
     * 是否正在加载更多
     */
    private boolean isLoadMore = false;
    /**
     * 是否可以上拉加载
     */
    private boolean isLoadEnable = true;
    /**
     * 是否可以下拉刷新
     */
    private boolean isRefreshEnable = true;

    private int lastPosition = 0;
    private boolean isScrollEnd = false;

    private Context context;


    private DataObserver mDataObserver;
    private WraperAdapter mWrapperAdapter;

    public TRefreshView(@NonNull Context context) {
        this(context, null);
    }

    public TRefreshView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TRefreshView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initTRView();
    }

    private void initTRView() {
        baseFootView = new SimpleFootView(getContext());
        baseFootView.setRefreshView(this);

        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.view_refresh_view, this);
        blankView = (FrameLayout) rootView.findViewById(R.id.blank_view);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
        refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);


        LayoutParams params = new LayoutParams(-1, -1);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        blankView.setLayoutParams(params);

        //默认颜色黑色
        refreshLayout.setColorSchemeColors(Color.parseColor("#000000"));
        refreshLayout.setOnRefreshListener(this);

        //默认布局线性布局
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!isLoadEnable || isRefreshing() || isLoadMore) {
                    return;
                }
                manager = recyclerView.getLayoutManager();
                if (manager instanceof LinearLayoutManager) {
                    lastPosition = ((LinearLayoutManager) manager).findLastVisibleItemPosition();
                }

                int childCount = mWrapperAdapter == null ? 0 : mWrapperAdapter.getItemCount();
                if (!isRefreshing() && childCount > 1 && lastPosition == childCount - 1 && dy > 0) {
                    //滑动的时候才显示footview
                    baseFootView.onLoadMore();
                    if (listener != null) {
                        isLoadMore = true;
                        listener.onLoadMore();
                    }
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        if (listener != null) {
            isLoadMore = false;
            listener.onRefresh();
        }

    }


    /**
     * 下拉刷新的颜色
     *
     * @param colors .
     */
    public void setColorSchemeColors(@ColorInt int... colors) {
        refreshLayout.setColorSchemeColors(colors);
    }

    /**
     * 下拉刷新的颜色
     *
     * @param colorResIds .
     */
    public void setColorSchemeResources(@ColorRes int... colorResIds) {
        refreshLayout.setColorSchemeResources(colorResIds);
    }

    /**
     * 禁止下拉刷新
     *
     * @param refreshEnable .
     */
    public void setRefreshEnable(boolean refreshEnable) {
        isRefreshEnable = refreshEnable;
        refreshLayout.setEnabled(isRefreshEnable);
    }

    /**
     * 是否支持下拉刷新
     *
     * @return .
     */
    public boolean getRefreshEnable() {
        return isRefreshEnable;
    }

    /**
     * 设置是否加载更多
     *
     * @param loadMoreEnable .
     */
    public void setLoadMoreEnable(boolean loadMoreEnable) {
        if (!loadMoreEnable) {
            stopLoadingMore();
        }
        isLoadEnable = loadMoreEnable;
    }

    /**
     * 是否支持加载更多
     *
     * @return
     */
    public boolean getLoadMoreEnable() {
        return isLoadEnable;
    }

    /**
     * 停止加载更多
     */
    private void stopLoadingMore() {
        isLoadMore = false;
        if (mWrapperAdapter != null) {
            baseFootView.onHideLoad();
            mWrapperAdapter.notifyItemRemoved(mWrapperAdapter.getItemCount());
        }

    }

    /**
     * 是否正在下拉刷新
     *
     * @return .
     */
    public boolean isRefreshing() {
        return refreshLayout.isRefreshing();
    }


    /**
     * 是否加载更多中
     *
     * @return .
     */
    public boolean isLoadingMore() {
        return isLoadMore;
    }


    /**
     * 空视图是否显示中
     *
     * @return .
     */
    public boolean isEmptyViewShowing() {
        return isEmpty;
    }


    /**
     * 获取SwipeRefreshLayout 可以修改起属性
     *
     * @return .
     */
    public SwipeRefreshLayout getRefreshLayout() {
        return refreshLayout;
    }

    /**
     * 获取RecyclerView 可以修改属性
     *
     * @return .
     */
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setOnLoadListener(OnLoadListener listener) {
        this.listener = listener;
    }

    /**
     * 设置footView
     *
     * @param footView .
     */
    public void setFootView(BaseFootView footView) {
        if (footView != null)
            this.baseFootView = footView;
    }

    /**
     * 无数据的时候设置空视图
     *
     * @param tip 提示
     */
    public void setEmptyView(String tip) {
        if (blankView.getChildCount() > 0) {
            blankView.removeAllViews();
        }
        if (emptyView == null) {
            emptyView = LayoutInflater.from(getContext()).inflate(R.layout.view_empty, null);
            emptyTip = (TextView) emptyView.findViewById(R.id.empty_tip);
        }
        blankView.addView(emptyView);
        emptyTip.setText(tip);
        if (mDataObserver != null) {
            mDataObserver.onChanged();
        }
    }


    /**
     * 设置默认网络错误提示视图
     */
    public void setErrorView() {

        if (blankView.getChildCount() > 0) {
            blankView.removeAllViews();
        }
        if (errorView == null) {
            errorView = LayoutInflater.from(getContext()).inflate(R.layout.view_error, null);
            retryButton = (Button) errorView.findViewById(R.id.retry);
        }
        retryButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setRefreshing(true);
            }
        });
        blankView.addView(errorView);
        if (mDataObserver != null) {
            mDataObserver.onChanged();
        }
    }

    /**
     * 首次加载
     *
     * @param refreshing .
     */
    public void setRefreshing(boolean refreshing) {
        refreshLayout.setRefreshing(refreshing);
        if (refreshing && !isLoadMore && listener != null) {
            listener.onRefresh();
        }
    }


    public void setCreateView(View newView) {
        if (blankView.getChildCount() > 0) {
            blankView.removeAllViews();
        }
        blankView.addView(newView);
        if (mDataObserver != null) {
            mDataObserver.onChanged();
        }
    }

    /**
     * footView交给WraperAdapter显示
     *
     * @param adapter .
     */
    public void setAdapter(RecyclerView.Adapter adapter) {
        if (adapter != null) {
            if (mDataObserver == null) {
                mDataObserver = new DataObserver();
            }
            mWrapperAdapter = new WraperAdapter(adapter);
            recyclerView.setAdapter(mWrapperAdapter);
            adapter.registerAdapterDataObserver(mDataObserver);
            mDataObserver.onChanged();
        }

    }


    public void setSourceAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    /**
     * 下拉或上拉完成
     */
    public void complete() {
        refreshLayout.setRefreshing(false);
        stopLoadingMore();
    }


    //恢复footView
    public void onLoadingMore() {
        if (baseFootView != null) {
            baseFootView.onLoadMore();
        }
    }

    //无更多数据
    public void noNoMore() {
        if (baseFootView != null) {
            isLoadMore = true;
            baseFootView.onNoMore();
        }
    }

    //网络错误
    // 注意:page要自行-1
    public void onError() {
        if (baseFootView != null) {
            baseFootView.onError();
        }
    }

    public interface OnLoadListener {
        /**
         * 刷新
         */
        void onRefresh();

        /**
         * 加载更多
         */
        void onLoadMore();
    }

    /*---------------------------------------------------------------------------------------------*/

    /**
     * 监控数据的变化
     */
    private class DataObserver extends RecyclerView.AdapterDataObserver {
        @Override
        public void onChanged() {
            super.onChanged();
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (adapter != null) {
                if (adapter.getItemCount() == 0) {
                    //如果没有数据
                    isEmpty = true;
                    recyclerView.setVisibility(View.GONE);
                    blankView.setVisibility(View.VISIBLE);
                } else {
                    //有数据时
                    isEmpty = false;
                    recyclerView.setVisibility(View.VISIBLE);
                    blankView.setVisibility(View.GONE);
                }
            }
        }
    }

    /**
     * 控制footView显示
     */
    private class WraperAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        static final int TYPE_FOOT = 0x01;
        static final int TYPE_HEADER = 0x02;

        private RecyclerView.Adapter mAdapter;

        WraperAdapter(RecyclerView.Adapter mAdapter) {
            this.mAdapter = mAdapter;
        }

        /**
         * 是否是footView
         *
         * @param position .
         * @return .
         */
        boolean isLoadMoreItem(int position) {
            return isLoadEnable && position == getItemCount() - 1;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //判断是否是footView来显示holder样式
            if (viewType == TYPE_FOOT) {
                return new FootViewHolder(baseFootView);
            }
            return mAdapter.onCreateViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (isLoadMoreItem(position)) {
                return;
            }
            mAdapter.onBindViewHolder(holder, position);
        }

        @Override
        public int getItemViewType(int position) {
            if (isLoadMoreItem(position)) {
                return TYPE_FOOT;
            }

            return mAdapter.getItemViewType(position);
        }

        @Override
        public int getItemCount() {
            int count = mAdapter == null ? 0 : mAdapter.getItemCount();
            if (count == 0) {
                return 0;
            }
            //如果有footView,总个数+1
            return isLoadEnable ? count + 1 : count;
        }

        @Override
        public long getItemId(int position) {
            return mAdapter.getItemId(position);
        }

        @Override
        public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
            mAdapter.registerAdapterDataObserver(observer);
        }

        @Override
        public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
            mAdapter.unregisterAdapterDataObserver(observer);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (mWrapperAdapter != null) {
            mWrapperAdapter.unregisterAdapterDataObserver(mDataObserver);
        }
        super.onDetachedFromWindow();

    }

    private class FootViewHolder extends RecyclerView.ViewHolder {

        FootViewHolder(View itemView) {
            super(itemView);
        }
    }


    /*---------------------------------------------------------------------------------------------*/
}
