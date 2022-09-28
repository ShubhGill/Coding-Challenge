/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;


import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@SpringBootApplication
@RestController
public class BasicController {
    @Autowired private Environment environment;
 
    @GetMapping(
        "/currency-exchange-sample/fromCurrency/{fromCurrency}/toCurrency/{toCurrency}/amount={amount}")
    // where {fromCurrency} and {toCurrency} are path
    // variable
    // fromCurrency can be USD,EUR,AUD,INR and toCurrency
    // can be the opposite of any fromCurrency
    public ExchangeValue
    retrieveExchangeValue(@PathVariable String fromCurrency,
                          @PathVariable String toCurrency,@PathVariable String amount)
    {
        // Here we need to write all of our business logic
        BigDecimal conversionMultiple = null;
        ExchangeValue exchangeValue = new ExchangeValue();
        if (fromCurrency != null && toCurrency != null) {
            if (fromCurrency.equalsIgnoreCase("USD")
                && toCurrency.equalsIgnoreCase("INR")) {
                conversionMultiple = BigDecimal.valueOf(78);
            }
            if (fromCurrency.equalsIgnoreCase("INR")
                && toCurrency.equalsIgnoreCase("USD")) {
                conversionMultiple
                    = BigDecimal.valueOf(0.013);
            }
            if (fromCurrency.equalsIgnoreCase("EUR")
                && toCurrency.equalsIgnoreCase("INR")) {
                conversionMultiple = BigDecimal.valueOf(82);
            }
            if (fromCurrency.equalsIgnoreCase("AUD")
                && toCurrency.equalsIgnoreCase("INR")) {
                conversionMultiple = BigDecimal.valueOf(54);
            }
        }
        // setting the port
        exchangeValue = new ExchangeValue(
            1000L, fromCurrency, toCurrency,
            conversionMultiple,amount);
        exchangeValue.setPort(Integer.parseInt(
            environment.getProperty("local.server.port")));
        return exchangeValue;
    }
}
