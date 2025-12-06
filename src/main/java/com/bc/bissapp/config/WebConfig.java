package com.bc.bissapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ResponseHeaderInterceptor responseHeaderInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Development-friendly: adjust origins for your front-end host in production
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "http://localhost:4200", "http://localhost:5173", "*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Add interceptor to all requests to include ownership headers
        registry.addInterceptor(responseHeaderInterceptor)
                .addPathPatterns("/**");
    }
}
