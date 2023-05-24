package com.eazybytes.loans.controller;

import com.eazybytes.loans.config.LoansServiceConfig;
import com.eazybytes.loans.model.Customer;
import com.eazybytes.loans.model.Loans;
import com.eazybytes.loans.model.Properties;
import com.eazybytes.loans.repository.LoansRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoansController {

    private static final Logger logger = LoggerFactory.getLogger(LoansController.class);

    @Autowired
    private LoansRepository loansRepository;

    @Autowired
    LoansServiceConfig loansConfig;

    @PostMapping("/myLoans")
    public List<Loans> getLoansDetails(@RequestHeader("eazybank-correlation-id") String correlationid, @RequestBody Customer customer) {
        // Resilience4j retry
//        System.out.println("Invoking Loans Microservices");

        logger.info("getLoansDetails() method started");
        List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
        logger.info("getLoansDetails() method ended");
        if (loans != null) {
            return loans;
        } else {
            return null;
        }
    }

    @GetMapping("/loans/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(loansConfig.getMsg(), loansConfig.getBuildVersion(),
                loansConfig.getMailDetails(), loansConfig.getActiveBranches());
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }

}


