package com.knife.config;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;
import com.knife.config.interceptor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * @Description: TODO
 * @Author geey
 * @Date 2023/8/2 14:08
 * @Version 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private RequestLogIntercept requestLogIntercept;
    private IpBlacklistIntercept ipBlacklistIntercept;
    private AuthenticateIntercept authenticateIntercept;
    private UserBlacklistIntercept userBlacklistIntercept;
    private VerifyPermissionIntercept verifyPermissionIntercept;
    private QpsIntercept qpsIntercept;
    @Autowired
    public WebMvcConfig(RequestLogIntercept requestLogIntercept, IpBlacklistIntercept ipBlacklistIntercept, AuthenticateIntercept authenticateIntercept, UserBlacklistIntercept userBlacklistIntercept, VerifyPermissionIntercept verifyPermissionIntercept, QpsIntercept qpsIntercept) {
        this.requestLogIntercept = requestLogIntercept;
        this.ipBlacklistIntercept = ipBlacklistIntercept;
        this.authenticateIntercept = authenticateIntercept;
        this.userBlacklistIntercept = userBlacklistIntercept;
        this.verifyPermissionIntercept = verifyPermissionIntercept;
        this.qpsIntercept = qpsIntercept;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        //自定义配置...
        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        config.setReaderFeatures(JSONReader.Feature.FieldBased, JSONReader.Feature.SupportArrayToBean);
        config.setWriterFeatures(JSONWriter.Feature.WriteMapNullValue, JSONWriter.Feature.PrettyFormat);
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        converters.add(0, converter);
    }

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        int order = 0;
        registry.addInterceptor(requestLogIntercept)
                .addPathPatterns("/**")
                .order(order);

        registry.addInterceptor(qpsIntercept)
                .addPathPatterns("/**")
                .order(++order);

        registry.addInterceptor(ipBlacklistIntercept)
                .addPathPatterns("/**")
                .order(++order);

        registry.addInterceptor(authenticateIntercept)
                .addPathPatterns("/multiPhone/business/**")
                .order(++order);

        registry.addInterceptor(userBlacklistIntercept)
                .addPathPatterns("/multiPhone/business/**")
                .order(++order);

        registry.addInterceptor(verifyPermissionIntercept)
                .addPathPatterns("/multiPhone/business/**")
                .order(++order);

        WebMvcConfigurer.super.addInterceptors(registry);
    }


}