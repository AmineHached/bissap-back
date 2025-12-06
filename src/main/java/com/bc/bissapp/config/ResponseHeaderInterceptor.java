package com.bc.bissapp.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Interceptor to add project ownership watermark to all API responses
 * This ensures every response carries the developer's signature
 */
@Component
public class ResponseHeaderInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // Add custom header with project ownership information
        response.setHeader("X-Project-Owner", ProjectMetadata.PROJECT_OWNER);
        response.setHeader("X-Project-Name", ProjectMetadata.PROJECT_NAME);
        response.setHeader("X-Powered-By", ProjectMetadata.OWNERSHIP_WATERMARK);
        
        return true;
    }
}
