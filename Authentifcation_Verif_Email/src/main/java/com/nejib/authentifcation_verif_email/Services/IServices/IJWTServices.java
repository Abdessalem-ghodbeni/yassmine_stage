package com.nejib.authentifcation_verif_email.Services.IServices;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface IJWTServices {
    String extractUsername(String token);
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
    String generateRefreshToken(Map<String,Object> extraClaims, UserDetails userDetails);
}
