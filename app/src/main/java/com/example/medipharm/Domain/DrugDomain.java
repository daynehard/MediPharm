package com.example.medipharm.Domain;

import java.io.Serializable;

public class DrugDomain implements Serializable {
    private String title;
    private String pic;
    private String description;
    private String howtouse;
    private Double fee;
    private int numberInCart;

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
    public String getHowtouse(){
        return howtouse;
    }
    public void setHowtouse(String howtouse){
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
