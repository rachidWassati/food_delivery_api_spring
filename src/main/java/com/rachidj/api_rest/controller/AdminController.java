package com.rachidj.api_rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rachidj.api_rest.dto.RestaurantRequest;
import com.rachidj.api_rest.entities.Restaurant;
import com.rachidj.api_rest.entities.Role;
import com.rachidj.api_rest.repository.RestaurantRepository;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping(path = "api/v1/admin/restaurant")
@AllArgsConstructor
public class AdminController {

    private final RestaurantRepository restaurantRepository;
    private final PasswordEncoder passwordEncoder;
    
    @GetMapping("/")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();

        if (restaurants.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(restaurants);
        }
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody RestaurantRequest request) {

        Restaurant restaurant = Restaurant.builder()
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
                    
        restaurantRepository.save(restaurant);

        return ResponseEntity.ok(restaurant);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getRestaurantById(@PathVariable Integer id) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);

        if(restaurantOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();
            return ResponseEntity.ok(restaurant);
        }

        HashMap<String, String> message = new HashMap<>();
        message.put("message", "No restaurant found with ID: " + id);


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
    
}
