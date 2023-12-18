package com.rachidj.api_rest.dto;

import java.util.List;

public record RestaurantRequest(
    String name,
    String ownerName,
    List<String> foodTypes,
    String postalcode,
    String address,
    String phone,
    String email,
    String password
) {}
