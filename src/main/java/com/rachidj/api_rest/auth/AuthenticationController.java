package com.rachidj.api_rest.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rachidj.api_rest.dto.AuthenticationRequest;
import com.rachidj.api_rest.dto.AuthenticationResponse;
import com.rachidj.api_rest.dto.RestaurantRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    
    @PostMapping("/restaurant/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RestaurantRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/restaurant/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

}
