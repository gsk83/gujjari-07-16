package com.app.tool_rental.utils;

import com.app.tool_rental.exceptions.BadRequestException;
import com.app.tool_rental.models.ToolRentalRequest;

public class ValidationUtils {

    public static void inputValidations(ToolRentalRequest toolRentalRequest) throws BadRequestException {
        if (toolRentalRequest.getRentalDayCount() < 1) {
            throw new BadRequestException("Rental day count should not be less than 1.");
        }

        if(toolRentalRequest.getDiscountPercent() < 0 || toolRentalRequest.getDiscountPercent() > 100) {
            throw new BadRequestException("Discount percent must be in the range from 0 to 100");
        }
    }
}
