**Car Vehicle Registration Details Check

Scenario**

Reads given input file: car_input.txt
Extracts vehicle registration numbers based on pattern(s).
Each number extracted from input file is fed to https://cartaxcheck.co.uk/ (Perform Free CarCheck)
Compare the output returned by https://cartaxcheck.co.uk/ with given car_output.txt

** Prerequisites**

  Java Development Kit (JDK) 11 or higher.
  Maven 3.6 or higher.
  Google Chrome browser.
  ChromeDriver (managed by WebDriverManager).

**  Getting Started**
  
** Clone Repository **

    ```bash
    git clone [your_repository_url]
     ```
** Create Test Data **

    * Create `src/test/resources/testdata/car_input.txt` with vehicle registration numbers (one per line).
    * Create `src/test/resources/testdata/car_output.txt` with the corresponding expected valuation results (one per line).

** Edit Run/Debug Configurations **

    * Modify the properties to match your environment (e.g. Main Class, Glue, Feature folder, Working Directory, Class path,JRE etc).

** Running the Tests **

    ```bash
    mvn clean test
    ```

