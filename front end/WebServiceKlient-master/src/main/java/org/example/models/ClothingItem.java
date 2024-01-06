package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClothingItem {

    private int productId;
    private String productName;
    private double price;
    private String color;
    private String dimension;
    private String specification;
    private String manufacturer;
    private int quantity;
    private int createdBy;
    private String category;
}
