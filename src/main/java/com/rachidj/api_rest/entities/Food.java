package com.rachidj.api_rest.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Food {
    @Id
    @SequenceGenerator(name = "food_id_sequence", sequenceName = "food_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_id_sequence")
    private Integer id;

    private String name;
    private String description;
    private String category;
    private String foodType;
    private Integer readyTime;
    private Float price;
    private Float rating;
    private List<String> images;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}
