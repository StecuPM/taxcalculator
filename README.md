# taxcalculator
TaxCalculator - a kata for a Clean Code exercise

## Fixed compilation errors and improved naming

### Fixed compilation errors:

-duplicate declaration of advanceTaxPaidadvanceTax

-Renamed to proper variables advanceTax and advanceTaxPaid

-Fixed type mistake: soc_sick_secur  to socialSickSecurity

-Removed unused variables

-Removed incorrectly declared variables

### variable naming changed to camelCase:

soc_security to socialSecurity

soc_health_security to socialHealthSecurity

soc_sick_security to socialSickSecurity

soc_health1 to healthInsurance9

soc_health2 to healthInsurance775

advanceTaxPaidadvanceTax to advanceTaxPaid

advanceTaxPaid0 to advanceTaxPaidRounded

### Better method and formatter names:

df - formatNoDecimals

df00 - formatTwoDecimals

d_income - reducedIncome

### Better output messages:

-"Incorrect" changed to "Incorrect input"

### Why:

Java convention - using camelCase

For clarity: Descriptive names make code self-documenting

Fixed bugs that prevented compilation for correctness

Clear variable names reduce need for comments (readability)

## Extracted methods and reduced code duplication

### Extracted 3 methods from main():

readUserInput() - handles all user input logic

processEmploymentContract() - handles employment contract flow

processCivilContract() - handles civil contract flow

### Created display methods

printSocialSecurityTaxes() - prints all social security taxes

printHealthInsurance() - prints health insurance info

printAdvanceTaxPaid() - prints advance tax information

printNetIncome() - calculates and prints net income

calculateAndPrintTaxableIncome() - reduces duplication

### Better encapsulation

Changed public to private

All methods except main() are now private

### Simplified main()

Now only 10 lines - more readable

Separation of concerns

Each method has a single responsibility

### Why:

Single Responsibility: Each method does one thing

Readability - main() is easier to understand

changes to output format require editing only one place

Smaller methods are easier to test

## Add constants and other improvements

### Replaced magic numbers with named constants

9.76 - SOCIAL_SECURITY_RATE

1.5 - HEALTH_SECURITY_RATE

2.45 - SICK_SECURITY_RATE

9.0 - HEALTH_INSURANCE_RATE_9

7.75 - HEALTH_INSURANCE_RATE_775

18.0 - ADVANCE_TAX_RATE

20.0 - CIVIL_CONTRACT_DEDUCTION_RATE

111.25 - EMPLOYMENT_TAX_DEDUCTIBLE

46.33 - EMPLOYMENT_TAX_FREE_INCOME

### Improved formatting constants (now final and uppercase)

formatTwoDecimals changed to FORMAT_TWO_DECIMALS

formatNoDecimals changed to FORMAT_NO_DECIMALS

### Better variable organization

Grouped constants by purpose (rates, fixed values)

Clear comments for each group

Constants are now private static final

### Minor improvements

Simplified BufferedReader initialization

Used constants in output messages

### Why:

No magic numbers -easier to find and modify

Constants names explain what values mean

Changing tax rates requires editing only one place

Following Java naming conventions for constants

Easier to adapt for potential tax changes

## Added unit tests for verification

### Created TaxCalculatorTest.java

-Complete unit test

-Tests for all calculation methods

-Tests verify if refactoring preserved functionality

### Better visibility for testability

-changed calculation methods to package-private

-changed calculated tax fields to package-private

-constants remain package-private for test verification

### Test coverage includes:

-testCalculateReducedIncome_StandardCase() - verifies social security deductions

-testCalculateHealthInsurance() - tests health insurance calculations

-testCalculateAdvanceTax() - validates advance tax rate

-testCalculateAdvanceTaxPaid_Employment() - employment contract specific

-testCalculateAdvanceTaxPaid_Civil() - civil contract specific

-testSocialSecurityCalculation() - comprehensive social security test

-testTaxRatesConstants() - validates all rate constants

-testEmploymentContractConstants() - validates employment constants

### Why:

-Verification requires testing

-To ensure refactoring didn't break functionality (tests)

-tests protect against bugs in future modifications

-package-private access - good practise

-Only accessible within the same package - Encapsulation maintained

-Testability: Allows unit tests without breaking encapsulation

