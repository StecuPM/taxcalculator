package com.bartoszwalter.students.taxes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class TaxCalculator {

	public static double income = 0;
	public static char contractType = ' ';

	// social security taxes
	public static double socialSecurity = 0; // 9.76% of basis
	public static double socialHealthSecurity = 0; // 1.5% of basis
	public static double socialSickSecurity = 0; // 2.45% of basis

	// health-related taxes
	public static double taxDeductibleExpenses = 111.25;
	public static double healthInsurance9 = 0; // 9% of basis
	public static double healthInsurance775 = 0; // 7.75% of basis
	public static double advanceTax = 0; // advance tax 18%
	public static double taxFreeIncome = 46.33; // tax-free income monthly
	public static double advanceTaxPaid = 0;
	public static double advanceTaxPaidRounded = 0;

	public static void main(String[] args) {
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);

			System.out.print("Enter income: ");
			income = Double.parseDouble(br.readLine());

			System.out.print("Contract Type: (E)mployment, (C)ivil: ");
			contractType = br.readLine().charAt(0);

		} catch (Exception ex) {
			System.out.println("Incorrect input");
			System.err.println(ex);
			return;
		}

		DecimalFormat formatTwoDecimals = new DecimalFormat("#.00");
		DecimalFormat formatNoDecimals = new DecimalFormat("#");

		if (contractType == 'E') {
			System.out.println("EMPLOYMENT CONTRACT");
			System.out.println("Income: " + income);

			double reducedIncome = calculateReducedIncome(income);

			System.out.println("Social security tax: " + formatTwoDecimals.format(socialSecurity));
			System.out.println("Health social security tax: " + formatTwoDecimals.format(socialHealthSecurity));
			System.out.println("Sickness social security tax: " + formatTwoDecimals.format(socialSickSecurity));
			System.out.println("Income basis for health social security: " + reducedIncome);

			calculateHealthInsurance(reducedIncome);

			System.out.println("Health social security tax: 9% = " + formatTwoDecimals.format(healthInsurance9) +
					" 7.75% = " + formatTwoDecimals.format(healthInsurance775));
			System.out.println("Tax deductible expenses: " + taxDeductibleExpenses);

			double taxableIncome = reducedIncome - taxDeductibleExpenses;
			double taxableIncomeRounded = Double.parseDouble(formatNoDecimals.format(taxableIncome));

			System.out.println("Taxable income: " + taxableIncome + " rounded: " + formatNoDecimals.format(taxableIncomeRounded));

			calculateAdvanceTax(taxableIncomeRounded);

			System.out.println("Advance tax 18%: " + advanceTax);
			System.out.println("Tax free income: " + taxFreeIncome);

			double reducedTax = advanceTax - taxFreeIncome;
			System.out.println("Reduced tax: " + formatTwoDecimals.format(reducedTax));

			calculateAdvanceTaxPaid();

			advanceTaxPaidRounded = Double.parseDouble(formatNoDecimals.format(advanceTaxPaid));
			System.out.println("Advance tax paid: " + formatTwoDecimals.format(advanceTaxPaid) +
					" rounded: " + formatNoDecimals.format(advanceTaxPaidRounded));

			double netIncome = income - ((socialSecurity + socialHealthSecurity + socialSickSecurity) +
					healthInsurance9 + advanceTaxPaidRounded);

			System.out.println();
			System.out.println("Net income: " + formatTwoDecimals.format(netIncome));

		} else if (contractType == 'C') {
			System.out.println("CIVIL CONTRACT");
			System.out.println("Income: " + income);

			double reducedIncome = calculateReducedIncome(income);

			System.out.println("Social security tax: " + formatTwoDecimals.format(socialSecurity));
			System.out.println("Health social security tax: " + formatTwoDecimals.format(socialHealthSecurity));
			System.out.println("Sickness social security tax: " + formatTwoDecimals.format(socialSickSecurity));
			System.out.println("Income for calculating health security tax: " + reducedIncome);

			calculateHealthInsurance(reducedIncome);

			System.out.println("Health security tax: 9% = " + formatTwoDecimals.format(healthInsurance9) +
					" 7.75% = " + formatTwoDecimals.format(healthInsurance775));

			taxFreeIncome = 0;
			taxDeductibleExpenses = (reducedIncome * 20) / 100;

			System.out.println("Tax deductible expenses: " + taxDeductibleExpenses);

			double taxableIncome = reducedIncome - taxDeductibleExpenses;
			double taxableIncomeRounded = Double.parseDouble(formatNoDecimals.format(taxableIncome));

			System.out.println("Income to be taxed: " + taxableIncome + " rounded: " + formatNoDecimals.format(taxableIncomeRounded));

			calculateAdvanceTax(taxableIncomeRounded);

			System.out.println("Advance tax 18%: " + advanceTax);

			double reducedTax = advanceTax - taxFreeIncome;
			System.out.println("Tax to be paid: " + formatTwoDecimals.format(reducedTax));

			calculateAdvanceTaxPaid();

			advanceTaxPaidRounded = Double.parseDouble(formatNoDecimals.format(advanceTaxPaid));
			System.out.println("Advance tax: " + formatTwoDecimals.format(advanceTaxPaid) +
					" rounded: " + formatNoDecimals.format(advanceTaxPaidRounded));

			double netIncome = income - ((socialSecurity + socialHealthSecurity + socialSickSecurity) +
					healthInsurance9 + advanceTaxPaidRounded);

			System.out.println();
			System.out.println("Net income: " + formatTwoDecimals.format(netIncome));

		} else {
			System.out.println("Unknown type of contract!");
		}
	}

	public static void calculateAdvanceTaxPaid() {
		advanceTaxPaid = advanceTax - healthInsurance775 - taxFreeIncome;
	}

	public static void calculateAdvanceTax(double income) {
		advanceTax = (income * 18) / 100;
	}

	public static double calculateReducedIncome(double income) {
		socialSecurity = (income * 9.76) / 100;
		socialHealthSecurity = (income * 1.5) / 100;
		socialSickSecurity = (income * 2.45) / 100;
		return (income - socialSecurity - socialHealthSecurity - socialSickSecurity);
	}

	public static void calculateHealthInsurance(double income) {
		healthInsurance9 = (income * 9) / 100;
		healthInsurance775 = (income * 7.75) / 100;
	}
}