package com.example.demo.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("unused")
@Component
public class JwtAuthenticationFilter implements Filter {

    // This method extracts the JWT token from the HTTP request
    @Override
    public void doFilter(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Extract token from Authorization header
        String token = httpRequest.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove "Bearer " prefix
        }

        if (token != null && validateToken(token)) {
            Authentication auth = getAuthentication(token);
            // Set the authentication context
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        chain.doFilter(request, response); // Proceed with the filter chain
    }

    // Sample method to validate JWT token (you can implement this using a JWT
    // library like jjwt or java-jwt)
    private boolean validateToken(String token) {
        // Logic to validate the JWT (like checking signature, expiration, etc.)
        // For now, just a placeholder
        return true; // Replace with actual validation logic
    }

    // Method to extract Authentication object based on the JWT
    private Authentication getAuthentication(String token) {
        // Use the token to extract user details and create an Authentication object
        // For simplicity, this is just a placeholder
        return null; // Replace with actual authentication logic (e.g.,
                     // UsernamePasswordAuthenticationToken)
    }
}
