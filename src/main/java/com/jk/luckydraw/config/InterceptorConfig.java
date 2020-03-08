package com.jk.luckydraw.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")
                .addPathPatterns("/lucky/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/admin/toLoginPage")
                .excludePathPatterns("/lucky/reg")
                .excludePathPatterns("/lucky/baoming")
                .excludePathPatterns("/lucky/saveBaoMingUser")
                .excludePathPatterns("/lucky/save")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/jkjw/**");
        registry.addInterceptor(new SysInterceptor())
                .addPathPatterns("/jkjw/**")
                .excludePathPatterns("/jkjw/login")
                .excludePathPatterns("/jkjw/upload")
                .excludePathPatterns("/jkjw/studentLogin")
                .excludePathPatterns("/jkjw/sendStudentLoginCode");

    }
}
