package org.vic.backend_libros.config.security.Jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.vic.backend_libros.services.JwtService;

import java.io.IOException;

@Component // Marks this class as a Spring component
@RequiredArgsConstructor // Lombok's annotation to generate a constructor requiring all final fields
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService; // Service to handle JWT operations
    private final UserDetailsService userDetailsService; // Service to handle user details

    @Override
    // This method is called for every request to check if the user is authenticated
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Extract the token from the request
        final String token = getTokenFromRequest(request);
        final String correo;

        // If there's no token, continue with the request
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extract the email from the token
        correo = jwtService.getCorreoFromToken(token);

        // If the email is not null and there's no authentication in the security context
        if (correo != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Load the user details
            UserDetails userDetails = userDetailsService.loadUserByUsername(correo);
            // If the token is valid
            if (jwtService.isTokenValid(token, userDetails)) {
                // Create an authentication token
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                // Set the details of the authentication token
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set the authentication in the security context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }

        // Continue with the request
        filterChain.doFilter(request, response);

    }

    // This method extracts the token from the request
    private String getTokenFromRequest(HttpServletRequest request) {
        // Get the authorization header
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // If the header is not null and starts with "Bearer ", return the token
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}