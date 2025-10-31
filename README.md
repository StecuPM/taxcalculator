# taxcalculator
TaxCalculator - a kata for a Clean Code exercise

# Fixed compilation errors and improved naming
1. Fixed compilation errors:
-duplicate declaration of advanceTaxPaidadvanceTax
-Renamed to proper variables advanceTax and advanceTaxPaid
-Fixed type mistake: soc_sick_secur  to socialSickSecurity
-Removed unused variables
-Removed incorrectly declared variables
2. variable naming changed to camelCase:
soc_security to socialSecurity
soc_health_security to socialHealthSecurity
soc_sick_security to socialSickSecurity
soc_health1 to healthInsurance9
soc_health2 to healthInsurance775
advanceTaxPaidadvanceTax to advanceTaxPaid
advanceTaxPaid0 to advanceTaxPaidRounded
3. Better method and formatter names:
df - formatNoDecimals
df00 - formatTwoDecimals
d_income - reducedIncome
4. Better output messages:
-"Incorrect" changed to "Incorrect input"


# Extracted methods and reduced code duplication
1. Extracted 3 methods from main():
readUserInput() - handles all user input logic
processEmploymentContract() - handles employment contract flow
processCivilContract() - handles civil contract flow
2. Created display methods
printSocialSecurityTaxes() - prints all social security taxes
printHealthInsurance() - prints health insurance info
printAdvanceTaxPaid() - prints advance tax information
printNetIncome() - calculates and prints net income
calculateAndPrintTaxableIncome() - reduces duplication
3. Better encapsulation
Changed public to private
All methods except main() are now private
4. Simplified main()
Now only 10 lines - more readable
Separation of concerns
Each method has a single responsibility
5. Why:
Single Responsibility: Each method does one thing
Readability - main() is easier to understand
changes to output format require editing only one place
Smaller methods are easier to test

# Add constants and other improvements
1. Replaced magic numbers with named constants
9.76 - SOCIAL_SECURITY_RATE
1.5 - HEALTH_SECURITY_RATE
2.45 - SICK_SECURITY_RATE
9.0 - HEALTH_INSURANCE_RATE_9
7.75 - HEALTH_INSURANCE_RATE_775
18.0 - ADVANCE_TAX_RATE
20.0 - CIVIL_CONTRACT_DEDUCTION_RATE
111.25 - EMPLOYMENT_TAX_DEDUCTIBLE
46.33 - EMPLOYMENT_TAX_FREE_INCOME
2. Improved formatting constants (now final and uppercase)
formatTwoDecimals changed to FORMAT_TWO_DECIMALS
formatNoDecimals changed to FORMAT_NO_DECIMALS
3. Better variable organization
Grouped constants by purpose (rates, fixed values)
Clear comments for each group
Constants are now private static final
4. Minor improvements
Simplified BufferedReader initialization
Used constants in output messages
5. Why:
No magic numbers -easier to find and modify
Constants names explain what values mean
Changing tax rates requires editing only one place
Following Java naming conventions for constants
Easier to adapt for potential tax changes
