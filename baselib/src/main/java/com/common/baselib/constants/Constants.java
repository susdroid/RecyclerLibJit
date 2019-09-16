package com.common.baselib.constants;

public class Constants {


    //    public static final int BASE_ID = 0;
//    public static final int RQF_PAY = BASE_ID + 1;
//    public static final int RQF_INSTALL_CHECK = RQF_PAY + 1;
//
//    /**
//     * 连连支付，交易成功
//     */
//    public static final String PAY_RET_CODE_SUCCESS = "0000";//"0000";
//    /**
//     * 连连支付，交易取消
//     */
//    public static final String PAY_RET_CODE_CANCEL = "1006";//"0000";
//    /**
//     * 连连支付，余额不足
//     */
//    public static final int PAY_RET_CODE_INSUFFICIENT_BALANCE = 1005;//"0000";
//    /**
//     * 连连支付，支付处理中
//     */
//    public static final String PAY_RET_CODE_PROCESS = "2008";//"2008";
//    /**
//     * 交易异常，请联系客服
//     */
//    public static final int PAY_RET_CODE_UNKNOW_ERR = 9999;//"9999";
//
//    public static final String PAY_RESULT_PAY_SUCCESS = "SUCCESS";
//    public static final String PAY_RESULT_PAY_PROCESSING = "PROCESSING";
//    public static final String PAY_RESULT_PAY_FAILURE = "FAILURE";
//    public static final String PAY_RESULT_PAY_REFUND = "REFUND";
//
//    /**
//     * 主菜单角标位置
//     */
//    public static final class MenuIndex {
//        /*
//         * homefragement索引值
//         */
//        public static final int HOME_FRAGMENT_INDEX = 0;
//        /*
//         * InvesFragment索引值
//         */
//        public static final int INVES_FRAGMENT_INDEX = 1;
//        /*
//         * MallFragment索引值
//         */
//        public static final int MALL_FRAGMENT_INDEX = 2;
//        /*
//         * mineFragment索引值
//         */
//        public static final int MINE_FRAGMENT_INDEX = 3;
//    }
//
    public static class Config {
        public static final int PICK_CONTACT = 1; //打开通讯录
        public static final int SELECT_COUPON = 2;//选择优惠券
        public static final int INVES_TO_LOGIN = 3;//投资跳转登录
        public static final int AC_TO_LOGIN = 4;//活动跳转登录
        public static final int TO_AUTH = 4;//实名认证
        public static final int TO_CASH = 5;//实名认证
        public static final int TO_ALIPAY = 6;//支付宝认证
        public static final int TO_RECHARGE = 7;//支付宝认证
        public static final int TO_SELECT_BANK = 8;//选择银行
        public static final int OPEN_DEPOSIT_STATUS = 9;//开通存管等状态
    }
//
//    /**
//     * 全局变量,给更新sid的时候用,因为传入Context很不方便,这样可以实现参数和返回值的修改,方便使用
//     */
//    public static class ONLINE {
//        public static String SID = "";
//        public static String UID = "";
//        public static String AT = "";
//        public static String CHANNEL = "";
//        public static String VERSION = "";
//        public static String _SID = "11";
//        public static boolean DEPOSIT_STATUS = false;  //是否存管登录
//        public static boolean BIND_BANK_STATUS = false; //是否绑定银行卡
//        public static boolean CURRENT_STATUS = false; //当前状态是否存管
//        public static boolean CURRENT_FLAG = false; //登录界面标签状态，true 存管 - false 普通
//    }
}
