package com.common.baselib.utils.pic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.common.baselib.R;
import com.common.baselib.utils.ValidationUtils;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


/**
 * 图片加载 工具类
 * Created by chen on 2015/11/2.
 */
public class PicUtils {


    /**
     * imageView
     *
     * @param context
     * @param uriString
     * @param imageView
     */
    public static void loadEQAvatar(@NonNull Context context, String uriString,
                                    @NonNull ImageView imageView) {
        loadImages(context, uriString, imageView);
    }

    /**
     * 圆角图片
     *
     * @param context
     * @param uriString
     * @param imageView
     */
    public static void loadRoAvatar(@NonNull Context context, String uriString,
                                    @NonNull ImageView imageView, float radius) {
        loadImageRound(context, uriString, imageView, radius);
    }

    /**
     * 圆角图片
     *
     * @param context
     * @param file
     * @param imageView
     */
    public static void loadRoAvatarFile(@NonNull Context context, File file,
                                        @NonNull ImageView imageView, float radius) {
        loadImageRoundFile(context, file, imageView, radius);
    }

    /**
     * 圆角图片顶部
     *
     * @param context
     * @param uriString
     * @param imageView
     */
    public static void loadRoAvatarTop(@NonNull Context context, String uriString,
                                       @NonNull ImageView imageView, float radius) {
        loadImageRoundTop(context, uriString, imageView, radius);
    }

    /**
     * imageView
     *
     * @param context
     * @param uriString
     * @param imageView
     */
    public static void loadDefaultAvatar(@NonNull Context context, String uriString,
                                         @NonNull ImageView imageView) {
        loadImageDefault(context, uriString, imageView);
    }

    /**
     * 加载头像图片
     *
     * @param context
     * @param uriString
     * @param imageView
     */
    public static void loadCirAvatar(@NonNull Context context, String uriString,
                                     @NonNull ImageView imageView) {
        loadImageToCir(context, uriString, imageView);
    }

    /**
     * 加载模糊图片
     *
     * @param context
     * @param uriString
     * @param imageView
     */
    public static void loadBlurAvatar(@NonNull Context context, String uriString,
                                      @NonNull ImageView imageView) {
        loadImageToBlur(context, uriString, imageView);
    }

    /**
     * 加载res资源图
     *
     * @param context
     * @param imageView
     */
    public static void loadResAvatar(@NonNull Context context, @NonNull Integer res,
                                     @NonNull ImageView imageView, float radius) {

        Glide.with(context)
                .load(res)
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(DensityUtil.dp2px(radius),
                        0, RoundedCornersTransformation.CornerType.ALL)))
                .into(imageView);
    }


    private static void loadImages(@NonNull Context context, @Nullable String uriString, ImageView view) {

        String uri = getUri(uriString);
        //路径不为空
        if (uri.isEmpty()) return;

        Glide.with(context)
                .load(uri)
                .thumbnail(0.5f)
                .into(view);
    }

    private static void loadImageRound(@NonNull Context context, @Nullable String uriString, ImageView view, float radius) {

        String uri = getUri(uriString);
        //路径不为空
        if (uri.isEmpty()) return;

        Glide.with(context)
                .load(uri)
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(DensityUtil.dp2px(radius),
                        0, RoundedCornersTransformation.CornerType.ALL)))
                .thumbnail(0.5f)
                .into(view);
    }

    private static void loadImageRoundFile(@NonNull Context context, @Nullable File file, ImageView view, float radius) {

        Glide.with(context)
                .load(file)

                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(DensityUtil.dp2px(radius),
                        0, RoundedCornersTransformation.CornerType.ALL)))
                .thumbnail(0.5f)
                .into(view);
    }

    private static void loadImageRoundTop(@NonNull Context context, @Nullable String uriString, ImageView view, float radius) {

        String uri = getUri(uriString);
        //路径不为空
        if (uri.isEmpty()) return;
        Glide.with(context)
                .load(uri)
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(DensityUtil.dp2px(radius),
                        0, RoundedCornersTransformation.CornerType.TOP)))
                .thumbnail(0.5f)
                .into(view);
    }

    private static void loadImageDefault(@NonNull Context context, @Nullable String uriString, ImageView view) {

        String uri = getUri(uriString);
        //路径不为空
        if (uri.isEmpty()) return;

        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_default_avatar)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context)
                .load(uri)
                .apply(options)
                .thumbnail(0.5f)
                .into(view);
    }

    private static void loadImageToCir(@NonNull Context context, @Nullable String uriString, ImageView view) {

        String uri = getUri(uriString);
        //路径不为空
        if (uri.isEmpty()) return;

        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_default_avatar)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

        Glide.with(context)
                .load(uri)
                .apply(options)
                .into(view);
    }


    private static void loadImageToBlur(@NonNull Context context, @Nullable String uriString, ImageView view) {

        String uri = getUri(uriString);
        //路径不为空
        if (uri.isEmpty()) return;
        Glide.with(context)
                .load(uri)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(10)))
                .into(view);
    }

    /**
     * 获取缩略图的非完整路径
     *
     * @param uri
     * @return
     */
    public static String getThumbUri(String uri) {
        if (uri == null) return null;
        int index = uri.lastIndexOf("/");
        if (index == -1) return "thumb_" + uri;
        return uri.substring(0, index + 1) + "thumb_" + uri.substring(index + 1, uri.length());
    }

    /**
     * 根据路径名称补全图片路径, 可传入空值
     *
     * @param uri
     * @return
     */
    public static String getUri(@Nullable String uri) {
        if (uri == null) return "";
        if (!ValidationUtils.isUrl(uri)) return "";

        return uri.replace("\\\\/", "\\/");
    }

    //模糊处理 已经替换为JNI实现
    public static Bitmap doBlur(Bitmap sentBitmap, int radius, boolean canReuseInBitmap) {

        // Stack Blur v1.0 from
        // http://www.quasimondo.com/StackBlurForCanvas/StackBlurDemo.html
        //
        // Java Author: Mario Klingemann <mario at quasimondo.com>
        // http://incubator.quasimondo.com
        // created Feburary 29, 2004
        // Android port : Yahel Bouaziz <yahel at kayenko.com>
        // http://www.kayenko.com
        // ported april 5th, 2012
        //
        // Stack Blur Algorithm by Mario Klingemann <mario@quasimondo.com>

        Bitmap bitmap;
        if (canReuseInBitmap) {
            bitmap = sentBitmap;
        } else {
            bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
        }

        if (radius < 1) {
            bitmap.recycle();
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

        bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        return (bitmap);
    }

    //裁剪成正方形
    public static Bitmap makeSquare(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int left = 0, top = 0, right = width, bottom = height;
        float roundPx = height / 2;
        if (width > height) {
            left = (width - height) / 2;
            top = 0;
            right = left + height;
            bottom = height;
        } else if (height > width) {
            left = 0;
            top = (height - width) / 2;
            right = width;
            bottom = top + width;
            roundPx = width / 2;
        }

        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        int color = 0xff424242;
        Paint paint = new Paint();
        Rect rect = new Rect(left, top, right, bottom);
        RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }
}
