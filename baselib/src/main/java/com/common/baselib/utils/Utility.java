package com.common.baselib.utils;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

    /**
     * 判断一个未知对象是否为空
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null)
            return true;

        if (obj instanceof CharSequence)
            return ((CharSequence) obj).length() == 0;

        if (obj instanceof Collection)
            return ((Collection) obj).isEmpty();

        if (obj instanceof Map)
            return ((Map) obj).isEmpty();

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }


    public static String replaceNum(String paramString) {
        if (paramString.length() < 7) {
            return paramString;
        }
        char[] arrayOfChar = new char[paramString.length()];
        arrayOfChar = paramString.toCharArray();
        arrayOfChar[3] = '*';
        arrayOfChar[4] = '*';
        arrayOfChar[5] = '*';
        arrayOfChar[6] = '*';
        return String.valueOf(arrayOfChar);
    }

    //调用桌面app
    public static void openAppByPackageName(Application app, Context context, String packageName) {

        PackageInfo pi;

        try {
            pi = app.getPackageManager().getPackageInfo(packageName, 0);

            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);

            resolveIntent.setPackage(pi.packageName);

            PackageManager pManager = app.getPackageManager();

            List<ResolveInfo> apps = pManager.queryIntentActivities(

                    resolveIntent, 0);

            ResolveInfo ri = apps.iterator().next();

            if (ri != null) {

                packageName = ri.activityInfo.packageName;

                String className = ri.activityInfo.name;

                Intent intent = new Intent(Intent.ACTION_MAIN);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

                ComponentName cn = new ComponentName(packageName, className);

                intent.setComponent(cn);

                context.startActivity(intent);

            }

        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();

        }
    }


    //监听小数点让用户只能输入小数点后两位
    public static void setPricePoint(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });
    }


    //去零
    public static void setClearFontZero(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                int len = s.toString().length();
                if (len > 1 && text.startsWith("0")) {
                    s.replace(0, 1, "");
                }
            }

        });
    }

    public static String getOnlyChinese(String str){
        String reg = "[^\u4e00-\u9fa5]";
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(str);
        String res = mat.replaceAll("");
        return res;
    }


    public static String getTextString(EditText str){
        return  str.getText().toString().trim();
    }

    public static Boolean isEmpty(String str){
        if(str == null){
            return  true;
        }

        if(str.trim().isEmpty()){
            return  true;
        }else{
            return  false;
        }
    }

    public static boolean isNumeric(String str){
        if(null == str || "".equals(str)){
            return  false;
        }

        for (int i = 0; i < str.length(); i++){
            System.out.println(str.charAt(i));
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

}
