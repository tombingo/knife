package com.knife.config.filter;

import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.knife.config.Access;
import com.knife.core.AccessContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 入口，生成请求ID
 * @Author geey
 * @Date 2023/8/3 16:32
 * @Version 1.0
 */
@Component
@Log4j2
public class EntryFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /**
         * 请求编号
         */
        String requestId = IdUtil.simpleUUID();
        /**
         * ip地址
         */
        String ip = ServletUtil.getClientIP((HttpServletRequest) servletRequest);
        if(ip.equals("0:0:0:0:0:0:0:1")){
            ip = "127.0.0.1";
        }
        Access access = new Access();
        access.setRequestId(requestId);
        access.setIp(ip);
        AccessContext.setAccessMeta(access);
        filterChain.doFilter(servletRequest, servletResponse);

        /**
         * 及时清除资源
         */
        AccessContext.remove();
    }
}
