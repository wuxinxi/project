package com.wxx.imooc.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;

import com.wxx.imooc.interfaces.OnItemClick;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: Tangren on 2017/7/4
 * 包名：com.commonlylib.adapter
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class MultiItemTypeAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<T> mDatas = new ArrayList<>();
    protected ItemViewTypeManager manager;
    private Context mContext;
    private Object o;


    private SparseBooleanArray mBooleanArray;
    private int mLastCheckedPosition = -1;
    private OnItemClick click;

    public MultiItemTypeAdapter(Context context, List<T> mDatas) {
        this.mDatas = mDatas;
        this.mContext = context;
        manager = new ItemViewTypeManager();
        mBooleanArray = new SparseBooleanArray(mDatas.size());
    }

    public MultiItemTypeAdapter(Context context) {
        this.mContext = context;
        manager = new ItemViewTypeManager();
    }

    public void setNewDatas(List<T> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public void setNewDatas(List<T> mDatas, Object o) {
        this.mDatas = mDatas;
        this.o = o;
        notifyDataSetChanged();
    }


    /**
     * 下拉刷新后,可调用,会clear之前的数据
     *
     * @param mDatas .
     */
    public void setRestDatas(List<T> mDatas) {
        this.mDatas.clear();
        this.mDatas.addAll(mDatas);
        notifyDataSetChanged();
    }

    /**
     * 加载到更多
     *
     * @param mDatas .
     */
    public void addMoreListData(List<T> mDatas) {
        this.mDatas.addAll(mDatas);
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        if (!useItemViewTypeManager()) {
            super.getItemViewType(position);
        }
        return manager.getItemViewType(mDatas.get(position), position);
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewType itemViewType = manager.getItemViewDelegate(viewType);
        int layoutID = itemViewType.getItemViewLayoutID();
        BaseViewHolder holder = BaseViewHolder.createViewHolder(mContext, parent, layoutID, click);
        onViewHolderCreated(holder, holder.getConvertView());
        return holder;
    }

    public void onViewHolderCreated(BaseViewHolder holder, View itemView) {

    }

    protected boolean isEnabled(int viewType) {
        return true;
    }

    public void convert(BaseViewHolder holder, T t) {
        manager.convert(holder, t, holder.getAdapterPosition());
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        convert(holder, mDatas.get(position));
    }

    protected boolean useItemViewTypeManager() {
        return manager.getItemViewTypeCount() > 0;
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public MultiItemTypeAdapter addItemViewDelegate(ItemViewType<T> itemViewDelegate) {
        Log.d("MultiItemTypeAdapter",
                "addItemViewDelegate(MultiItemTypeAdapter.java:124)添加");
        manager.addType(itemViewDelegate);
        return this;
    }

    public MultiItemTypeAdapter addItemViewDelegate(int viewType, ItemViewType<T> itemViewDelegate) {
        manager.addType(viewType, itemViewDelegate);
        return this;
    }

    @Override
    public int getItemCount() {
        int itemCount = mDatas.size();
        return itemCount;
    }

    public void setItemChecked(int position) {
        mBooleanArray.put(position, true);
        if (mLastCheckedPosition > -1) {
            mBooleanArray.put(mLastCheckedPosition, false);
            notifyItemChanged(mLastCheckedPosition);
        }
        notifyDataSetChanged();

        mLastCheckedPosition = position;
    }


    public void setItemClick(OnItemClick click) {
        this.click = click;
    }


}
