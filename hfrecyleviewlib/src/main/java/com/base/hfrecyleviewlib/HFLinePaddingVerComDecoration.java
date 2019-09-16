package com.base.hfrecyleviewlib;

import android.graphics.Rect;
import android.view.View;

import com.base.hfrecyleviewlib.utils.DensityUtils;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Sudroid on 2019-09-11.
 */
public class HFLinePaddingVerComDecoration extends RecyclerView.ItemDecoration {

    private int dividerHeight;
    private boolean isTop;


    public HFLinePaddingVerComDecoration(int dp, boolean isTopPadding) {
        dividerHeight = DensityUtils.dp2px(dp);
        isTop = isTopPadding;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int pos = parent.getChildAdapterPosition(view);

        if (!isTop) {
            outRect.set(0, 0, 0, dividerHeight);
        } else {
            if (pos == 0) {
                outRect.set(0, dividerHeight, 0, dividerHeight);
            } else {
                outRect.set(0, 0, 0, dividerHeight);
            }
        }


    }
}
