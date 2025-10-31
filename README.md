# taxcalculator
TaxCalculator - a kata for a Clean Code exercise

Fixed compilation errors and improved naming
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