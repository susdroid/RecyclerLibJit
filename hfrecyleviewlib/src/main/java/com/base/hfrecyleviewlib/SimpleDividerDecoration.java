package com.base.hfrecyleviewlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Sudroid on 2019-09-11.
 */

public class SimpleDividerDecoration extends RecyclerView.ItemDecoration {

    private int dividerHeight;
    private Paint dividerPaint;

    public SimpleDividerDecoration(Context context,int color,int px) {
        dividerPaint = new Paint();
        dividerPaint.setColor(color);
        dividerHeight = context.getResources().getDimensionPixelSize(px);
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = dividerHeight;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + dividerHeight;
            c.drawRect(left, top, right, bottom, dividerPaint);
        }
    }
}