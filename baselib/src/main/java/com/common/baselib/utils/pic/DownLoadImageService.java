package com.common.baselib.utils.pic;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.common.baselib.utils.AndroidUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图片异步下载服务
 * Created by Sudroid on 2017/9/1.
 */

public class DownLoadImageService implements Runnable {
    private String url;
    private Context context;
    private File currentFile;
    private Handler mHandler;
    private static final int SUCCESS = 0;
    private static final int FAIL = 1;

    public DownLoadImageService(Context context, String url) {
        this.url = url;
        this.context = context;
        mHandler = new MyHandler();
    }

    @Override
    public void run() {
        Bitmap bitmap = null;
        try {
            bitmap = Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();

            if (bitmap != null) {
                // 在这里执行图片保存方法
                saveImageToGallery(context, bitmap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bitmap != null && currentFile.exists()) {
                updateByMessage(SUCCESS);
            } else {
                updateByMessage(FAIL);
            }
        }
    }

    private void updateByMessage(int code) {
        Message msg = mHandler.obtainMessage(code);
        mHandler.sendMessage(msg);
    }

    private class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS://在收到消息时，对界面进行更新
                    AndroidUtils.ToastMiddle(context, "图片保存至-银程图片-文件夹内");
                    break;
                case FAIL:
                    AndroidUtils.ToastMiddle(context, "图片保存失败！");
                    break;
                default:
                    break;
            }
        }
    }

    public void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsoluteFile();//注意小米手机必须这样获得public绝对路径
        String fileName = "银程图片";
        File appDir = new File(file, fileName);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        String fileNames = System.currentTimeMillis() + ".jpg";
        currentFile = new File(appDir, fileNames);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(currentFile);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    currentFile.getAbsolutePath(), fileNames, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.fromFile(new File(currentFile.getPath()))));
    }
}