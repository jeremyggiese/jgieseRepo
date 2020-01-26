import java.util.Scanner;

public class EconMenu {

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(EconomicsMethods.findMaxAmountPresentGradient(10000.0,60000.0,10.0,5.0));
		System.out.println();
		//System.out.println(EconomicsMethods.findPFValue(1.0,6.0,8 ));
		//menu();
		
	}
	public static int menu() {
		System.out.println("1.  To find Simple Interest.\n2.  To find Compound Interest.\n3.  (F/P) To find Future with Present value.\n4.  (P/F) To find Present with Future value.\n5.  (P/A)To find Present with Annual value.\n6.  (A/P) To find Annual with Present value.\n7.  (A/F) To find Annual with Future value.\n8.  (F/A) To find Future with Annual value.\n9.  To find Cash Flow Gradient.\n10. To determine Arithmetic Gradient.\n11. (P/G) To find Present Value with Gradient.\n12. (A/G) To find Annual with Gradient.\n13. (F/G) To find Future with Gradient.\n14. To find Present Geometric value with initial. ");
		return 0;
	}
*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*int menu=menu();
		if(menu==1) {
			String firstValue=menuVariable();
			String secondValue=menuVariable();
			String toFind=menuVariable();
			
		}else if(menu==2) {
			
		}else if(menu==3) {
			
		}else if(menu==4) {
			
		}else{
			menu=menu;
		}*/
		
		String binary=String.format("%8s", Integer.toBinaryString(0xFD & 0xFF)).replace(' ','0');
		System.out.println(binary.getBytes());
		//01010101
		//1001010101
	//	System.out.println(EconomicsMethods.calculateEffectiveAny(12, 4));//(269,10,6));
		/*int choice = menu()
		while(choice != 23){
			switch (choice){
				case 1:
					Scanner in = new Scanner(System.in);
					System.out.println("Principal: ");
					double principal = in.nextDouble();
					in.reset();
					System.out.println("Interest: ");
					double interest = in.nextDouble();
					in.reset();
					System.out.println("Periods: ");
					int periods = in.nextInt();
					in.reset();
					System.out.println("Simple Interest: " + EconomicsMethods.findSimpleInterest(principal, interest, periods));
					break;
				case 2:
					in = new Scanner(System.in);
					System.out.println("Principal: ");
					principal = in.nextDouble();
					System.out.println("Interest: ");
					interest = in.nextDouble();
					System.out.println("Years: ");
					periods = in.nextInt();
					System.out.println("Compound Interest: " + EconomicsMethods.findCompoundInterestInYears(principal, interest, periods));
					break;
				case 3:
					in = new Scanner(System.in);
					System.out.println("Present: ");
					principal = in.nextDouble();
					System.out.println("Interest: ");
					interest = in.nextDouble();
					System.out.println("Period: ");
					double number = in.nextDouble();
					System.out.println("Future from Present: " + EconomicsMethods.findFPValue(principal, interest, number));
					break;
				case 4:
					in = new Scanner(System.in);
					System.out.println("Future: ");
					principal = in.nextDouble();
					System.out.println("Interest: ");
					interest = in.nextDouble();
					System.out.println("Period: ");
					number = in.nextDouble();
					System.out.println("Present from Future: " + EconomicsMethods.findPFValue(principal, interest, number));
					break;
				case 5:
					in = new Scanner(System.in);
					System.out.println("Annual: ");
					principal = in.nextDouble();
					System.out.println("Interest: ");
					interest = in.nextDouble();
					System.out.println("Period: ");
					number = in.nextDouble();
					System.out.println("Present from Annual: " + EconomicsMethods.findPAValue(principal, interest, number));
					break;
				case 6:
					in = new Scanner(System.in);
					System.out.println("Present: ");
					principal = in.nextDouble();
					System.out.println("Interest: ");
					interest = in.nextDouble();
					System.out.println("Period: ");
					number = in.nextDouble();
					System.out.println("Annual from Present: " + EconomicsMethods.findAPValue(principal, interest, number));
					break;
				case 7:
					in = new Scanner(System.in);
					System.out.println("Future: ");
					principal = in.nextDouble();
					System.out.println("Interest: ");
					interest = in.nextDouble();
					System.out.println("Period: ");
					number = in.nextDouble();
					System.out.println("Annual from Future: " + EconomicsMethods.findAFValue(principal, interest, number));
					break;
				case 8:
					in = new Scanner(System.in);
					System.out.println("Annual: ");
					principal = in.nextDouble();
					System.out.println("Interest: ");
					interest = in.nextDouble();
					System.out.println("Period: ");
					number = in.nextDouble();
					System.out.println("Future from Annual: " + EconomicsMethods.findFAValue(principal, interest, number));
					break;
				case 9:
					in = new Scanner(System.in);
					System.out.println("Base Amount: ");
					principal = in.nextDouble();
					System.out.println("Interest: ");
					interest = in.nextDouble();
					System.out.println("Period: ");
					number = in.nextDouble();
					System.out.println("Cash Flow Gradient: " + EconomicsMethods.cashFlowGradient(principal, interest, number));
					break;
				case 10:
					in = new Scanner(System.in);
					System.out.println("Principal: ");
					principal = in.nextDouble();
					System.out.println("Interest: ");
					interest = in.nextDouble();
					System.out.println("Period: ");
					number = in.nextDouble();
					System.out.println("Arithmetic Gradient Percent: " + EconomicsMethods.determineArithmeticGradientPercent(principal, interest, number));
					break;
				case 11:
					in = new Scanner(System.in);
					System.out.println("Desired final Amount: ");
					double toAccumulate = in.nextDouble();
					System.out.println("Principal: ");
					principal = in.nextDouble();
					System.out.println("Interest: ");
					interest = in.nextDouble();
					System.out.println("Period: ");
					number = in.nextDouble();
					System.out.println("Arithmetic Gradient Value: " + EconomicsMethods.determineArithmeticGradientValue(toAccumulate,principal, interest, number));
					break;
				case 12:
					in = new Scanner(System.in);
					System.out.println("Gradient: ");
					principal = in.nextDouble();
					System.out.println("Interest: ");
					interest = in.nextDouble();
					System.out.println("Period: ");
					number = in.nextDouble();
					System.out.println("Compound Interest: " + EconomicsMethods.findPG(principal, interest, number));
					break;
				case 13:
					in = new Scanner(System.in);
					System.out.println("Gradient: ");
					double gradient = in.nextDouble();
					System.out.println("Interest: ");
					interest = in.nextDouble();
					System.out.println("Time Period ");
					number = in.nextDouble();
					System.out.println("Annual from Gradient: " + EconomicsMethods.findAG(gradient, interest,number));
					break;
				case 14:
					in = new Scanner(System.in);
					System.out.println("Gradient: ");
					principal = in.nextDouble();
					System.out.println("Interest: ");
					interest = in.nextDouble();
					System.out.println("Period: ");
					number = in.nextDouble();
					System.out.println("Future from Gradient: " + EconomicsMethods.findFG(principal, interest, number));
					break;
				case 15:
					in = new Scanner(System.in);
					System.out.println("Gradient Percent: ");
					gradient = in.nextDouble();
					System.out.println("Initial Value: ");
					double initial = in.nextDouble();
					System.out.println("Interest: ");
					interest = in.nextDouble();
					System.out.println("Period: ");
					number = in.nextDouble();
					System.out.println("Present from Gradient: " + EconomicsMethods.findCurrentGeometric(gradient, initial, interest, number));
					break;
				case 16:
					in = new Scanner(System.in);
					System.out.println("Gradient: ");
					gradient = in.nextDouble();
					System.out.println("Initial Value: ");
					initial = in.nextDouble();
					System.out.println("Interest: ");
					interest = in.nextDouble();
					System.out.println("Period: ");
					number = in.nextDouble();
					System.out.println("Max amount with base amount and gradient: " + EconomicsMethods.findMaxAmountPresentGradient(gradient, initial, interest, number));
					break;
				case 17:
					in = new Scanner(System.in);
					System.out.println("Enter original compound Period: ");
					initial = in.nextDouble();
					System.out.println("New time period: ");
					double newPeriod = in.nextDouble();
					System.out.println("Interest: ");
					interest = in.nextDouble();
					System.out.println("Nominal interest: " + EconomicsMethods.calculateNominal(interest, initial, newPeriod));
					break;
				case 18:
					in = new Scanner(System.in);
					System.out.println("Enter original compound Period: ");
					initial = in.nextDouble();
					System.out.println("New time period: ");
					newPeriod = in.nextDouble();
					System.out.println("Interest: ");
					interest = in.nextDouble();
					System.out.println("Present with Gradient: " + EconomicsMethods.calculateNominal(interest, initial, newPeriod));
					break;
				case 19:
					in = new Scanner(System.in);
					System.out.println("Enter number of compounds per Period: ");
					initial = in.nextDouble();
					System.out.println("Interest: ");
					interest = in.nextDouble();
					System.out.println("Effective interest for "+initial+": " + EconomicsMethods.calculateEffectiveAny(interest, initial));
					break;
				case 20:
					in = new Scanner(System.in);
					System.out.println("Interest: ");
					interest = in.nextDouble();
					System.out.println("Continuous rate: " + EconomicsMethods.calculateContinuous(interest));
					break;
				case 21:
					in = new Scanner(System.in);
					System.out.println("Enter future value: ");
					double future = in.nextDouble();
					System.out.println("Enter present value: ");
					double present = in.nextDouble();
					System.out.println("Time period: ");
					interest = in.nextDouble();
					System.out.println("Rate(F/P): " + 100*EconomicsMethods.findRateFP(future, present,interest));
					break;
				case 22:
					in = new Scanner(System.in);
					System.out.println("Enter annual value: ");
					double annual = in.nextDouble();
					System.out.println("Enter future value: ");
					future = in.nextDouble();
					System.out.println("Time period: ");
					interest = in.nextDouble();
					System.out.println("Rate(F/A): " + 100*EconomicsMethods.findRateFA(future, annual,interest));
					break;
			}
			Scanner wait = new Scanner(System.in);
			System.out.println("Enter anything to continue: ");
			String hold = wait.nextLine();
			choice = menu();
		}*/
		
	}
	public static String menuVariable() {
		System.out.println("Enter a variable you are using(Ex:Present(P), Future(f), annual(a), interest(i), time(n), gradient value(GV), etc):");
		Scanner in = new Scanner(System.in);
		String select="";
		String input = in.nextLine();
		if(input.toLowerCase().contains("p")&&!input.toLowerCase().contains("f")||input.toLowerCase().contains("present")&&!input.toLowerCase().contains("f")) {
			select="p";
		}
		else if(input.toLowerCase().contains("f")&&!input.toLowerCase().contains("p")||input.toLowerCase().contains("future")&&!input.toLowerCase().contains("p")) {
			select="f";
		}
		else if(input.toLowerCase().contains("t")&&!input.toLowerCase().contains("f")&&!input.toLowerCase().contains("p")||input.toLowerCase().contains("n")&&!input.toLowerCase().contains("time")) {
			select="t";
		}else if(input.toLowerCase().contains("a")&&!input.toLowerCase().contains("e")||input.toLowerCase().contains("annual")&&!input.toLowerCase().contains("gradient")) {
			select="a";
		}else if(input.toLowerCase().contains("gradient")&&!input.toLowerCase().contains("value")||input.toLowerCase().contains("gp")&&!input.toLowerCase().contains("gv")) {
			select="gp";
		}else if(input.toLowerCase().contains("gradient")&&!input.toLowerCase().contains("percent")||input.toLowerCase().contains("gv")&&!input.toLowerCase().contains("gp")) {
			select="gv";
		}if(input.contains("?")) {
			select+="?";
		}
		return select;
	}
	
	public static int menu() {
		System.out.println("1. To find an unknown value using any equation in the form of (X/Y).\n2. To find an interest rate from a different interest rate.\n3. To use functions related to Arithmetic Gradient.\n4. To use functions related to Geometric gradient.");
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		return input;
	}
	
}
