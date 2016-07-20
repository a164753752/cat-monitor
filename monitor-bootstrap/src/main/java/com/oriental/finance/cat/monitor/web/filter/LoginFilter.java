package com.oriental.finance.cat.monitor.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 描述：登录过滤器
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/18 ProjectName:cat-monitor Version: 1.0
 */
@Slf4j
public class LoginFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        super.doFilter(request,response,filterChain);
    }
}
