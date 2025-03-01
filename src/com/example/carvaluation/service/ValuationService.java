package com.example.carvaluation.service;

import com.example.carvaluation.model.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class ValuationService {

    private final WebDriver driver;
    private static final String VALUATION_URL = "https://car-checking.com/";

    public ValuationService(WebDriver driver) {
        this.driver = driver;
    }

    public Map<String, Car> performValuation(String registration) {
        driver.get(VALUATION_URL);

        WebElement registrationInput = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.id("subForm1")));
        registrationInput.clear();
        registrationInput.sendKeys(registration);

        WebElement submitButton = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.className("check-now-button")));

        submitButton.click();

        WebElement errorElement = null;
        try {
            errorElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-danger")));
        } catch (Exception e) {
            // ignore as the error is not present
        }
        if (errorElement != null) {
            return Map.of();
        }


        Car carDetails = new Car();
        carDetails.setRegistration(registration);
        carDetails.setMake(driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[3]/div/div/div[2]/table/tbody/tr[1]/td[2]")).getText());
        carDetails.setModel(driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[3]/div/div/div[2]/table/tbody/tr[2]/td[2]")).getText());
        carDetails.setYear(driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[3]/div/div/div[2]/table/tbody/tr[4]/td[2]")).getText());
        System.out.println(carDetails);

        return Map.of(registration, carDetails);
    }
}

