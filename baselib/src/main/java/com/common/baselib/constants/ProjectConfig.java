package com.common.baselib.constants;


import com.common.baselib.BaseApplication;
import com.common.baselib.utils.update.PackageUtils;

/**
 * Created by cyq on 16/3/9.
 */
public class ProjectConfig extends BaseProjectConfig {

    public static String appid = "4";
    public static String secret = "FHueh83HFHFjjdh883";
    public static String version = "";
    public static String os = "android";

    static {
        version = "v" + PackageUtils.getPackageVersionName(BaseApplication.getContext());
    }

    /**
     * 测试域名
     */
//    public static final String preUrl = "http://120.27.211.129/";
    /**
     * 正式域名
     */
    public static final String preUrl = "https://old.yinchenglicai.com/";

    /**
     * 网页
     */
    public static String t_preurl = "https://www.yinchenglicai.com/";

}
