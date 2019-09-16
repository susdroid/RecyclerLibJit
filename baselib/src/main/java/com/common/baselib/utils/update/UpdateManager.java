package com.common.baselib.utils.update;

import android.accounts.NetworkErrorException;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.common.baselib.BaseApplication;
import com.common.baselib.R;
import com.common.baselib.constants.ProjectConfig;
import com.common.baselib.utils.AndroidUtils;
import com.common.baselib.utils.Utility;
import com.common.baselib.utils.ValidationUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;

/**
 * 新版本更新提示
 * Created by Sudroid on 17/8/28.
 */
public class UpdateManager {

    private static final String TAG = UpdateManager.class.getSimpleName();

    public static final int WHAT = 1;
    private static final int DOWNLOAD = 2;//下载
    private static final int DOWNLOAD_FINISH = 3;//下载完成
    private String mSavePath;//保存路径
    private int progress = 0;//进度值
    private ProgressBar mProgress;//进度条
    private TextView mTeProgress;//进度条
    private boolean cancelUpdate = false;//取消更新

    private String updateMessage;

    private Context mContext;
    private boolean isAuto;

    private static UpdateManager mInstance;
    private String downURL;

    public static UpdateManager getInstance() {
        if (mInstance == null) {
            mInstance = new UpdateManager();
        }
        return mInstance;
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UpdateManager.WHAT:
                    String response = msg.getData().getString("response");
                    try {
                        int localVersionCode = PackageUtils.getPackageVersionCode(mContext);

                        JSONObject d = new JSONObject(response).getJSONObject("item");
                        if (Utility.isEmpty(d)) {
                            if (isAuto) {
                                AndroidUtils.ToastMiddle(mContext, "已经是最新版本了");
                            }
                            return;
                        }
                        int versionCode = d.getInt("version");
                        String updateMsg = d.getString("updateMsg");
                        String downloadUrl = d.getString("downloadUrl");
                        String force = d.getString("force");
                        downURL = downloadUrl;
                        String channel = d.getString("channel");

                        List<String> types = new Gson().fromJson(channel,
                                new TypeToken<List<String>>() {
                                }.getType());

                        if (versionCode > localVersionCode) {
                            if (Utility.isEmpty(versionCode)) {
                                return;
                            }
                            if (force.equals("1")) {
//                                boolean isUpdate = useLoop(types, BuildConfig.channel);
//                                if (isUpdate) {
//                                    createDialog(updateMsg, true);
//                                }
                            } else if (force.equals("0")) {
                                createDialog(updateMsg, false);
                            }
                        } else {
                            if (isAuto) {
                                AndroidUtils.ToastMiddle(mContext, "已经是最新版本了");
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case DOWNLOAD:
                    // 设置进度条位置
                    if (progress > 100) {
                        progress = 100;
                    }
                    mProgress.setProgress(progress);
                    // 设置进度条数字显示
                    mTeProgress.setText("正在升级（" + progress + "%）");
                    break;
                case DOWNLOAD_FINISH:
                    // 安装文件
                    installApk();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public boolean useLoop(List<String> arr, String targetValue) {
        if (arr == null || arr.size() == 0) {
            return false;
        }
        for (String s : arr) {
            if (s.equals(targetValue))
                return true;
        }
        return false;
    }

    /**
     * 检测服务端APP的版本信息
     */
    public void checkServerAppVersionInfo(Context context, boolean isAuto) {
        if (!NetworkCheckUtils.isNetworkAvailable(context)) {
            AndroidUtils.ToastMiddle(context, "网络连接不可用");
            return;
        }
        this.mContext = context;
        this.isAuto = isAuto;

        new CheckUpdateThread().start();

    }

    private class CheckUpdateThread extends Thread {

        public CheckUpdateThread() {
            super();
        }

        @Override
        public void run() {
            super.run();

            Map<String, String> Map = new HashMap<>();
            Map.put("os", "android");
            Map.put("app_id", "4");

            String response = post(ProjectConfig.t_preurl + "index_wx.php/App/version", Map, "utf-8");

            if (response != null) {
                Message msg = new Message();
                msg.what = WHAT;
                Bundle bundle = new Bundle();
                bundle.putString("response", response);
                msg.setData(bundle);
                mHandler.sendMessage(msg);
            }
        }
    }

    public static StringBuffer getRequestData(Map<String, String> params, String encode) {
        StringBuffer stringBuffer = new StringBuffer();        //存储封装好的请求体信息
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                stringBuffer.append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), encode))
                        .append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);    //删除最后的一个"&"
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }

    private static String post(String url, Map<String, String> params, String encode) {

        byte[] data = getRequestData(params, encode).toString().getBytes();

        HttpURLConnection conn = null;
        try {
            URL mURL = new URL(url);
            conn = (HttpURLConnection) mURL.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(10000);
            conn.setDoOutput(true);

            OutputStream out = conn.getOutputStream();
            out.write(data);
            out.flush();
            out.close();

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                String response = getStringFromInputStream(is);
                return response;
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
                    throw new NetworkErrorException("response status is " + responseCode);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return null;
    }

    private static String getStringFromInputStream(InputStream is)
            throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        is.close();
        String state = os.toString();
        os.close();
        return state;
    }

