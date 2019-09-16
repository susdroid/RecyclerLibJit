package com.base.hfrecyleviewlib;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.base.hfrecyleviewlib.utils.DensityUtils;

/**
 * Created by Sudroid on 2019-09-11.
 */
public class HFLinePaddingVerDecoration extends RecyclerView.ItemDecoration {

    private int dividerHeight;


    public HFLinePaddingVerDecoration(int dp) {
        dividerHeight = DensityUtils.dp2px(dp);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int pos = parent.getChildAdapterPosition(view);

        if (pos == 0) {
            outRect.set(0, dividerHeight, 0, dividerHeight);
        } else {
            outRect.set(0, 0, 0, dividerHeight);
        }


    }
}
