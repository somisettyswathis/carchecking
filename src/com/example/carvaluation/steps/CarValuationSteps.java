package com.example.carvaluation.steps;

import com.example.carvaluation.model.Car;
import com.example.carvaluation.service.RegistrationExtractor;
import com.example.carvaluation.service.ValuationService;
import com.example.carvaluation.util.FileReaderUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarValuationSteps {

    private WebDriver driver;
    private RegistrationExtractor registrationExtractor;
    private ValuationService valuationService;
    private List<String> registrationNumbers;
    private HashMap<String, Car> expectedOutputs;
    private HashMap<String, Car> actualOutputs;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        registrationExtractor = new RegistrationExtractor();
        valuationService = new ValuationService(driver);
        actualOutputs = new HashMap<>();
        expectedOutputs = new HashMap<>();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the input file {string}")
    public void the_input_file(String inputFile) throws IOException {
        registrationNumbers = registrationExtractor.extractRegistrationNumbers(inputFile);
    }

    @Given("the output file {string}")
    public void the_output_file(String outputFile) throws IOException {
        List<String[]> records = FileReaderUtil.readCSVFile(outputFile);
        for (String[] record : records) {
            expectedOutputs.put(record[0], new Car(record[0], record[1], record[2], record[3]));
        }
    }

    @When("I perform valuations")
    public void i_perform_valuations() {
        for (String registration : registrationNumbers) {
            actualOutputs.putAll(valuationService.performValuation(registration));
        }
    }

    @Then("the valuations should match the expected outputs")
    public void the_valuations_should_match_the_expected_outputs() {
        Assertions.assertEquals(expectedOutputs.size(), actualOutputs.size(), "Mismatch in number of outputs");
        // 2. Iterate through the expected outputs
        for (Map.Entry<String, Car> entry : expectedOutputs.entrySet()) {
            String registration = entry.getKey();
            Car expectedCar = entry.getValue();

            // 3. Check if the actual outputs contain the same registration
            Assertions.assertTrue(actualOutputs.containsKey(registration), "Registration not found in actual outputs: " + registration);

            // 4. Get the actual car for the registration
            Car actualCar = actualOutputs.get(registration);

            // 5. Compare the expected and actual cars
            Assertions.assertEquals(expectedCar, actualCar, "Mismatch for registration: " + registration);
        }
    }
}
