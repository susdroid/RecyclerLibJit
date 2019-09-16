package com.common.baselib.base;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.common.baselib.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.IntDef;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

/**
 * RecycleView 分页适配器基类
 * Created by chen on 2015/5/26.
 */
public abstract class BasePageListAdapter<T> extends RecyclerView.Adapter {
    /**
     * item类型
     */
    public static final int ITEM = 1;
    public static final int FOOTER = 2;

    @IntDef({NULL, LOADING, LOADMORE, NOMORE, CUSTOM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LoadState {
    }

    /**
     * remove the footer of RecycleView
     */
    public static final int NULL = 0;

    /**
     * show the footer is loading
     */
    public static final int LOADING = 1;

    /**
     * show the footer is loading more
     */
    public static final int LOADMORE = 2;

    /**
     * show there is no more data
     */
    public static final int NOMORE = 3;

    /**
     * show custom search tip
     */
    public static final int CUSTOM = 4;


    protected Context context;
    protected List<T> data = new ArrayList<>();
    public int itemResourceId;
    private boolean ready = false;//is ready to call load next listener
    private int pageSize = 10;
    private String search = "";
    protected int loadingState = NULL;

    private OnItemClickListener onItemClickListener;


    protected BasePageListAdapter(Context context, List<T> data) {
        this(context, 0, data);
    }

    public BasePageListAdapter(Context context, @LayoutRes int itemResourceId, List<T> data) {
        this.context = context;
        this.itemResourceId = itemResourceId;
        if (data != null) this.data = data;
        setItemResourceId();
    }


    /**
     * if use the constructor with param of the resource iD of item layout, you can ignore this method,
     * else the resource AGENCY_ID of item layout should be assigned to itemResourceId ;
     *
     * @see #itemResourceId
     */
    public abstract void setItemResourceId();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM:
                View view = LayoutInflater.from(parent.getContext()).inflate(itemResourceId, parent, false);
                return new ItemViewHolder(view);
            case FOOTER:
                View footer = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load_footer, parent, false);
                return new FooterViewHolder(footer);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (position == getItemCount() - 1 && loadingState != NULL) {
            @SuppressWarnings("unchecked")
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            TextView tip = (TextView) footerViewHolder.views.findViewById(R.id.pull_to_load_footer_hint_textview);
            ProgressBar progressBar = (ProgressBar) footerViewHolder.views.findViewById(R.id.pull_to_load_footer_progressbar);
            switch (loadingState) {
                case LOADING:
                    tip.setVisibility(View.VISIBLE);
                    tip.setText("拼命加载中...");
                    progressBar.setVisibility(View.VISIBLE);
                    break;
                case LOADMORE:
                    tip.setVisibility(View.VISIBLE);
                    tip.setText("加载更多...");
                    progressBar.setVisibility(View.VISIBLE);
                    break;
                case NOMORE:
                    tip.setVisibility(View.VISIBLE);
                    tip.setText("只有这些了...");
                    progressBar.setVisibility(View.GONE);
                    break;
                case CUSTOM:
                    tip.setVisibility(View.VISIBLE);
                    if (search.length() == 0) {
                        tip.setText("搜索中...");
                    } else {
                        String tipStr = "正在搜索" + "\"<font color=\"#FF369E\">" + search + "</font>\"";
                        tip.setText(Html.fromHtml(tipStr));
                    }
                    progressBar.setVisibility(View.VISIBLE);
                    break;
            }
        } else if (position < data.size()) {
            @SuppressWarnings("unchecked")
            final ItemViewHolder viewHolder = (ItemViewHolder) holder;
            if (onItemClickListener != null) {
                viewHolder.views.setOnClickListener(v -> onItemClickListener.onItemClick(viewHolder.views, position));
            }
            distributeData(viewHolder, data.get(position));
        }
    }

    public abstract void distributeData(ItemViewHolder viewHolder, T data);

    /**
     * this BaseRecycleAdapter include two type of view-- normal item and footer;
     *
     * @param position get item type with the position
     * @return type
     */
    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1 && loadingState != NULL) return FOOTER;
        else return ITEM;
    }


    @Override
    public int getItemCount() {
        if (data.size() == 0 && loadingState != NULL) return 0;
        return data.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public View views;

        public ItemViewHolder(View itemView) {
            super(itemView);
            views = itemView;
        }

        @SuppressWarnings("unchecked")
        public <E extends View> E getView(int resId) {
            return (E) views.findViewById(resId);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        View views;

        public FooterViewHolder(View itemView) {
            super(itemView);
            views = itemView.findViewById(R.id.pull_to_load_footer_content);
        }
    }

    public void addItems(List<T> data) {
        if (data == null) return;
        if (data.size() == pageSize) setReadyToLoadNext(true);
        this.data.addAll(data);
        notifyItemRangeChanged(this.data.size() - data.size(), this.data.size());
    }

    public void replaceItems(List<T> data) {
        this.data.clear();
        if (data != null) {
            if (data.size() > 0) this.data.addAll(data);
            if (data.size() == pageSize) setReadyToLoadNext(true);
        }
        notifyDataSetChanged();
    }

    public void addAllItems(List<T> data) {
        this.data.clear();
        if (data != null) {
            if (data.size() > 0) this.data.addAll(data);
            if (data.size() == pageSize) setReadyToLoadNext(true);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        replaceItems(new ArrayList<T>());
    }

    public int getLoadingState() {
        return loadingState;
    }

    public void setLoadingState(@LoadState int state) {
        loadingState = state;
        if (state == NULL) {
            notifyItemRemoved(getItemCount() - 1);
        } else {
            notifyDataSetChanged();
        }
    }

    public void setCustomFooter(String tip) {
        search = tip;
        setLoadingState(CUSTOM);
    }

    /**
     * check if RecyclerView is ready to callback the method-- onLoadNext,
     * you should call this method before you want to callback
     *
     * @param ready is the RecycleView ready to call method onLoadNext()
     */
    public void setReadyToLoadNext(boolean ready) {
        this.ready = ready;
    }

    public boolean isReadyToLoadNext() {
        return ready;
    }

    public List<T> getData() {
        return data;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * default page size is 10,
     *
     * @param pageSize if data size is equal page size,RecycleView is ready to load next.
     * @see #pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
