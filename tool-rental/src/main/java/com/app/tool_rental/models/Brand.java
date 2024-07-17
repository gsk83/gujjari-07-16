package com.app.tool_rental.models;

public enum Brand {
    STIHL("Stihl"),
    WERNER("Werner"),
    DEWALT("DeWalt"),
    RIDGID("Ridgid");

    private final String brand;

    Brand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return this.brand;
    }
}
