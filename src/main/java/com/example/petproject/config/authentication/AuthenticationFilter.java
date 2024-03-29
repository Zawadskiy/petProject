package com.example.petproject.config.authentication;


import com.example.petproject.dto.request.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class AuthenticationFilter extends GenericFilterBean {

    private final static String SPRING_SECURITY_CONTEXT =  "SPRING_SECURITY_CONTEXT";

    private final AuthenticationManager authenticationManager;

    private final ObjectMapper objectMapper;

    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                ObjectMapper objectMapper) {
        this.authenticationManager = authenticationManager;
        this.objectMapper = objectMapper;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

            CachedBodyHttpServletRequest cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest(req);
            String json = cachedBodyHttpServletRequest.getReader()
                    .lines()
                    .reduce(String::concat)
                    .orElse(StringUtils.EMPTY);

            LoginRequest loginRequest = objectMapper.readValue(json, LoginRequest.class);

            Authentication authentication = authenticationManager
//                    todo а есть ли смысл пароль сетать? Продолжая мысль, в первый параметр - principal,
//                     можно всунуть не только юзернейм, а и другую инфу по юзеру,
//                     которая может быть полезна в секьюрити контексте. Скажем, его почта,
//                     если она почему-то часто нужна в логике. Тогда мы в рантайме всегда сможем получить и логин,
//                     и почту, не залазя в бд
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);

            // TODO: 22.06.2023 просто на потыкать. Можно сессии в каком-нить редисе хранить.
            //  Прикольно поразбираться с этим
            HttpSession session = req.getSession(true);
            session.setAttribute(SPRING_SECURITY_CONTEXT, context);

        chain.doFilter(request, response);
    }
}
