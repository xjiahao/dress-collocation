package com.dress.collocation.util;

import com.alibaba.fastjson.JSONObject;

/**
 * Description:
 * User:Xue jiahao (xuejiahao@raycloud.com)
 * Date: 16/10/12
 * Time: 15:29
 * Version: 1.0
 **/
public class ResponseUtils {

    /**
     * 系统错误
     *
     * @return
     */
    public static JSONObject systemError() {
        JSONObject object = new JSONObject();
        object.put("code", 0);
        object.put("message", "系统错误");
        return object;
    }

    /**
     * 业务操作错误信息返回
     *
     * @param message message 错误信息
     * @return
     */
    public static JSONObject ErrorResponse(String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("message", message);
        return jsonObject;
    }

    /**
     * 正确信息返回
     * @param object object 数据对象
     * @return
     */
    public static JSONObject SuccessResponse(Object object) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 100);
        jsonObject.put("data", object);
        return jsonObject;
    }
}
