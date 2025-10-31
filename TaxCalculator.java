package com.bartoszwalter.students.taxes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class TaxCalculator {

	private static double income = 0;
	private static char contractType = ' ';

	// social security taxes
	private static double socialSecurity = 0;
	private static double socialHealthSecurity = 0;
	private static double socialSickSecurity = 0;

	// health-related taxes
	private static double taxDeductibleExpenses = 111.25;
	private static double healthInsurance9 = 0;
	private static double healthInsurance775 = 0;
	private static double advanceTax = 0;
	private static double taxFreeIncome = 46.33;
	private static double advanceTaxPaid = 0;
	private static double advanceTaxPaidRounded = 0;

	private static DecimalFormat formatTwoDecimals = new DecimalFormat("#.00");
	private static DecimalFormat formatNoDecimals = new DecimalFormat("#");

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
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);

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

		System.out.println("Tax deductible expenses: " + taxDeductibleExpenses);

		double taxableIncomeRounded = calculateAndPrintTaxableIncome(reducedIncome);

		calculateAdvanceTax(taxableIncomeRounded);
		System.out.println("Advance tax 18%: " + advanceTax);
		System.out.println("Tax free income: " + taxFreeIncome);

		double reducedTax = advanceTax - taxFreeIncome;
		System.out.println("Reduced tax: " + formatTwoDecimals.format(reducedTax));

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
		taxDeductibleExpenses = (reducedIncome * 20) / 100;
		System.out.println("Tax deductible expenses: " + taxDeductibleExpenses);

		double taxableIncomeRounded = calculateAndPrintTaxableIncome(reducedIncome);

		calculateAdvanceTax(taxableIncomeRounded);
		System.out.println("Advance tax 18%: " + advanceTax);

		double reducedTax = advanceTax - taxFreeIncome;
		System.out.println("Tax to be paid: " + formatTwoDecimals.format(reducedTax));

		calculateAdvanceTaxPaid();
		printAdvanceTaxPaid();

		printNetIncome();
	}

	private static void printSocialSecurityTaxes() {
		System.out.println("Social security tax: " + formatTwoDecimals.format(socialSecurity));
		System.out.println("Health social security tax: " + formatTwoDecimals.format(socialHealthSecurity));
		System.out.println("Sickness social security tax: " + formatTwoDecimals.format(socialSickSecurity));
	}

	private static void printHealthInsurance() {
		System.out.println("Health security tax: 9% = " + formatTwoDecimals.format(healthInsurance9) +
				" 7.75% = " + formatTwoDecimals.format(healthInsurance775));
	}

	private static double calculateAndPrintTaxableIncome(double reducedIncome) {
		double taxableIncome = reducedIncome - taxDeductibleExpenses;
		double taxableIncomeRounded = Double.parseDouble(formatNoDecimals.format(taxableIncome));
		System.out.println("Taxable income: " + taxableIncome + " rounded: " + formatNoDecimals.format(taxableIncomeRounded));
		return taxableIncomeRounded;
	}

	private static void printAdvanceTaxPaid() {
		advanceTaxPaidRounded = Double.parseDouble(formatNoDecimals.format(advanceTaxPaid));
		System.out.println("Advance tax paid: " + formatTwoDecimals.format(advanceTaxPaid) +
				" rounded: " + formatNoDecimals.format(advanceTaxPaidRounded));
	}

	private static void printNetIncome() {
		double netIncome = income - ((socialSecurity + socialHealthSecurity + socialSickSecurity) +
				healthInsurance9 + advanceTaxPaidRounded);
		System.out.println();
		System.out.println("Net income: " + formatTwoDecimals.format(netIncome));
	}

	private static void calculateAdvanceTaxPaid() {
		advanceTaxPaid = advanceTax - healthInsurance775 - taxFreeIncome;
	}

	private static void calculateAdvanceTax(double income) {
		advanceTax = (income * 18) / 100;
	}

	private static double calculateReducedIncome(double income) {
		socialSecurity = (income * 9.76) / 100;
		socialHealthSecurity = (income * 1.5) / 100;
		socialSickSecurity = (income * 2.45) / 100;
		return (income - socialSecurity - socialHealthSecurity - socialSickSecurity);
	}

	private static void calculateHealthInsurance(double income) {
		healthInsurance9 = (income * 9) / 100;
		healthInsurance775 = (income * 7.75) / 100;
	}
}