package com.app.tool_rental;

import com.app.tool_rental.models.*;
import com.app.tool_rental.services.CheckoutService;
import com.app.tool_rental.exceptions.BadRequestException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutServiceTest {

    @InjectMocks
    private CheckoutService checkoutService;

    @Mock
    private HashMap<String, Tool> toolsMap;

    @Mock
    private HashMap<String, Charges> chargesMap;

    @Before
    public void setUp() {

        Tool tool = new Tool(ToolCode.CHNS.toString(), ToolType.CHAINSAW.toString(), Brand.STIHL.toString());
        toolsMap.put(ToolCode.CHNS.toString(), tool);
        tool = new Tool(ToolCode.LADW.toString(), ToolType.LADDER.toString(), Brand.WERNER.toString());
        toolsMap.put(ToolCode.LADW.toString(), tool);
        tool = new Tool(ToolCode.JAKD.toString(), ToolType.JACKHAMMER.toString(), Brand.DEWALT.toString());
        toolsMap.put(ToolCode.JAKD.toString(), tool);
        tool = new Tool(ToolCode.JAKR.toString(), ToolType.JACKHAMMER.toString(), Brand.RIDGID.toString());
        toolsMap.put(ToolCode.JAKR.toString(), tool);

        Charges ladderCharges = new Charges.Builder().dailyCharge(1.99).weekdayCharge(true).weekendCharge(true).holidayCharge(false).build();
        Charges chainsawCharges = new Charges.Builder().dailyCharge(1.49).weekdayCharge(true).weekendCharge(false).holidayCharge(true).build();
        Charges jackHammerCharges = new Charges.Builder().dailyCharge(2.99).weekdayCharge(true).weekendCharge(false).holidayCharge(false).build();

        chargesMap.put(ToolType.LADDER.toString(), ladderCharges);
        chargesMap.put(ToolType.CHAINSAW.toString(), chainsawCharges);
        chargesMap.put(ToolType.JACKHAMMER.toString(), jackHammerCharges);
    }

    @Test(expected = BadRequestException.class)
    public void test1() throws BadRequestException {
        ToolRentalRequest request = new ToolRentalRequest(ToolCode.JAKR.toString(), 5, 101, "9/3/15");
        checkoutService.checkout(request);
    }

    @Test
    public void test2() throws BadRequestException {
        ToolRentalRequest request = new ToolRentalRequest(ToolCode.LADW.toString(), 3, 10, "7/2/20");

        Mockito.when(toolsMap.get(ToolCode.LADW.toString())).thenReturn(new Tool(ToolCode.LADW.toString(), ToolType.LADDER.toString(), Brand.WERNER.toString()));
        Mockito.when(chargesMap.get(ToolType.LADDER.toString())).thenReturn(new Charges.Builder().dailyCharge(1.99).weekdayCharge(true).weekendCharge(true).holidayCharge(false).build());

        RentalAgreement rentalAgreement = checkoutService.checkout(request);

        assertEquals(ToolCode.LADW.toString(), rentalAgreement.getToolCode());
        assertEquals(ToolType.LADDER.toString(), rentalAgreement.getToolType());
        assertEquals(Brand.WERNER.toString(), rentalAgreement.getToolBrand());
        assertEquals(3, rentalAgreement.getRentalDays());
        assertEquals("07/02/20", rentalAgreement.getCheckoutDate());
        assertEquals("07/04/20", rentalAgreement.getDueDate());
        assertEquals("$1.99", rentalAgreement.getDailyRentalCharge());
        assertEquals(2, rentalAgreement.getChargeDays());
        assertEquals("$3.98", rentalAgreement.getPreDiscountCharge());
        assertEquals("10%", rentalAgreement.getDiscountPercent());
        assertEquals("$0.40", rentalAgreement.getDiscountAmount());
        assertEquals("$3.58", rentalAgreement.getFinalCharge());
    }

    @Test
    public void test3() throws BadRequestException {
        ToolRentalRequest request = new ToolRentalRequest(ToolCode.CHNS.toString(), 5, 25, "7/2/15");

        Mockito.when(toolsMap.get(ToolCode.CHNS.toString())).thenReturn(new Tool(ToolCode.CHNS.toString(), ToolType.CHAINSAW.toString(), Brand.STIHL.toString()));
        Mockito.when(chargesMap.get(ToolType.CHAINSAW.toString())).thenReturn(new Charges.Builder().dailyCharge(1.49).weekdayCharge(true).weekendCharge(false).holidayCharge(true).build());

        RentalAgreement rentalAgreement = checkoutService.checkout(request);

        assertEquals(ToolCode.CHNS.toString(), rentalAgreement.getToolCode());
        assertEquals(ToolType.CHAINSAW.toString(), rentalAgreement.getToolType());
        assertEquals(Brand.STIHL.toString(), rentalAgreement.getToolBrand());
        assertEquals(5, rentalAgreement.getRentalDays());
        assertEquals("07/02/15", rentalAgreement.getCheckoutDate());
        assertEquals("07/06/15", rentalAgreement.getDueDate());
        assertEquals("$1.49", rentalAgreement.getDailyRentalCharge());
        assertEquals(3, rentalAgreement.getChargeDays());
        assertEquals("$4.47", rentalAgreement.getPreDiscountCharge());
        assertEquals("25%", rentalAgreement.getDiscountPercent());
        assertEquals("$1.12", rentalAgreement.getDiscountAmount());
        assertEquals("$3.35", rentalAgreement.getFinalCharge());
    }


    @Test
    public void test4() throws BadRequestException {
        ToolRentalRequest request = new ToolRentalRequest(ToolCode.JAKD.toString(), 6, 0, "9/3/15");

        Mockito.when(toolsMap.get(ToolCode.JAKD.toString())).thenReturn(new Tool(ToolCode.JAKD.toString(), ToolType.JACKHAMMER.toString(), Brand.DEWALT.toString()));
        Mockito.when(chargesMap.get(ToolType.JACKHAMMER.toString())).thenReturn(new Charges.Builder().dailyCharge(2.99).weekdayCharge(true).weekendCharge(false).holidayCharge(false).build());

        RentalAgreement rentalAgreement = checkoutService.checkout(request);

        assertEquals(ToolCode.JAKD.toString(), rentalAgreement.getToolCode());
        assertEquals(ToolType.JACKHAMMER.toString(), rentalAgreement.getToolType());
        assertEquals(Brand.DEWALT.toString(), rentalAgreement.getToolBrand());
        assertEquals(6, rentalAgreement.getRentalDays());
        assertEquals("09/03/15", rentalAgreement.getCheckoutDate());
        assertEquals("09/08/15", rentalAgreement.getDueDate());
        assertEquals("$2.99", rentalAgreement.getDailyRentalCharge());
        assertEquals(3, rentalAgreement.getChargeDays());
        assertEquals("$8.97", rentalAgreement.getPreDiscountCharge());
        assertEquals("0%", rentalAgreement.getDiscountPercent());
        assertEquals("$0.00", rentalAgreement.getDiscountAmount());
        assertEquals("$8.97", rentalAgreement.getFinalCharge());
    }

    @Test
    public void test5() throws BadRequestException {
        ToolRentalRequest request = new ToolRentalRequest(ToolCode.JAKR.toString(), 9, 0, "7/2/15");

        Mockito.when(toolsMap.get(ToolCode.JAKR.toString())).thenReturn(new Tool(ToolCode.JAKR.toString(), ToolType.JACKHAMMER.toString(), Brand.RIDGID.toString()));
        Mockito.when(chargesMap.get(ToolType.JACKHAMMER.toString())).thenReturn(new Charges.Builder().dailyCharge(2.99).weekdayCharge(true).weekendCharge(false).holidayCharge(false).build());

        RentalAgreement rentalAgreement = checkoutService.checkout(request);

        assertEquals(ToolCode.JAKR.toString(), rentalAgreement.getToolCode());
        assertEquals(ToolType.JACKHAMMER.toString(), rentalAgreement.getToolType());
        assertEquals(Brand.RIDGID.toString(), rentalAgreement.getToolBrand());
        assertEquals(9, rentalAgreement.getRentalDays());
        assertEquals("07/02/15", rentalAgreement.getCheckoutDate());
        assertEquals("07/10/15", rentalAgreement.getDueDate());
        assertEquals("$2.99", rentalAgreement.getDailyRentalCharge());
        assertEquals(6, rentalAgreement.getChargeDays());
        assertEquals("$17.94", rentalAgreement.getPreDiscountCharge());
        assertEquals("0%", rentalAgreement.getDiscountPercent());
        assertEquals("$0.00", rentalAgreement.getDiscountAmount());
        assertEquals("$17.94", rentalAgreement.getFinalCharge());
    }

    @Test
    public void test6() throws BadRequestException {
        ToolRentalRequest request = new ToolRentalRequest(ToolCode.JAKR.toString(), 4, 50, "7/2/20");

        Mockito.when(toolsMap.get(ToolCode.JAKR.toString())).thenReturn(new Tool(ToolCode.JAKR.toString(), ToolType.JACKHAMMER.toString(), Brand.RIDGID.toString()));
        Mockito.when(chargesMap.get(ToolType.JACKHAMMER.toString())).thenReturn(new Charges.Builder().dailyCharge(2.99).weekdayCharge(true).weekendCharge(false).holidayCharge(false).build());

        RentalAgreement rentalAgreement = checkoutService.checkout(request);

        assertEquals(ToolCode.JAKR.toString(), rentalAgreement.getToolCode());
        assertEquals(ToolType.JACKHAMMER.toString(), rentalAgreement.getToolType());
        assertEquals(Brand.RIDGID.toString(), rentalAgreement.getToolBrand());
        assertEquals(4, rentalAgreement.getRentalDays());
        assertEquals("07/02/20", rentalAgreement.getCheckoutDate());
        assertEquals("07/05/20", rentalAgreement.getDueDate());
        assertEquals("$2.99", rentalAgreement.getDailyRentalCharge());
        assertEquals(1, rentalAgreement.getChargeDays());
        assertEquals("$2.99", rentalAgreement.getPreDiscountCharge());
        assertEquals("50%", rentalAgreement.getDiscountPercent());
        assertEquals("$1.50", rentalAgreement.getDiscountAmount());
        assertEquals("$1.50", rentalAgreement.getFinalCharge());
    }

    @Test
    public void test7() throws BadRequestException {
        ToolRentalRequest request = new ToolRentalRequest(ToolCode.JAKR.toString(), 456, 5, "7/2/24");

        Mockito.when(toolsMap.get(ToolCode.JAKR.toString())).thenReturn(new Tool(ToolCode.JAKR.toString(), ToolType.JACKHAMMER.toString(), Brand.RIDGID.toString()));
        Mockito.when(chargesMap.get(ToolType.JACKHAMMER.toString())).thenReturn(new Charges.Builder().dailyCharge(2.99).weekdayCharge(true).weekendCharge(false).holidayCharge(false).build());

        RentalAgreement rentalAgreement = checkoutService.checkout(request);

        assertEquals(ToolCode.JAKR.toString(), rentalAgreement.getToolCode());
        assertEquals(ToolType.JACKHAMMER.toString(), rentalAgreement.getToolType());
        assertEquals(Brand.RIDGID.toString(), rentalAgreement.getToolBrand());
        assertEquals(456, rentalAgreement.getRentalDays());
        assertEquals("07/02/24", rentalAgreement.getCheckoutDate());
        assertEquals("09/30/25", rentalAgreement.getDueDate());
        assertEquals("$2.99", rentalAgreement.getDailyRentalCharge());
        assertEquals(322, rentalAgreement.getChargeDays());
        assertEquals("$962.78", rentalAgreement.getPreDiscountCharge());
        assertEquals("5%", rentalAgreement.getDiscountPercent());
        assertEquals("$48.14", rentalAgreement.getDiscountAmount());
        assertEquals("$914.64", rentalAgreement.getFinalCharge());
    }

    @Test(expected = BadRequestException.class)
    public void test8() throws BadRequestException {
        ToolRentalRequest request = new ToolRentalRequest(ToolCode.JAKR.toString(), 0, 11, "9/3/15");
        checkoutService.checkout(request);
    }

}
