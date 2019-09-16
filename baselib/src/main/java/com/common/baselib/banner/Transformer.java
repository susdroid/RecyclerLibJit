package com.common.baselib.banner;


import com.common.baselib.banner.transformer.AccordionTransformer;
import com.common.baselib.banner.transformer.BackgroundToForegroundTransformer;
import com.common.baselib.banner.transformer.CubeInTransformer;
import com.common.baselib.banner.transformer.CubeOutTransformer;
import com.common.baselib.banner.transformer.DefaultTransformer;
import com.common.baselib.banner.transformer.DepthPageTransformer;
import com.common.baselib.banner.transformer.FlipHorizontalTransformer;
import com.common.baselib.banner.transformer.FlipVerticalTransformer;
import com.common.baselib.banner.transformer.ForegroundToBackgroundTransformer;
import com.common.baselib.banner.transformer.RotateDownTransformer;
import com.common.baselib.banner.transformer.RotateUpTransformer;
import com.common.baselib.banner.transformer.ScaleInOutTransformer;
import com.common.baselib.banner.transformer.StackTransformer;
import com.common.baselib.banner.transformer.TabletTransformer;
import com.common.baselib.banner.transformer.ZoomInTransformer;
import com.common.baselib.banner.transformer.ZoomOutSlideTransformer;
import com.common.baselib.banner.transformer.ZoomOutTranformer;

import androidx.viewpager.widget.ViewPager;

public class Transformer {
    public static Class<? extends ViewPager.PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends ViewPager.PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
