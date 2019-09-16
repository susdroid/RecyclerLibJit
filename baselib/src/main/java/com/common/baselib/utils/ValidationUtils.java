package com.common.baselib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;


import com.common.baselib.constants.ProjectConfig;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;

/**
 * 字符串格式验证工具：
 * <em>
 * 1.手机号码（格式）；
 * 2.验证码（位数）；
 * 3.密码（位数）；
 * 4.店铺名（非法字符）；
 * 5.身份证号码（格式）；
 * 6.邀请码（位数）；
 * 7.商品名（不能包含非法字符）；
 * 8.订单号（只需要验证是否为空）；
 * 9.快递单号（只需要验证是否为空）；
 * 10.价格、运费（只能是数字，可以整数和小数）；
 * 11.图片路径（只需要验证是否为空）；
 * 12.验证自然数
 * </em>
 * Created by caojing on 2016/2/24.
 */
public class ValidationUtils {

    /**
     * 验证自然数
     *
     * @param number
     * @return
     */
    public static boolean isNaturalNumber(String number) {
        Pattern pattern = Pattern.compile("(0|^[1-9]\\d*$)");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    /**
     * 验证银行卡号
     * @param number
     * @return
     */
    /**
     * 验证银行卡号
     */
    public static boolean CardNoCheck(Activity context, String number) {
        if (number.length() == 0) {
            AndroidUtils.Toast(context, "木有输入卡号。。。");
            return false;
        } else if (!isCard_no(number)) {
            AndroidUtils.Toast(context, "卡号长度不正确。。。");
            return false;
        }
        return true;
    }

    public static boolean isCard_no(String number) {
        Pattern pattern = Pattern.compile("(^\\d{16}$)|(^\\d{19}$)");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    /**
     * 检验手机号
     */
    public static boolean PhoneNumberCheck(Activity context, String number) {
        if (number.length() == 0) {
            AndroidUtils.Toast(context, "请填写手机号");
            return false;
        } else if (number.length() != 11) {
            AndroidUtils.Toast(context, "手机号长度错了");
            return false;
        }
        return true;
    }

    public static boolean PhoneNumberCheckR(Activity context, String number) {
        if (number.length() == 0) {
            AndroidUtils.ToastWhite(context, "请填写手机号");
            return false;
        } else if (number.length() != 11) {
            AndroidUtils.ToastWhite(context, "手机号长度错了");
            return false;
        }
        return true;
    }

    //判断输入手机号是否为手机号
    public static boolean isPhoneNumber(String number) {
        Pattern pattern = Pattern.compile("^(13[0-9]|14[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9]|19[0-9])[0-9]{8}$");
        return pattern.matcher(number).matches();
    }

    /**
     * 手机号打星号
     *
     * @param num 手机号
     * @param s   前留的位数
     * @param e   后留的位数
     * @return
     */
    public static String mobileNumReplaceStar(String num, int s, int e) {
        num = num.replaceAll("\\*", "1");
        String z = "(?<=[\\d]{" + s + "})\\d(?=[\\d]{" + e + "})";
        num = num.replaceAll(z, "*");
        return num;
    }


    public static String replaComma(String s) {
        String str = s;
        str = str.replace(",", "");
        return str;
    }

    /**
     * 检验 验证码位数
     */
    public static boolean VerifyCodeCheck(Activity context, String number) {
        if (number.length() == 0) {
            AndroidUtils.Toast(context, "请输入验证码。。。");
            return false;
        } else if (number.length() != 6) {
            AndroidUtils.Toast(context, "验证码长度错了。。。");
            return false;
        }
        return true;
    }

    public static boolean VerifyCodeCheckR(Activity context, String number) {
        if (number.length() == 0) {
            AndroidUtils.ToastWhite(context, "请输入验证码。。。");
            return false;
        } else if (number.length() != 6) {
            AndroidUtils.ToastWhite(context, "验证码长度错了。。。");
            return false;
        }
        return true;
    }

    /**
     * 检验密码位数
     */
    public static boolean PassWordCheck(Activity context, String number) {
        if (number.length() == 0) {
            AndroidUtils.Toast(context, "密码不能为空");
            return false;
        } else if (TextUtils.isEmpty(number)) {
            AndroidUtils.Toast(context, "密码不能为空");
            return false;
        } else if (number.length() < 6 || number.length() > 18) {
            AndroidUtils.Toast(context, "密码长度是6-18位");
            return false;
        }
        return true;
    }

    public static boolean PassWordCheckR(Activity context, String number) {
        if (number.length() == 0) {
            AndroidUtils.ToastWhite(context, "密码不能为空");
            return false;
        } else if (TextUtils.isEmpty(number)) {
            AndroidUtils.ToastWhite(context, "密码不能为空");
            return false;
        } else if (number.length() < 6 || number.length() > 18) {
            AndroidUtils.ToastWhite(context, "密码长度是6-18位");
            return false;
        }
        return true;
    }


    /**
     * 检验身份证号码
     */
    public static boolean IDNumberCheck(Activity context, String number) {
        if (number.length() == 0) {
            AndroidUtils.Toast(context, "木有输入身份证号码");
            return false;
        }
        return true;
    }

    //判断身份证号码格式是否正确
    public static boolean isIDNumber(String number) {
        Pattern pattern = Pattern.compile("(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)");
        return pattern.matcher(number).matches();
    }


    //判断货币字符是否为数字
    public static boolean isPrice(String number) {
        Pattern pattern = Pattern.compile("^[+]?\\d+(.\\d)?$[+]?");
        return pattern.matcher(number).matches();
    }

    /**
     * 检验图片路径
     */
    public static boolean ObjectCheck(Object number) {
        if (number == null) {
            return false;
        }
        return true;
    }

    /**
     * String  判空
     */
    public static boolean StringEmptyCheck(String number) {
        if (number == null) {
            return false;
        }
        if (number.length() == 0) {
            return false;
        }
        if (number.equals("")) {
            return false;
        }
        if (number.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * 去掉小数点后不需要的零
     */
    public static String DecimalCheck(String number) {
        if (number.indexOf(".") > 0) {
            //正则表达
            number = number.replaceAll("0+?$", "");//去掉后面无用的零
            number = number.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
        }
        return number;
    }

    /**
     * 去掉数字前不需要的零
     */
    public static String DecimalCheckFront(String number) {
        //正则表达
        number = number.replaceAll("0*(\\d+)", "$1");//去掉前面无用的零

        return number;
    }

    //判断是否有http头部
    public static boolean isUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        Pattern pattern = Pattern.compile("[a-zA-z]+://[^\\s]*");
        return pattern.matcher(url).matches();
    }

    //拼装url
    public static String urlCheck(String url) {

        if (url.contains("?")) {
            return url + "&";
        }
        return url + "?";
    }

    /**
     * 根据路径名称补全图片路径, 可传入空值
     *
     * @param uri
     * @return
     */
    public static String getUri(@Nullable String uri) {
        if (uri == null) return "";
        if (isUrl(uri)) {
            return uri.replace("\\\\/", "\\/");
        }
        String uriString = ProjectConfig.preUrl + uri;
        return uriString.replace("\\\\/", "\\/");
    }

    public static String getProtectedMobile(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber) || phoneNumber.length() < 11) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(phoneNumber.subSequence(0, 3));
        builder.append("****");
        builder.append(phoneNumber.subSequence(7, 11));
        return builder.toString();
    }

    /**
     * 判断list数组是否有重复值  true  有重复值，false 没有重复值
     */
    public static boolean cheakRepeat(List list) {
        HashSet hashSet = new HashSet();
        for (Object i : list) {
            hashSet.add(i);
        }
        if (hashSet.size() == list.size()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 在字符串中添加分隔符
     *
     * @param srcStr   原字符串
     * @param seprator 分隔符
     * @param count    间隔几个字符加分隔符
     * @return 处理后的字符串
     */
    public static String appendSeprator(String srcStr, String seprator, int count) {
        StringBuffer sb = new StringBuffer(srcStr);
        int index = count;
        while (sb.length() > count && index < sb.length() - 1) {
            sb.insert(index, seprator);
            index += count + 1;
        }
        return sb.toString();
    }

    public static String getProtectedAllText() {

        StringBuilder builder = new StringBuilder();
        builder.append("* * * *");
        return builder.toString();
    }

    /**
     * 千位分割号
     */

    public static String parseMoney(String text) {
        if (TextUtils.isEmpty(text)) {
            return text;
        }

        DecimalFormat df = null;
        if (text.indexOf(".") > 0) {
            if (text.length() - text.indexOf(".") - 1 == 0) {
                df = new DecimalFormat("###,##0.");
            } else if (text.length() - text.indexOf(".") - 1 == 1) {
                df = new DecimalFormat("###,##0.0");
            } else {
                df = new DecimalFormat("###,##0.00");
            }
        } else {
            df = new DecimalFormat("###,##0");
        }
        double number = 0.0;
        try {
            number = Double.parseDouble(text);
        } catch (Exception e) {
            number = 0.0;
        }
        return df.format(number);
    }

    /**
     * 取整
     */
    public static int getInteger(String number) {
        int idx = number.lastIndexOf(".");//查找小数点的位置
        String strNum = number.substring(0, idx);//截取从字符串开始到小数点位置的字符串，就是整数部分
        int num = Integer.valueOf(strNum);//把整数部分通过Integer.valueof方法转换为数字
        return num;
    }

    /**
     * 保留两位小数 四舍五入
     */
    public static String getDecimaT(String number) {
        BigDecimal bd1 = new BigDecimal(number);
        BigDecimal bd2 = bd1.setScale(2, RoundingMode.HALF_EVEN);
        String amout = bd2.toString();
        return amout;
    }

    /**
     * 保留两位小数  只取值
     */

    public static String getDecimaD(String number) {
        BigDecimal bd1 = new BigDecimal(number);
        BigDecimal bd2 = bd1.setScale(2, RoundingMode.FLOOR);
        String amout = bd2.toString();
        return amout;
    }

    /**
     * 字符串%小写  二位百分号
     */
    public static SpannableString setSpanText(String text, int size) {
        int pos1 = text.indexOf("%");
        int pos2 = text.indexOf("+");
        SpannableString msp = new SpannableString(text);
        if (text.length() == 0) {
            return msp;
        }
        if (!text.contains(".")) {
            return msp;
        }
        // 第一个%
        msp.setSpan(new AbsoluteSizeSpan(size), pos1, pos1 + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        msp.setSpan(new AbsoluteSizeSpan(size), pos2, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return msp;
    }

    /**
     * 字符串%小写
     *
     * @param text
     * @param size
     * @return
     */
    public static SpannableString setSpanTextOne(String text, int size) {
        int pos = text.indexOf("%");
        SpannableString msp = new SpannableString(text);
        if (text.length() == 0) {
            return msp;
        }
        if (!text.contains(".")) {
            return msp;
        }
        // 第一个%
        msp.setSpan(new AbsoluteSizeSpan(size), pos, pos + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return msp;
    }

    /**
     * 检测是否已安装微信
     */
    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }

        return false;
    }


}
