package com.bartoszwalter.students.taxes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class TaxCalculator {

	// Tax rates constants
	private static final double SOCIAL_SECURITY_RATE = 9.76;
	private static final double HEALTH_SECURITY_RATE = 1.5;
	private static final double SICK_SECURITY_RATE = 2.45;
	private static final double HEALTH_INSURANCE_RATE_9 = 9.0;
	private static final double HEALTH_INSURANCE_RATE_775 = 7.75;
	private static final double ADVANCE_TAX_RATE = 18.0;
	private static final double CIVIL_CONTRACT_DEDUCTION_RATE = 20.0;

	// Fixed values
	private static final double EMPLOYMENT_TAX_DEDUCTIBLE = 111.25;
	private static final double EMPLOYMENT_TAX_FREE_INCOME = 46.33;

	// Input data
	private static double income = 0;
	private static char contractType = ' ';

	// Calculated taxes
	private static double socialSecurity = 0;
	private static double socialHealthSecurity = 0;
	private static double socialSickSecurity = 0;
	private static double taxDeductibleExpenses = 0;
	private static double healthInsurance9 = 0;
	private static double healthInsurance775 = 0;
	private static double advanceTax = 0;
	private static double taxFreeIncome = 0;
	private static double advanceTaxPaid = 0;
	private static double advanceTaxPaidRounded = 0;

	private static final DecimalFormat FORMAT_TWO_DECIMALS = new DecimalFormat("#.00");
	private static final DecimalFormat FORMAT_NO_DECIMALS = new DecimalFormat("#");

	public static void main(String[] args) {
		if (!readUserInput()) {
			return;
		}

		if (contractType == 'E') {
			processEmploymentContract();
		} else if (contractType == 'C') {
			processCivilContract();
		} else {
			System.out.println("Unknown type of contract!");
		}
	}

	private static boolean readUserInput() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			System.out.print("Enter income: ");
			income = Double.parseDouble(br.readLine());

			System.out.print("Contract Type: (E)mployment, (C)ivil: ");
			contractType = br.readLine().charAt(0);

			return true;

		} catch (Exception ex) {
			System.out.println("Incorrect input");
			System.err.println(ex);
			return false;
		}
	}

	private static void processEmploymentContract() {
		System.out.println("EMPLOYMENT CONTRACT");
		System.out.println("Income: " + income);

		double reducedIncome = calculateReducedIncome(income);
		printSocialSecurityTaxes();

		System.out.println("Income basis for health social security: " + reducedIncome);

		calculateHealthInsurance(reducedIncome);
		printHealthInsurance();

		taxDeductibleExpenses = EMPLOYMENT_TAX_DEDUCTIBLE;
		taxFreeIncome = EMPLOYMENT_TAX_FREE_INCOME;

		System.out.println("Tax deductible expenses: " + taxDeductibleExpenses);

		double taxableIncomeRounded = calculateAndPrintTaxableIncome(reducedIncome);

		calculateAdvanceTax(taxableIncomeRounded);
		System.out.println("Advance tax " + ADVANCE_TAX_RATE + "%: " + advanceTax);
		System.out.println("Tax free income: " + taxFreeIncome);

		double reducedTax = advanceTax - taxFreeIncome;
		System.out.println("Reduced tax: " + FORMAT_TWO_DECIMALS.format(reducedTax));

		calculateAdvanceTaxPaid();
		printAdvanceTaxPaid();

		printNetIncome();
	}

	private static void processCivilContract() {
		System.out.println("CIVIL CONTRACT");
		System.out.println("Income: " + income);

		double reducedIncome = calculateReducedIncome(income);
		printSocialSecurityTaxes();

		System.out.println("Income for calculating health security tax: " + reducedIncome);

		calculateHealthInsurance(reducedIncome);
		printHealthInsurance();

		taxFreeIncome = 0;
		taxDeductibleExpenses = (reducedIncome * CIVIL_CONTRACT_DEDUCTION_RATE) / 100;
		System.out.println("Tax deductible expenses: " + taxDeductibleExpenses);

		double taxableIncomeRounded = calculateAndPrintTaxableIncome(reducedIncome);

		calculateAdvanceTax(taxableIncomeRounded);
		System.out.println("Advance tax " + ADVANCE_TAX_RATE + "%: " + advanceTax);

		double reducedTax = advanceTax - taxFreeIncome;
		System.out.println("Tax to be paid: " + FORMAT_TWO_DECIMALS.format(reducedTax));

		calculateAdvanceTaxPaid();
		printAdvanceTaxPaid();

		printNetIncome();
	}

	private static void printSocialSecurityTaxes() {
		System.out.println("Social security tax: " + FORMAT_TWO_DECIMALS.format(socialSecurity));
		System.out.println("Health social security tax: " + FORMAT_TWO_DECIMALS.format(socialHealthSecurity));
		System.out.println("Sickness social security tax: " + FORMAT_TWO_DECIMALS.format(socialSickSecurity));
	}

	private static void printHealthInsurance() {
		System.out.println("Health security tax: " + HEALTH_INSURANCE_RATE_9 + "% = " +
				FORMAT_TWO_DECIMALS.format(healthInsurance9) + " " + HEALTH_INSURANCE_RATE_775 + "% = " +
				FORMAT_TWO_DECIMALS.format(healthInsurance775));
	}

	private static double calculateAndPrintTaxableIncome(double reducedIncome) {
		double taxableIncome = reducedIncome - taxDeductibleExpenses;
		double taxableIncomeRounded = Double.parseDouble(FORMAT_NO_DECIMALS.format(taxableIncome));
		System.out.println("Taxable income: " + taxableIncome + " rounded: " +
				FORMAT_NO_DECIMALS.format(taxableIncomeRounded));
		return taxableIncomeRounded;
	}

	private static void printAdvanceTaxPaid() {
		advanceTaxPaidRounded = Double.parseDouble(FORMAT_NO_DECIMALS.format(advanceTaxPaid));
		System.out.println("Advance tax paid: " + FORMAT_TWO_DECIMALS.format(advanceTaxPaid) +
				" rounded: " + FORMAT_NO_DECIMALS.format(advanceTaxPaidRounded));
	}

	private static void printNetIncome() {
		double netIncome = income - ((socialSecurity + socialHealthSecurity + socialSickSecurity) +
				healthInsurance9 + advanceTaxPaidRounded);
		System.out.println();
		System.out.println("Net income: " + FORMAT_TWO_DECIMALS.format(netIncome));
	}

	private static void calculateAdvanceTaxPaid() {
		advanceTaxPaid = advanceTax - healthInsurance775 - taxFreeIncome;
	}

	private static void calculateAdvanceTax(double income) {
		advanceTax = (income * ADVANCE_TAX_RATE) / 100;
	}

	private static double calculateReducedIncome(double income) {
		socialSecurity = (income * SOCIAL_SECURITY_RATE) / 100;
		socialHealthSecurity = (income * HEALTH_SECURITY_RATE) / 100;
		socialSickSecurity = (income * SICK_SECURITY_RATE) / 100;
		return (income - socialSecurity - socialHealthSecurity - socialSickSecurity);
	}

	private static void calculateHealthInsurance(double income) {
		healthInsurance9 = (income * HEALTH_INSURANCE_RATE_9) / 100;
		healthInsurance775 = (income * HEALTH_INSURANCE_RATE_775) / 100;
	}
}