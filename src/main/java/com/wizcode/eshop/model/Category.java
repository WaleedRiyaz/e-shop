package com.wizcode.eshop.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Database generates the ID using auto-incrementing Primary Key.
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    private List<Product> productList;

    public Category(String name) {
        this.name = name;
    }
}
