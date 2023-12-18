package com.rachidj.api_rest.auth;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rachidj.api_rest.config.JwtService;
import com.rachidj.api_rest.dto.AuthenticationRequest;
import com.rachidj.api_rest.dto.AuthenticationResponse;
import com.rachidj.api_rest.dto.RestaurantRequest;
import com.rachidj.api_rest.entities.Restaurant;
import com.rachidj.api_rest.entities.Role;
import com.rachidj.api_rest.repository.RestaurantRepository;
import com.rachidj.api_rest.utils.PasswordUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RestaurantRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var restaurant = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(restaurant);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse register(RestaurantRequest request) {
        var restaurant = Restaurant.builder()
                                    .name(request.name())
                                    .ownerName(request.ownerName())
                                    .foodTypes(request.foodTypes())
                                    .postalcode(request.postalcode())
                                    .address(request.address())
                                    .phone(request.phone())
                                    .email(request.email())
                                    .password(passwordEncoder.encode(request.password()))
                                    .rating(0.0)
                                    .serviceAvailable(false)
                                    .coverImages(List.of())
                                    .foods(List.of())
                                    .role(Role.RESTAURANT)
                                    .build();
        repository.save(restaurant);
        var jwtToken = jwtService.generateToken(restaurant);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
    
}
