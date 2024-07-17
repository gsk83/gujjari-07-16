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
public class Charges {

    @JsonProperty("dailyCharge")
    private final double dailyCharge;

    @JsonProperty("weekdayCharge")
    private final boolean weekdayCharge;

    @JsonProperty("weekendCharge")
    private final boolean weekendCharge;

    @JsonProperty("holidayCharge")
    private final boolean holidayCharge;

    private Charges(Builder builder) {
        this.dailyCharge = builder.dailyCharge;
        this.weekdayCharge = builder.weekdayCharge;
        this.weekendCharge = builder.weekendCharge;
        this.holidayCharge = builder.holidayCharge;
    }

    public double getDailyCharge() {
        return dailyCharge;
    }

    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "dailyCharge=" + dailyCharge +
                ", weekdayCharge=" + weekdayCharge +
                ", weekendCharge=" + weekendCharge +
                ", holidayCharge=" + holidayCharge +
                '}';
    }

    public static class Builder {
        private double dailyCharge;
        private boolean weekdayCharge;
        private boolean weekendCharge;
        private boolean holidayCharge;

        public Builder dailyCharge(double dailyCharge) {
            this.dailyCharge = dailyCharge;
            return this;
        }

        public Builder weekdayCharge(boolean weekdayCharge) {
            this.weekdayCharge = weekdayCharge;
            return this;
        }

        public Builder weekendCharge(boolean weekendCharge) {
            this.weekendCharge = weekendCharge;
            return this;
        }

        public Builder holidayCharge(boolean holidayCharge) {
            this.holidayCharge = holidayCharge;
            return this;
        }

        public Charges build() {
            return new Charges(this);
        }
    }
}
