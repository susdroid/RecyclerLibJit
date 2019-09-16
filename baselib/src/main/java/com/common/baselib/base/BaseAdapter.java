package com.common.baselib.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

/**
 * recycleView 的简单适配器
 * Created by chen on 2015/6/12.
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter{

    protected Context context;
    protected List<T> data = new ArrayList<>();
    public int itemResourceId;

    public BaseAdapter(Context context, @LayoutRes int itemResourceId, List<T> data) {
        this.context = context;
        this.itemResourceId = itemResourceId;
        if (data != null) this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemResourceId, parent, false);
        return new ItemViewHolder(view);
    }

    public void replaceItem(List<T> list) {
        data.clear();
        if (list == null) return;
        data.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        View views;

        public ItemViewHolder(View itemView) {
            super(itemView);
            views = itemView;
        }

        @SuppressWarnings("unchecked")
        public <E extends View> E getView(int resId) {
            return (E) views.findViewById(resId);
        }
    }
}
