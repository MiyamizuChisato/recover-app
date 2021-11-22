package com.zxc.find.recover.config;

import com.zxc.find.recover.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Miya
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/error")
                .excludePathPatterns("/find/get/**")
                .excludePathPatterns("/lost/get/**")
                .excludePathPatterns("/user/**");
    }
}
