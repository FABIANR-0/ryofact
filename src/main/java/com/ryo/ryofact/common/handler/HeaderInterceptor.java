package com.ryo.ryofact.common.handler;

import com.ryo.ryofact.common.exception_handler.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class HeaderInterceptor implements HandlerInterceptor {

    @Value("${settings.client-secret-key}")
    private String secretAuthorization;

    private static final String HEADER_NAME = "Authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String secretHeader = request.getHeader(HEADER_NAME);

        if (secretHeader == null || !secretHeader.equals(secretAuthorization)) {
            throw new UnauthorizedException("Unauthorized: invalid or missing header");
        }

        return true;
    }
}
