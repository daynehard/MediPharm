package com.example.medipharm.Domain;

public class Address {
    private String country;
    private String county;
    private String constituency;

    // Required empty constructor for Firebase
    public Address() {}

    public Address(String country, String county, String constituency) {
        this.country = country;
        this.county = county;
        this.constituency = constituency;
    }

    // Getter methods...
    public String getCountry() {
        return country;
    }

    public String getCounty() {
        return county;
    }

    public String getConstituency() {
        return constituency;
    }
}
