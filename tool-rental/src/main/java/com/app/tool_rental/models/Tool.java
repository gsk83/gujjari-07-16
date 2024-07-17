package com.app.tool_rental.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tool {

    @JsonProperty("toolCode")
    private final String toolCode;

    @JsonProperty("toolType")
    private final String toolType;

    @JsonProperty("toolBrand")
    private final String toolBrand;

    private Tool(Builder builder) {
        this.toolCode = builder.toolCode;
        this.toolType = builder.toolType;
        this.toolBrand = builder.toolBrand;
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

    @Override
    public String toString() {
        return "Tool{" +
                "toolCode='" + toolCode + '\'' +
                ", toolType='" + toolType + '\'' +
                ", toolBrand='" + toolBrand + '\'' +
                '}';
    }

    public static class Builder {
        private String toolCode;
        private String toolType;
        private String toolBrand;

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

        public Tool build() {
            return new Tool(this);
        }
    }
}
