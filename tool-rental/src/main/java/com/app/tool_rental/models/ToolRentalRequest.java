package com.app.tool_rental.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ToolRentalRequest {

    @JsonProperty("toolCode")
    private final String toolCode;

    @JsonProperty("rentalDayCount")
    private final int rentalDayCount;

    @JsonProperty("discountPercent")
    private final int discountPercent;

    @JsonProperty("checkoutDate")
    private final String checkoutDate;

    private ToolRentalRequest(Builder builder) {
        this.toolCode = builder.toolCode;
        this.rentalDayCount = builder.rentalDayCount;
        this.discountPercent = builder.discountPercent;
        this.checkoutDate = builder.checkoutDate;
    }

    public String getToolCode() {
        return toolCode;
    }

    public int getRentalDayCount() {
        return rentalDayCount;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    @Override
    public String toString() {
        return "ToolRentalInfo{" +
                "toolCode='" + toolCode + '\'' +
                ", rentalDayCount=" + rentalDayCount +
                ", discountPercent=" + discountPercent +
                ", checkoutDate='" + checkoutDate + '\'' +
                '}';
    }

    public static class Builder {
        private String toolCode;
        private int rentalDayCount;
        private int discountPercent;
        private String checkoutDate;

        public Builder toolCode(String toolCode) {
            this.toolCode = toolCode;
            return this;
        }

        public Builder rentalDayCount(int rentalDayCount) {
            this.rentalDayCount = rentalDayCount;
            return this;
        }

        public Builder discountPercent(int discountPercent) {
            this.discountPercent = discountPercent;
            return this;
        }

        public Builder checkoutDate(String checkoutDate) {
            this.checkoutDate = checkoutDate;
            return this;
        }

        public ToolRentalRequest build() {
            return new ToolRentalRequest(this);
        }
    }

    public String print() {
        return "Tool Code: " + toolCode + "\n" +
                "Checkout Date: " + checkoutDate + "\n" +
                "Rental Days: " + rentalDayCount + "\n" +
                "Discount: " + discountPercent + "\n";
    }
}
