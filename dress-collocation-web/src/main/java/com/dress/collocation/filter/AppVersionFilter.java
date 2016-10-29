package com.dress.collocation.filter;


import com.alibaba.fastjson.JSONObject;
import com.dress.collocation.util.AppVersionUtils;
import com.dress.collocation.util.ResponseUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Description:AppVersion过滤器
 * User:Xue jiahao (xuejiahao@raycloud.com)
 * Date: 16/10/12
 * Time: 13:55
 * Version: 1.0
 **/
public class AppVersionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    @SuppressWarnings("unchecked")
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String appVersion = request.getParameter("appVersion");
        if (!AppVersionUtils.isAppVersion(appVersion)) {
            response.getWriter().write(ResponseUtils.systemError().toJSONString());
            return;
        }
//        Map<String,Object> parameterMap = request.getParameterMap();
//        if(parameterMap != null && parameterMap.containsKey("params")){
//            Map<String,Object> params = (Map<String,Object>)parameterMap.get("params");
//        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