    public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<>();

        if (json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<>();

        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }

    //自动更新对话框
    private void createDialog(String updateMsg, boolean isForce) {
        if (updateMsg.equals("null") || updateMsg.equals("")) {
            updateMessage = "修复若干bug";
        } else {
            updateMessage = updateMsg;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.update_dialog);
        View hintView = View.inflate(mContext, R.layout.dialog_update_hint, null);
        builder.setCancelable(false);
        builder.setView(hintView);
        final Dialog dialog = builder.create();
        dialog.show();
        //设置对话的宽高
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.dimAmount = 0.6f;
        params.height = BaseApplication.sHeight * 3 / 5;
        params.width = BaseApplication.sWidth * 4 / 5;
        dialog.getWindow().setAttributes(params);

        final TextView update_content = (TextView) hintView.findViewById(R.id.update_content);
        final ImageView close = (ImageView) hintView.findViewById(R.id.close);
        final TextView confirm = (TextView) hintView.findViewById(R.id.confirm);
        final LinearLayout layout_update = (LinearLayout) hintView.findViewById(R.id.layout_update);
        mProgress = (ProgressBar) hintView.findViewById(R.id.update_progress);
        mTeProgress = (TextView) hintView.findViewById(R.id.text_progress);

        //设置textview可上下滑动
        update_content.setMovementMethod(new ScrollingMovementMethod());

        update_content.setText(updateMessage);
        if (isForce) {
            //强制升级
            close.setVisibility(View.INVISIBLE);
            close.setOnClickListener(null);
        } else {
            close.setVisibility(View.VISIBLE);
            close.setOnClickListener(v -> dialog.dismiss());
        }
        confirm.setOnClickListener(v -> {
            if (ValidationUtils.isUrl(downURL)) {
                // 现在文件
                downloadApk();
                confirm.setClickable(false);
            } else {
                AndroidUtils.ToastMiddle(mContext, "下载地址不合法");
            }
            hideHintAnimation(confirm);

            new Handler().postDelayed(() -> showHintAnimation(layout_update), 300);
        });
    }

    /**
     * func:显示fab动画
     */
    private void showHintAnimation(View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX).setDuration(800).start();
    }

    /**
     * func:显示fab动画
     */
    private void hideHintAnimation(View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 0f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 0f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 0f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ).setDuration(300).start();
    }

    /**
     * 下载apk文件
     */
    private void downloadApk() {
        // 启动新线程下载软件
        new downloadApkThread().start();
    }

    private class downloadApkThread extends Thread {
        @Override
        public void run() {
            HttpURLConnection conn = null;
            try {
                // 判断SD卡是否存在，并且是否具有读写权限
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    // 获得存储卡的路径
                    String sdpath = Environment.getExternalStorageDirectory() + "/";
                    mSavePath = sdpath + "download";
                    URL url = new URL(downURL);
                    // 创建连接
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Accept-Encoding", "identity");
                    conn.connect();
                    // 获取文件大小
                    int length = conn.getContentLength();
                    // 创建输入流
                    InputStream is = conn.getInputStream();

                    File file = new File(mSavePath);
                    // 判断文件目录是否存在
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    File apkFile = new File(mSavePath, "银程金服");
                    FileOutputStream fos = new FileOutputStream(apkFile);
                    int count = 0;
                    // 缓存
                    byte buf[] = new byte[1024];
                    // 写入到文件中
                    do {
                        int numread = is.read(buf);
                        count += numread;
                        // 计算进度条位置
                        progress = (int) (((float) count / length) * 100);
                        // 更新进度
                        mHandler.sendEmptyMessage(DOWNLOAD);
                        if (numread <= 0) {
                            // 下载完成
                            mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                            break;
                        }
                        // 写入文件
                        fos.write(buf, 0, numread);
                    } while (!cancelUpdate);// 点击取消就停止下载.

                    fos.flush();
                    fos.close();
                    is.close();
                } else {
                    AndroidUtils.ToastMiddle(mContext, "未获取读写权限");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
        }
    }


    /**
     * 7.0 addFlags FLAG_GRANT_READ_URI_PERMISSION 代码顺序否则解析包失败
     */
    private void installApk() {
        File apkfile = new File(mSavePath, "银程金服");
        //提升读写权限,否则可能出现解析异常
        if (!mSavePath.isEmpty()) {
            SystemManager.setPermission(mSavePath);
        }
        if (!apkfile.exists()) {
            return;
        }
        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 24) {
            Uri uriForFile = FileProvider.getUriForFile(mContext.getApplicationContext(), "com.qirong.yinchengjinfu.fileprovider", apkfile);
            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            i.setDataAndType(uriForFile, "application/vnd.android.package-archive");
            //兼容8.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                boolean hasInstallPermission = mContext.getPackageManager().canRequestPackageInstalls();
                if (!hasInstallPermission) {
                    AndroidUtils.ToastMiddle(mContext, "请开启允许安装位置来源的应用程序");
                    startInstallPermissionSettingActivity();
                    return;
                }
            }
        } else {
            i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        }
        mContext.startActivity(i);
    }

    /**
     * 跳转到设置-允许安装未知来源-页面
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity() {
        //注意这个是8.0新API
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);

    }

}
