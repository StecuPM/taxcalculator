package com.bartoszwalter.students.taxes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//Unit tests for TaxCalculator to verify that refactoring did not break the functionality.
public class TaxCalculatorTest {

    private static final double DELTA = 0.01;

    @Test
    public void testCalculateReducedIncome_StandardCase() {
        // Given
        double income = 5000.0;
        
        // When
        double result = TaxCalculator.calculateReducedIncome(income);
        
        // Then
        // 5000 - (5000 * 13.71%) = 4314.5
        assertEquals(4314.5, result, DELTA);
    }

    @Test
    public void testCalculateHealthInsurance() {
        // Given
        double income = 4000.0;
        
        // When
        TaxCalculator.calculateHealthInsurance(income);
        
        // Then
        assertEquals(360.0, TaxCalculator.healthInsurance9, DELTA);
        assertEquals(310.0, TaxCalculator.healthInsurance775, DELTA);
    }

    @Test
    public void testCalculateAdvanceTax() {
        // Given
        double income = 3000.0;
        
        // When
        TaxCalculator.calculateAdvanceTax(income);
        
        // Then
        assertEquals(540.0, TaxCalculator.advanceTax, DELTA);
    }

    @Test
    public void testCalculateAdvanceTaxPaid_Employment() {
        // Given
        TaxCalculator.advanceTax = 500.0;
        TaxCalculator.healthInsurance775 = 100.0;
        TaxCalculator.taxFreeIncome = 46.33;
        
        // When
        TaxCalculator.calculateAdvanceTaxPaid();
        
        // Then
        assertEquals(353.67, TaxCalculator.advanceTaxPaid, DELTA);
    }

    @Test
    public void testCalculateAdvanceTaxPaid_Civil() {
        // Given
        TaxCalculator.advanceTax = 500.0;
        TaxCalculator.healthInsurance775 = 100.0;
        TaxCalculator.taxFreeIncome = 0.0;
        
        // When
        TaxCalculator.calculateAdvanceTaxPaid();
        
        // Then
        assertEquals(400.0, TaxCalculator.advanceTaxPaid, DELTA);
    }

    @Test
    public void testSocialSecurityCalculation() {
        // Given
        double income = 6000.0;
        
        // When
        TaxCalculator.calculateReducedIncome(income);
        
        // Then
        assertEquals(585.6, TaxCalculator.socialSecurity, DELTA);
        assertEquals(90.0, TaxCalculator.socialHealthSecurity, DELTA);
        assertEquals(147.0, TaxCalculator.socialSickSecurity, DELTA);
    }

    @Test
    public void testTaxRatesConstants() {
        assertEquals(9.76, TaxCalculator.SOCIAL_SECURITY_RATE, DELTA);
        assertEquals(1.5, TaxCalculator.HEALTH_SECURITY_RATE, DELTA);
        assertEquals(2.45, TaxCalculator.SICK_SECURITY_RATE, DELTA);
        assertEquals(18.0, TaxCalculator.ADVANCE_TAX_RATE, DELTA);
    }

    @Test
    public void testEmploymentContractConstants() {
        assertEquals(111.25, TaxCalculator.EMPLOYMENT_TAX_DEDUCTIBLE, DELTA);
        assertEquals(46.33, TaxCalculator.EMPLOYMENT_TAX_FREE_INCOME, DELTA);
    }
}
