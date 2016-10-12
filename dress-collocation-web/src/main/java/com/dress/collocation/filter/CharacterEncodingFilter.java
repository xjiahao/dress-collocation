package com.dress.collocation.filter;



import javax.servlet.*;
import java.io.IOException;

/**
 * Description:字符过滤过滤器
 * User:Xue jiahao (xuejiahao@raycloud.com)
 * Date: 16/10/12
 * Time: 13:55
 * Version: 1.0
 **/
public class CharacterEncodingFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
