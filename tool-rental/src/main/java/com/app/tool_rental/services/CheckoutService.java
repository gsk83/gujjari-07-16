package com.app.tool_rental.services;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashMap;

import com.app.tool_rental.models.*;
import com.app.tool_rental.utils.*;
import com.app.tool_rental.exceptions.*;
import org.springframework.stereotype.Service;


@Service
public class CheckoutService {

    HashMap<String, Tool> toolsMap = new HashMap<>();

    HashMap<String, Charges> chargesMap = new HashMap<>();

    public RentalAgreement checkout(ToolRentalRequest toolRentalRequest) throws BadRequestException {
        ValidationUtils.inputValidations(toolRentalRequest);
        String toolCode = toolRentalRequest.getToolCode();
        if (toolsMap.isEmpty() || chargesMap.isEmpty()) return new RentalAgreement.Builder().build();
        String toolType = toolsMap.get(toolCode).getToolType();
        String toolBrand = toolsMap.get(toolCode).getToolBrand();
        int rentalDays = toolRentalRequest.getRentalDayCount();

        LocalDate checkoutDate = ConversionUtils.from(toolRentalRequest.getCheckoutDate());
        LocalDate dueDate = checkoutDate.plusDays(rentalDays - 1);
        int weekends = CommonUtils.countWeekends(checkoutDate, dueDate);
        int holidays = CommonUtils.countHolidays(checkoutDate, dueDate);
        int weekdays = rentalDays - (weekends + holidays);

        double dailyRentalCharges = chargesMap.get(toolType).getDailyCharge();
        int chargeDays = (chargesMap.get(toolType).isWeekdayCharge() ? weekdays : 0) + (chargesMap.get(toolType).isWeekendCharge() ? weekends : 0) + (chargesMap.get(toolType).isHolidayCharge() ? holidays : 0);
        double preDiscountCharge = chargeDays * dailyRentalCharges;
        double discountPercentage = (toolRentalRequest.getDiscountPercent());
        double discountAmount = (preDiscountCharge / 100) * discountPercentage;
        double finalCharge = preDiscountCharge - discountAmount;

        DecimalFormat currencyFormat = new DecimalFormat("$#,##0.00");
        DecimalFormat percentFormat = new DecimalFormat("##%");

        return new RentalAgreement.Builder()
                .toolCode(toolCode)
                .toolType(toolType)
                .toolBrand(toolBrand)
                .rentalDays(rentalDays)
                .checkoutDate(ConversionUtils.formatDate(checkoutDate))
                .dueDate(ConversionUtils.formatDate(dueDate))
                .dailyRentalCharge(currencyFormat.format(dailyRentalCharges))
                .chargeDays(chargeDays)
                .preDiscountCharge(currencyFormat.format(Math.round(preDiscountCharge * 100.0) / 100.0))
                .discountPercent(percentFormat.format(discountPercentage / 100))
                .discountAmount(currencyFormat.format(Math.round(discountAmount * 100.0) / 100.0))
                .finalCharge(currencyFormat.format(finalCharge))
                .build();
    }

    public void addTools(Tool tool) {
        toolsMap.put(tool.getToolCode(), tool);
    }

    public void addChargesForTool(String toolType, Charges charges) {
        chargesMap.put(toolType, charges);
    }
}
