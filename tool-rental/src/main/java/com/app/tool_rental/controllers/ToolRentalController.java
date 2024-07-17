package com.app.tool_rental.controllers;

import com.app.tool_rental.exceptions.BadRequestException;
import com.app.tool_rental.models.*;
import com.app.tool_rental.services.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clerk")
public class ToolRentalController {

    @Autowired
    CheckoutService checkoutService;

    @GetMapping("/health-check")
    public ResponseEntity<String> health() {
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/ingest-data")
    public ResponseEntity<String> dataIngestion() {

        checkoutService.addTools(new Tool.Builder().toolCode(String.valueOf(ToolCode.CHNS)).toolType(String.valueOf(ToolType.CHAINSAW)).toolBrand(String.valueOf(Brand.STIHL)).build());
        checkoutService.addTools(new Tool.Builder().toolCode(String.valueOf(ToolCode.LADW)).toolType(String.valueOf(ToolType.LADDER)).toolBrand(String.valueOf(Brand.WERNER)).build());
        checkoutService.addTools(new Tool.Builder().toolCode(String.valueOf(ToolCode.JAKD)).toolType(String.valueOf(ToolType.JACKHAMMER)).toolBrand(String.valueOf(Brand.DEWALT)).build());
        checkoutService.addTools(new Tool.Builder().toolCode(String.valueOf(ToolCode.JAKR)).toolType(String.valueOf(ToolType.JACKHAMMER)).toolBrand(String.valueOf(Brand.RIDGID)).build());

        Charges ladderCharges = new Charges.Builder().dailyCharge(1.99).weekdayCharge(true).weekendCharge(true).holidayCharge(false).build();
        Charges chainsawCharges = new Charges.Builder().dailyCharge(1.49).weekdayCharge(true).weekendCharge(false).holidayCharge(true).build();
        Charges jackHammerCharges = new Charges.Builder().dailyCharge(2.99).weekdayCharge(true).weekendCharge(false).holidayCharge(false).build();

        checkoutService.addChargesForTool(String.valueOf(ToolType.LADDER), ladderCharges);
        checkoutService.addChargesForTool(String.valueOf(ToolType.CHAINSAW), chainsawCharges);
        checkoutService.addChargesForTool(String.valueOf(ToolType.JACKHAMMER), jackHammerCharges);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }


    @PostMapping("/add-tool")
    public ResponseEntity<String> addTool(@RequestBody Tool tool) {
        checkoutService.addTools(tool);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/add-charges/{toolType}")
    public ResponseEntity<String> addCharges(@RequestBody Charges charges, @PathVariable String toolType) {
        checkoutService.addChargesForTool(toolType, charges);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/checkout")
    public ResponseEntity<Response> checkout(@RequestBody ToolRentalRequest ToolRentalRequest) {
        try {
            RentalAgreement rentalAgreement = checkoutService.checkout(ToolRentalRequest);
            System.out.println("--------Rental Agreement-------");
            String printResponse = rentalAgreement.print();
            System.out.println(printResponse);
            Response response = new Response();
            response.setStatus("Success");
            response.setMessage("Checkout successful");
            response.setData(rentalAgreement);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BadRequestException e) {
            Response response = new Response();
            response.setStatus("Fail");
            response.setMessage(e.getMessage());
            response.setData(new RentalAgreement.Builder().build());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
