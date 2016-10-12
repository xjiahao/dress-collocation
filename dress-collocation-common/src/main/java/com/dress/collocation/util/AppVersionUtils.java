package com.dress.collocation.util;

import static com.dress.collocation.constants.SymbolConstants.POINT;

/**
 * Description:AppVersion版本控制操作
 * User:Xue jiahao (xuejiahao@raycloud.com)
 * Date: 16/10/12
 * Time: 14:00
 * Version: 1.0
 **/
public class AppVersionUtils {

    /**
     * 是否符合版本格式
     *
     * @param appVersion 请求中的appVersion参数
     * @return
     */
    public static boolean isAppVersion(String appVersion) {
        if (StringUtils.isNotEmpty(appVersion)) {
            String[] appVersions = appVersion.split(POINT);
            if (appVersions.length > 4 || appVersions.length == 0) {
                return false;
            }
            for (String version : appVersions) {
                try {
                    Integer.parseInt(version);
                } catch (NumberFormatException e) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    /**
     * 比较两个版本大小
     *
     * @param appVersion       当前请求传入appVersion
     * @param targetAppVersion 待比较appVersion
     * @return 返回比较结果
     * true:当前请求传入appVersion > 待比较appVersion
     * false:当前请求传入appVersion <= 待比较appVersion
     */
    public static boolean compareAppVersion(String appVersion, String targetAppVersion) {
        String[] appVersions = appVersion.split(POINT);
        String[] targetAppVersions = targetAppVersion.split(POINT);
        int minSize = appVersions.length < targetAppVersions.length ? appVersions.length : targetAppVersions.length;
        for (int i = 0; i < minSize; i++) {
            int value = Integer.parseInt(appVersions[i]);
            int _value = Integer.parseInt(targetAppVersions[i]);
            if (value == _value) {
                if (i == minSize - 1) {
                    return appVersions.length > targetAppVersions.length;
                }
                continue;
            }
            return value > _value;
        }
        return false;
    }


}
