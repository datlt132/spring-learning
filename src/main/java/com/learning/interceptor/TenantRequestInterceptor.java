package com.learning.interceptor;

import com.learning.config.tenant.TenantContext;
import com.learning.service.TenantDomain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Component
@AllArgsConstructor
public class TenantRequestInterceptor implements AsyncHandlerInterceptor {
    private final TenantDomain tenantDomain;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return Optional.of(request)
                .map(this::getTenantIdFromHeader)
                .map(tenantDomain::setTenantInContext)
                .orElse(false);
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) {
        TenantContext.clear();
    }

    private String getTenantIdFromHeader(HttpServletRequest request) {
        return request.getHeader("tenantId");
    }

}
