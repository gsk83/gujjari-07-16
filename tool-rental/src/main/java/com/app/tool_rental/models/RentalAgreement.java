package com.app.tool_rental.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentalAgreement {

    @JsonProperty("toolCode")
    private final String toolCode;

    @JsonProperty("toolType")
    private final String toolType;

    @JsonProperty("toolBrand")
    private final String toolBrand;

    @JsonProperty("rentalDays")
    private final int rentalDays;

    @JsonProperty("checkoutDate")
    private final String checkoutDate;

    @JsonProperty("dueDate")
    private final String dueDate;

    @JsonProperty("dailyRentalCharge")
    private final String dailyRentalCharge;

    @JsonProperty("chargeDays")
    private final int chargeDays;

    @JsonProperty("preDiscountCharge")
    private final String preDiscountCharge;

    @JsonProperty("discountPercent")
    private final String discountPercent;

    @JsonProperty("discountAmount")
    private final String discountAmount;

    @JsonProperty("finalCharge")
    private final String finalCharge;

    public RentalAgreement(Builder builder) {
        this.toolCode = builder.toolCode;
        this.toolType = builder.toolType;
        this.toolBrand = builder.toolBrand;
        this.rentalDays = builder.rentalDays;
        this.checkoutDate = builder.checkoutDate;
        this.dueDate = builder.dueDate;
        this.dailyRentalCharge = builder.dailyRentalCharge;
        this.chargeDays = builder.chargeDays;
        this.preDiscountCharge = builder.preDiscountCharge;
        this.discountPercent = builder.discountPercent;
        this.discountAmount = builder.discountAmount;
        this.finalCharge = builder.finalCharge;
    }

    public String getToolCode() {
        return toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public String getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public String getDiscountPercent() {
        return discountPercent;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public String getFinalCharge() {
        return finalCharge;
    }

    public static class Builder {
        private String toolCode;
        private String toolType;
        private String toolBrand;
        private int rentalDays;
        private String checkoutDate;

        private String dueDate;
        private String dailyRentalCharge;
        private int chargeDays;
        private String preDiscountCharge;
        private String discountPercent;
        private String discountAmount;
        private String finalCharge;

        public Builder toolCode(String toolCode) {
            this.toolCode = toolCode;
            return this;
        }

        public Builder toolType(String toolType) {
            this.toolType = toolType;
            return this;
        }

        public Builder toolBrand(String toolBrand) {
            this.toolBrand = toolBrand;
            return this;
        }

        public Builder rentalDays(int rentalDays) {
            this.rentalDays = rentalDays;
            return this;
        }

        public Builder checkoutDate(String checkoutDate) {
            this.checkoutDate = checkoutDate;
            return this;
        }

        public Builder dueDate(String dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder dailyRentalCharge(String dailyRentalCharge) {
            this.dailyRentalCharge = dailyRentalCharge;
            return this;
        }

        public Builder chargeDays(int chargeDays) {
            this.chargeDays = chargeDays;
            return this;
        }

        public Builder preDiscountCharge(String preDiscountCharge) {
            this.preDiscountCharge = preDiscountCharge;
            return this;
        }

        public Builder discountPercent(String discountPercent) {
            this.discountPercent = discountPercent;
            return this;
        }

        public Builder discountAmount(String discountAmount) {
            this.discountAmount = discountAmount;
            return this;
        }

        public Builder finalCharge(String finalCharge) {
            this.finalCharge = finalCharge;
            return this;
        }

        public RentalAgreement build() {
            return new RentalAgreement(this);
        }
    }

    public String print() {

        StringBuilder sb = new StringBuilder();
        sb.append("Tool Code: ").append(toolCode).append("\n");
        sb.append("Tool Type: ").append(toolType).append("\n");
        sb.append("Tool Brand: ").append(toolBrand).append("\n");
        sb.append("Rental Days: ").append(rentalDays).append("\n");
        sb.append("Check Out Date: ").append(checkoutDate).append("\n");
        sb.append("Due Date: ").append(dueDate).append("\n");
        sb.append("Daily Rental Charge: ").append(dailyRentalCharge).append("\n");
        sb.append("Charge Days: ").append(chargeDays).append("\n");
        sb.append("Pre-Discount Charge: ").append(preDiscountCharge).append("\n");
        sb.append("Discount Percent: ").append(discountPercent).append("\n");
        sb.append("Discount Amount: ").append(discountAmount).append("\n");
        sb.append("Final Charge: ").append(finalCharge).append("\n");

        return sb.toString();
    }
}
