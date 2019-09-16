package com.common.baselib.utils;

import android.content.Context;

import com.common.baselib.constants.UserInfo;


/**
 * SharedPreferences  管理工具类
 * Created by chen on 2015/4/7.
 */
public class PrefHelper {

    //登录模块-普通版token
    public static void setCommonToken(Context context, String token) {
        PreferencesUtils.putString(context
                , UserInfo.COMMON_TOKEN
                , token);
    }

    public static String getCommonToken(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.COMMON_TOKEN);
    }

    //登录模块-存管版版token
    public static void setAccessToken(Context context, String token) {
        PreferencesUtils.putString(context
                , UserInfo.CH_TOKEN
                , token);
    }

    public static String getAccessToken(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.CH_TOKEN);
    }

    //登录模块-sid
    public static void setSid(Context context, String sid) {
        PreferencesUtils.putString(context
                , UserInfo.CH_SID
                , sid);
    }

    public static String getSid(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.CH_SID);
    }

    //登录模块-uid
    public static void setUid(Context context, String uid) {
        PreferencesUtils.putString(context
                , UserInfo.CH_UID
                , uid);
    }

    public static String getUid(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.CH_UID);
    }

    //登录模块-cd
    public static void setCd(Context context, String cd) {
        PreferencesUtils.putString(context
                , UserInfo.CH_CD
                , cd);
    }

    public static String getCd(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.CH_CD);
    }


    //登录模块-salt
    public static void setSalt(Context context, String salt) {
        PreferencesUtils.putString(context
                , UserInfo.CH_SALT
                , salt);
    }

    public static String getSalt(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.CH_SALT);
    }

    //登录模块-用户首次登陆
    public static void setCommonOpen(Context context, boolean state) {
        PreferencesUtils.putBoolean(context
                , UserInfo.FIRST_OPEN
                , state);
    }

    public static boolean getCommonOpen(Context context) {
        return PreferencesUtils.getBoolean(context
                , UserInfo.FIRST_OPEN);
    }

    //登录模块-用户的登录状态
    public static void setLoginState(Context context, boolean state) {
        PreferencesUtils.putBoolean(context
                , UserInfo.LOGIN_STATE
                , state);
    }

    public static boolean getLoginState(Context context) {
        return PreferencesUtils.getBoolean(context
                , UserInfo.LOGIN_STATE);
    }

    //个人中心模块-username
    public static void setUserName(Context context, String username) {
        PreferencesUtils.putString(context
                , UserInfo.USER_NAME
                , username);
    }

    public static String getUserName(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.USER_NAME);
    }

    //个人中心模块-理财师提示
    public static void setInviteHint(Context context, String InviteHint) {
        PreferencesUtils.putString(context
                , UserInfo.INVITE_HINT
                , InviteHint);
    }

    public static String getInviteHint(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.INVITE_HINT);
    }

    //个人中心模块-mobile
    public static void setMobile(Context context, String mobile) {
        PreferencesUtils.putString(context
                , UserInfo.MOBILE
                , mobile);
    }

    public static String getMobile(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.MOBILE);
    }

    //个人中心模块-mobile_hiden
    public static void setMobileHiden(Context context, String mobile) {
        PreferencesUtils.putString(context
                , UserInfo.MOBILE_HIDEN
                , mobile);
    }

    public static String getMobileHiden(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.MOBILE_HIDEN);
    }

    //个人中心模块-account
    public static void setAccount(Context context, String account) {
        PreferencesUtils.putString(context
                , UserInfo.ACCOUNT
                , account);
    }

    public static String getAccount(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.ACCOUNT);
    }

    //个人中心模块-name
    public static void setName(Context context, String name) {
        PreferencesUtils.putString(context
                , UserInfo.NAME
                , name);
    }

    public static String getName(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.NAME);
    }

    //个人中心模块-交易密码
    public static void setDefaultPayPwd(Context context, int account) {
        PreferencesUtils.putInt(context
                , UserInfo.DEFAULT_PAY_PWD
                , account);
    }

    public static int getDefaultPayPwd(Context context) {
        return PreferencesUtils.getInt(context
                , UserInfo.DEFAULT_PAY_PWD);
    }

    //个人中心模块-password
    public static void setPwd(Context context, String pwd) {
        PreferencesUtils.putString(context
                , UserInfo.PASSWORD
                , pwd);
    }

    public static String getPwd(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.PASSWORD);
    }

    //个人中心模块-是否显示金额
    public static void setShowAmount(Context context, boolean show) {
        PreferencesUtils.putBoolean(context
                , UserInfo.SHOW_AMOUNT
                , show);
    }

    public static boolean getShowAmout(Context context) {
        return PreferencesUtils.getBoolean(context
                , UserInfo.SHOW_AMOUNT);
    }


    //个人中心模块-是否有默认头像
    public static void setDefaultAvatar(Context context, boolean show) {
        PreferencesUtils.putBoolean(context
                , UserInfo.DEFAULT_AVATAR
                , show);
    }

    public static boolean getDefaultAvatar(Context context) {
        return PreferencesUtils.getBoolean(context
                , UserInfo.DEFAULT_AVATAR);
    }

    //个人中心模块-指纹识别
    public static void setFingerHave(Context context, boolean show) {
        PreferencesUtils.putBoolean(context
                , UserInfo.FINGER_HAVE
                , show);
    }

    public static boolean getFingerHave(Context context) {
        return PreferencesUtils.getBoolean(context
                , UserInfo.FINGER_HAVE);
    }

    //个人中心模块-是否显示注册提示框
    public static void setShowActivity(Context context, boolean show) {
        PreferencesUtils.putBoolean(context
                , UserInfo.SHOW_ACTIVITY
                , show);
    }

    public static boolean getShowActivity(Context context) {
        return PreferencesUtils.getBoolean(context
                , UserInfo.SHOW_ACTIVITY);
    }

    //手势模块-是否显示注册提示框
    public static void setRegisterFlag(Context context, boolean show) {
        PreferencesUtils.putBoolean(context
                , UserInfo.REGISTER_FLAG
                , show);
    }

    public static boolean getRegisterFlag(Context context) {
        return PreferencesUtils.getBoolean(context
                , UserInfo.REGISTER_FLAG);
    }

    //个人中心模块-avatar
    public static void setAvatar(Context context, String avatar) {
        PreferencesUtils.putString(context
                , UserInfo.AVATAR
                , avatar);
    }

    public static String getAvatar(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.AVATAR);
    }

    //个人中心模块-alipay
    public static void setAlipay(Context context, String alipay) {
        PreferencesUtils.putString(context
                , UserInfo.ALIPAY
                , alipay);
    }

    public static String getAlipay(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.ALIPAY);
    }

    //个人中心模块-自动投标排名
    public static void setAutoSort(Context context, String autosort) {
        PreferencesUtils.putString(context
                , UserInfo.AUTO_SORT
                , autosort);
    }

    public static String getAutoSort(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.AUTO_SORT);
    }

    //个人中心模块-是否开启手势
    public static void setGesturesState(Context context, boolean state) {
        PreferencesUtils.putBoolean(context
                , UserInfo.GESTURES_STATE
                , state);
    }

    public static boolean getGesturesState(Context context) {
        return PreferencesUtils.getBoolean(context
                , UserInfo.GESTURES_STATE);
    }


    //个人中心模块-是否开启音效
    public static void setSoundStatus(Context context, boolean state) {
        PreferencesUtils.putBoolean(context
                , UserInfo.SOUND_STATUS
                , state);
    }

    public static boolean getSoundStatus(Context context) {
        return PreferencesUtils.getBoolean(context
                , UserInfo.SOUND_STATUS);
    }

    //个人中心模块-是否开启指纹
    public static void setTouchIDState(Context context, boolean state) {
        PreferencesUtils.putBoolean(context
                , UserInfo.TOUCHID_STATE
                , state);
    }

    public static boolean getTouchIDState(Context context) {
        return PreferencesUtils.getBoolean(context
                , UserInfo.TOUCHID_STATE);
    }

    //个人中心模块-是否账户认证
    public static void setAuthCheckState(Context context, int state) {
        PreferencesUtils.putInt(context
                , UserInfo.AUTH_CHECK_STATE
                , state);
    }

    public static int getAuthCheckState(Context context) {
        return PreferencesUtils.getInt(context
                , UserInfo.AUTH_CHECK_STATE);
    }

    //个人中心模块-是否投过标
    public static void setNewUser(Context context, int state) {
        PreferencesUtils.putInt(context
                , UserInfo.NEW_USER
                , state);
    }

    public static int getNewUser(Context context) {
        return PreferencesUtils.getInt(context
                , UserInfo.NEW_USER);
    }

    //个人中心模块-是否新标
    public static void setNewInves(Context context, boolean state) {
        PreferencesUtils.putBoolean(context
                , UserInfo.NEW_INVES
                , state);
    }

    public static boolean getNewInves(Context context) {
        return PreferencesUtils.getBoolean(context
                , UserInfo.NEW_INVES);
    }

    //个人中心模块-记录资产总额
    public static void setRecordAmount(Context context, String number) {
        PreferencesUtils.putString(context
                , UserInfo.RECORD_AMOUT
                , number);
    }

    public static String getRecordAmount(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.RECORD_AMOUT);
    }

    //个人中心模块-记录累计收益
    public static void setAccuEarnings(Context context, String number) {
        PreferencesUtils.putString(context
                , UserInfo.ACC_EARNGINS
                , number);
    }

    public static String getAccuEarnings(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.ACC_EARNGINS);
    }

    //个人中心模块-记录累计收益
    public static void setUsableBalance(Context context, String number) {
        PreferencesUtils.putString(context
                , UserInfo.USABLE_BALANCE
                , number);
    }

    public static String getUsableBalance(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.USABLE_BALANCE);
    }

    //个人中心模块-记录待收收益
    public static void setWaitBalance(Context context, String number) {
        PreferencesUtils.putString(context
                , UserInfo.WAIT_BALANCE
                , number);
    }

    public static String getWaitBalance(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.WAIT_BALANCE);
    }

    //个人中心模块-记录体验金
    public static void setExperience(Context context, String number) {
        PreferencesUtils.putString(context
                , UserInfo.EXPERIENCE
                , number);
    }

    public static String getExperience(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.EXPERIENCE);
    }

    //是否还款状态
    public static void setBackStatus(Context context, boolean state) {
        PreferencesUtils.putBoolean(context
                , UserInfo.BACK_STATUS
                , state);
    }

    public static boolean getBackStatus(Context context) {
        return PreferencesUtils.getBoolean(context
                , UserInfo.BACK_STATUS);
    }


    //个人中心模块-location
    public static void setLocation(Context context, String location) {
        PreferencesUtils.putString(context
                , UserInfo.APP_LOCATION
                , location);
    }

    public static String getLocation(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.APP_LOCATION);
    }

    //个人中心模块-是否开启定位上传
    public static void setLocationStatus(Context context, boolean state) {
        PreferencesUtils.putBoolean(context
                , UserInfo.LOCATION_STATUS
                , state);
    }

    public static boolean getLocationStatus(Context context) {
        return PreferencesUtils.getBoolean(context
                , UserInfo.LOCATION_STATUS);
    }

    //个人中心模块-风险评估状态
    public static void setRiskStatus(Context context, boolean status) {
        PreferencesUtils.putBoolean(context
                , UserInfo.RISK_STATUS
                , status);
    }

    public static boolean getRiskStatus(Context context) {
        return PreferencesUtils.getBoolean(context
                , UserInfo.RISK_STATUS);
    }

    //个人中心模块-缴费授权状态
    public static void setPayLimitsStatus(Context context, boolean status) {
        PreferencesUtils.putBoolean(context
                , UserInfo.PAY_LIMITS
                , status);
    }

    public static boolean getPayLimitsStatus(Context context) {
        return PreferencesUtils.getBoolean(context
                , UserInfo.PAY_LIMITS);
    }

    //个人中心模块-自动投标缴费授权状态
    public static void setAutoLimitsStatus(Context context, boolean status) {
        PreferencesUtils.putBoolean(context
                , UserInfo.AUTO_LIMITS
                , status);
    }

    public static boolean getAutoLimitsStatus(Context context) {
        return PreferencesUtils.getBoolean(context
                , UserInfo.AUTO_LIMITS);
    }

    //个人中心模块-风险评估类型
    public static void setRiskType(Context context, String status) {
        PreferencesUtils.putString(context
                , UserInfo.RISK_TYPE
                , status);
    }

    public static String getRiskType(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.RISK_TYPE);
    }


    //个人中心模块-风险评估分数
    public static void setRiskScore(Context context, String status) {
        PreferencesUtils.putString(context
                , UserInfo.RISK_SCORE
                , status);
    }

    public static String getRiskScore(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.RISK_SCORE);
    }

    /**
     * 注册活动弹窗
     */
    //ActivityImage
    public static void setActivityImage(Context context, String image) {
        PreferencesUtils.putString(context
                , UserInfo.ACTIVITY_IMAGE
                , image);
    }

    public static String getActivityImage(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.ACTIVITY_IMAGE);
    }

    //ActivityContent
    public static void setActivityContent(Context context, String image) {
        PreferencesUtils.putString(context
                , UserInfo.ACTIVITY_CONTENT
                , image);
    }

    public static String getActivityContent(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.ACTIVITY_CONTENT);
    }

    //ActivityMoney
    public static void setActivityMoney(Context context, String image) {
        PreferencesUtils.putString(context
                , UserInfo.ACTIVITY_MONEY
                , image);
    }

    public static String getActivityMoney(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.ACTIVITY_MONEY);
    }

    //ActivityStatus
    public static void setActivityStatus(Context context, String image) {
        PreferencesUtils.putString(context
                , UserInfo.ACTIVITY_STATUS
                , image);
    }

    public static String getActivityStatus(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.ACTIVITY_STATUS);
    }

    //活动模块-今日头条激活
    public static void setActivate(Context context, String image) {
        PreferencesUtils.putString(context
                , UserInfo.ACTIVATE
                , image);
    }

    public static String getActivate(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.ACTIVATE);
    }

    //投资列表模块-投资列表标名
    public static void setTitleInves(Context context, String[] status) {
        PreferencesUtils.putStringArrays(context
                , UserInfo.TITLE_INVES
                , status);
    }

    public static String[] getTitleInves(Context context) {
        return PreferencesUtils.getStringArrays(context
                , UserInfo.TITLE_INVES);
    }

    //投资列表模块-请求数据参数
    public static void setTitleFlag(Context context, String[] status) {
        PreferencesUtils.putStringArrays(context
                , UserInfo.TITLE_FLAG
                , status);
    }

    public static String[] getTitleFlag(Context context) {
        return PreferencesUtils.getStringArrays(context
                , UserInfo.TITLE_FLAG);
    }

    //注册红包
    public static void setRegisterNum(Context context, String num) {
        PreferencesUtils.putString(context
                , UserInfo.REGISTER_NUM
                , num);
    }

    public static String getRegisterNum(Context context) {
        return PreferencesUtils.getString(context, UserInfo.REGISTER_NUM);
    }

    /**
     * 存管模式
     *
     * @param context
     * @param status
     */
    //是否存管状态模式
    public static void setDepositStatus(Context context, boolean status) {
        PreferencesUtils.putBoolean(context
                , UserInfo.DEPOSIT_STATUS
                , status);
    }

    public static boolean getDepositStatus(Context context) {
        return PreferencesUtils.getBoolean(context
                , UserInfo.DEPOSIT_STATUS);
    }

    //是否开户
    public static void setOpenDeposit(Context context, boolean status) {
        PreferencesUtils.putBoolean(context
                , UserInfo.OPEN_STATUS
                , status);
    }

    public static boolean getOpenDeposit(Context context) {
        return PreferencesUtils.getBoolean(context
                , UserInfo.OPEN_STATUS);
    }

    //是否修改手机号
    public static void setModifyPhone(Context context, boolean status) {
        PreferencesUtils.putBoolean(context
                , UserInfo.MODIFY_PHONE
                , status);
    }

    public static boolean getModifyPhone(Context context) {
        return PreferencesUtils.getBoolean(context
                , UserInfo.MODIFY_PHONE);
    }

