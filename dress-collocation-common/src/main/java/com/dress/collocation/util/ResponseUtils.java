package com.dress.collocation.util;

import com.alibaba.fastjson.JSONObject;
import com.dress.collocation.exception.SystemBizException;
import com.dress.collocation.exception.SystemSessionException;

/**
 * Description:请求返回封装
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
     * @param e e 错误信息
     * @return
     */
    public static JSONObject ErrorResponse(Exception e) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        if(e instanceof SystemBizException || e instanceof SystemSessionException){
            jsonObject.put("message", e.getMessage());
        }else{
            jsonObject.put("message", "系统错误");
        }
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
