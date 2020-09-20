package com.example.bookstoreapi.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "Name is empty")
    private String name;

    @NotNull(message = "Price is empty")
    @DecimalMin(value = "0.0", message = "Price can't be negative")
    private float price;

}
