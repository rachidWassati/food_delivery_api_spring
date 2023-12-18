package com.rachidj.api_rest.controller;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rachidj.api_rest.entities.Restaurant;
import com.rachidj.api_rest.repository.RestaurantRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantRepository repository;

    @GetMapping("/profile")
    public ResponseEntity<?> showProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<Restaurant> restaurantOptional = repository.findByEmail(email);
        
        if(restaurantOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();
            return ResponseEntity.ok(restaurant);
        }

        HashMap<String, String> message = new HashMap<>();
        message.put("message", "No Profile Found");


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    // @PatchMapping("/profile")
    // public ResponseEntity<Restaurant> updateProfile() {

    // }

    // @PostMapping("/add-food")
    // public ResponseEntity<Restaurant> addFood() {

    // }

    // @GetMapping("/foods")
    // public ResponseEntity<Restaurant> getFoods() {

    // }

    // @PatchMapping("/cover-image")
    // public ResponseEntity<Restaurant> updateCoverImage() {

    // }

}
