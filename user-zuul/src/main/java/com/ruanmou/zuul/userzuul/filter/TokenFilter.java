package com.ruanmou.zuul.userzuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.ruanmou.zuul.userzuul.result.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @ProjectName: house
 * @Package: com.ruanmou.zuul.userzuul.filter
 * @ClassName: TokenFilter
 * @Author: majiafei
 * @Description: <pre>
 *     token的过滤器
 * </pre>
 * @Date: 2018/12/25 12:13
 */
@SpringBootConfiguration
public class TokenFilter extends ZuulFilter {


    @Autowired
    private RestTemplate restTemplate;

    private static String[] noCheckList = {
            "/signin",
            "/logout",
            "/newest",
            "/house/list",
            "house/hot",
            "/house/detail",
            "/agency/detail"
    };

    /**
     * 过滤器的类型
     * PRE_TYPE代表前置过滤器
     *
     * @return
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 过滤器的执行顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    /**
     * 是否去执行过滤器
     * true：是
     * false：否，不执行拦截
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        // 获取请求对象
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        // 获取当前请求的路径
        String requestURI = request.getRequestURI();
        // 判断请求路径是否需要处理
        for (String uri : noCheckList) {
            if (requestURI.contains(uri)) {
                // 不执行鉴权操作
                return false;
            }
        }

        // 执行鉴权操作
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 获取请求对象
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        // 获取头中的token执行
        String token = request.getHeader("token");

        if (token == null) {
            // 不允许请求后端服务器
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        } else {
            // 鉴权验证token是否合法
            String url = "http://user-service/token/verify?token=" + token;
            String json = restTemplate.getForObject(url, String.class);
            Message message = JSONObject.parseObject(json, Message.class);
           /* if ("gerry".equals(message.getData())) {
                // 鉴权成功
                // 允许请求后端服务器
                context.setSendZuulResponse(true);
            } else {
                // 不允许请求后端服务器
                context.setSendZuulResponse(false);
                context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            }*/
        }

        return null;
    }
}
