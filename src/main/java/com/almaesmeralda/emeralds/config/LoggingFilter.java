package com.almaesmeralda.emeralds.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);

        try {
            filterChain.doFilter(wrappedRequest, response);
        } catch (Exception ex) {
            logger.error("❌ Exception in filter: {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            logRequest(wrappedRequest);
        }
    }

    private void logRequest(ContentCachingRequestWrapper request) {
        logger.info("📥 Request: {} {}", request.getMethod(), request.getRequestURI());

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            logger.info("🔸 Header: {} = {}", header, request.getHeader(header));
        }

        byte[] body = request.getContentAsByteArray();
        if (body.length > 0) {
            String bodyString = new String(body, StandardCharsets.UTF_8);
            logger.info("📦 Body: {}", bodyString);
        } else {
            logger.info("📦 Body: [empty]");
        }
    }
}
