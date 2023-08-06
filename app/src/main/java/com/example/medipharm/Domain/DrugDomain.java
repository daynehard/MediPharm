package com.example.medipharm.Domain;

import java.io.Serializable;
import java.util.UUID;

public class DrugDomain implements Serializable {
    private String title;
    private String itemId;
    private String pic;
    private String description;
    private String howtouse;
    private Double fee;
    private int numberInCart;

    // Add a no-argument constructor
    public DrugDomain() {
        // Required empty constructor for Firebase
    }

    public DrugDomain(String title, String pic, String description, Double fee, String howtouse) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.howtouse = howtouse;
        this.fee = fee;
    }

    public DrugDomain(String title, String pic, String description, String howtouse, Double fee, int numberInCart) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.howtouse = howtouse;
        this.fee = fee;
        this.numberInCart = numberInCart;
    }
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    // Method to generate a unique identifier
    private String generateItemId() {
        // Implement your logic to generate a unique identifier here
        // For example, you can use UUID to generate a random unique identifier
        return UUID.randomUUID().toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHowtouse() {
        return howtouse;
    }

    public void setHowtouse(String howtouse) {
        this.howtouse = howtouse;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