//    //是否设置存管交易密码
//    public static void setDepositPwd(Context context, boolean status) {
//        PreferencesUtils.putBoolean(context
//                , UserInfo.DEPOSIT_PWD
//                , status);
//    }

    //    public static boolean getDepositPwd(Context context) {
//        return PreferencesUtils.getBoolean(context
//                , UserInfo.DEPOSIT_PWD);
//    }
    //登录显示开户dialog
    public static void setShowBankDialog(Context context, boolean state) {
        PreferencesUtils.putBoolean(context
                , UserInfo.SHOW_BANK
                , state);
    }

    public static boolean getShowBankDialog(Context context) {
        return PreferencesUtils.getBoolean(context
                , UserInfo.SHOW_BANK);
    }

    //绑定银卡信息
    public static void setBankInfo(Context context, String info) {
        PreferencesUtils.putString(context
                , UserInfo.BANK_INFO
                , info);
    }

    public static String getBankInfo(Context context) {
        return PreferencesUtils.getString(context, UserInfo.BANK_INFO);
    }

    //指向投标列表位置
    public static void setInvesType(Context context, String type) {
        PreferencesUtils.putString(context
                , UserInfo.INVES_TYPE
                , type);
    }

    public static String getInvesType(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.INVES_TYPE);
    }

    //分享模块-邀请二维码
    public static void setShareCode(Context context, String shareCode) {
        PreferencesUtils.putString(context
                , UserInfo.SHARE_CODE
                , shareCode);
    }

    public static String getShareCode(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.SHARE_CODE);
    }

    //分享模块-邀请码
    public static void setShareInvite(Context context, String ShareInvite) {
        PreferencesUtils.putString(context
                , UserInfo.SHARE_INVITE
                , ShareInvite);
    }

    public static String getShareInvite(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.SHARE_INVITE);
    }

    //分享模块-shareUrl
    public static void setShareUrl(Context context, String shareurl) {
        PreferencesUtils.putString(context
                , UserInfo.SHARE_URL
                , shareurl);
    }

    public static String getShareUrl(Context context) {
        return PreferencesUtils.getString(context
                , UserInfo.SHARE_URL);
    }

    //菜单底部选中
    public static void setIconSel(Context context, String[] status) {
        PreferencesUtils.putStringArrays(context
                , UserInfo.TITLE_SEL
                , status);
    }

    public static String[] getIconSel(Context context) {
        return PreferencesUtils.getStringArrays(context
                , UserInfo.TITLE_SEL);
    }

    //菜单底部未选中
    public static void setIconUnSel(Context context, String[] status) {
        PreferencesUtils.putStringArrays(context
                , UserInfo.TITLE_UNSEL
                , status);
    }

    public static String[] getIconUnSel(Context context) {
        return PreferencesUtils.getStringArrays(context
                , UserInfo.TITLE_UNSEL);
    }

    //菜单底部文案
    public static void setBottomText(Context context, String[] status) {
        PreferencesUtils.putStringArrays(context
                , UserInfo.BOTTOM_TEXT
                , status);
    }

    public static String[] getBottomText(Context context) {
        return PreferencesUtils.getStringArrays(context
                , UserInfo.BOTTOM_TEXT);
    }

    //清空底部选中
    public static void ClearIconSel(Context context) {
        PreferencesUtils.ClearKey(context, UserInfo.TITLE_SEL);
    }

    //清空底部未选中
    public static void ClearIconUnSel(Context context) {
        PreferencesUtils.ClearKey(context, UserInfo.TITLE_UNSEL);
    }

    //清空底部文案
    public static void ClearBottomText(Context context) {
        PreferencesUtils.ClearKey(context, UserInfo.BOTTOM_TEXT);
    }

    //切换域名
    public static void setDoMain(Context context, String info) {
        PreferencesUtils.putString(context
                , UserInfo.DO_MAIN, info);
    }

    public static String getDoMain(Context context) {
        return PreferencesUtils.getString(context, UserInfo.DO_MAIN);
    }

}
