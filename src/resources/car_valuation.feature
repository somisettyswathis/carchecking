Feature: Car Valuation

  Scenario: Verify car valuations
    Given the input file "src/resources/car_input.txt"
    And the output file "src/resources/car_output.txt"
    When I perform valuations
    Then the valuations should match the expected outputs