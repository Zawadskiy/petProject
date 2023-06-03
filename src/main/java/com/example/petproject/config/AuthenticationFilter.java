package com.example.petproject.config;


import com.example.petproject.dto.request.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthenticationFilter implements Filter {

    private final AuthenticationManager authenticationManager;

    private final ObjectMapper objectMapper;

    private final String url = "/auth/signin";

    @Autowired
    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                ObjectMapper objectMapper) {
        this.authenticationManager = authenticationManager;
        this.objectMapper = objectMapper;
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getRequestURI().equals(url)) {

            CachedBodyHttpServletRequest cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest(req);
            String json = cachedBodyHttpServletRequest.getReader().lines().reduce(String::concat).orElse(StringUtils.EMPTY);

            LoginRequest loginRequest = objectMapper.readValue(json, LoginRequest.class);

            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);

            HttpSession session = req.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", context);
        }

        chain.doFilter(request, response);
    }
}
