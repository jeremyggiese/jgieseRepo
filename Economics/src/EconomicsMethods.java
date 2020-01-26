import java.math.*;
public class EconomicsMethods {
	EconomicsMethods(){
		
	}
	//1
	public static String findSimpleInterest(double principal, double interestRate,int numberOfPeriods) {
		interestRate=interestRate/100;
		double simpleInterest=principal+(principal*numberOfPeriods*interestRate);
		return ("I=Pni\n"+principal+"+("+principal+"*"+numberOfPeriods+"*"+interestRate+"="+simpleInterest);
	}
	//2
	public static String findCompoundInterestInYears(double principal, double interestRate,int numberOfYears) {
		interestRate=interestRate/100;
		double compoundInterest=principal*Math.pow((1+interestRate), numberOfYears);
		return ("I=P(1+i)^n\n("+principal+"+"+"(1+"+interestRate+")^"+numberOfYears+"="+compoundInterest);
	}
	//3
	//all below work
	public static double findFPValue(double present, double interestRate, double timePeriod) {
		interestRate=interestRate/100;
		//System.out.println("Find F/P using formula: Future=Present(1+interest)^time\n"+present+"*(1+"+interestRate+")^"+timePeriod);
		double FValue=present*Math.pow((1+interestRate), timePeriod);
		return FValue;
	}
	//4
	public static double findPFValue(double future, double interestRate, double timePeriod) {
		interestRate=interestRate/100;
		//System.out.println("Find P/F using formula: Present=Future(1+interest)^-time\n"+future+"*(1+"+interestRate+")^-"+timePeriod);
		double PValue=future*Math.pow((1+interestRate), -timePeriod);
		return PValue;
	}
	//5
	public static double findPAValue(double annual, double interestRate, double timePeriod) {
		interestRate=interestRate/100;
		//System.out.println("Find P/A using formula: Present=Annual*\n(1+interest)^time)-1)/\ninterest*(1+i)^time\n");
		//System.out.println("Find P/A using formula: Present="+annual+"*\n(1+"+interestRate+")^"+timePeriod+")-1)/\n"+interestRate+"*(1+"+interestRate+")^time\n");
		double PValue=annual*((Math.pow((1+interestRate), timePeriod)-1)/(interestRate*Math.pow((1+interestRate), timePeriod)));
		return PValue;
	}
	//6
	public static double findAPValue(double present, double interestRate, double timePeriod) {
		interestRate=interestRate/100;
		//System.out.println("Find A/P using formula: Annual=Present*\ninterest*(1+interest)^time))/\n((1+i)^time)-1\n");
		//System.out.println("Find A/P using formula: Annual="+present+"*\n"+interestRate+"(1+"+interestRate+")^"+timePeriod+"))/\n*((1+"+interestRate+")^time)-1\n");
		double AValue=present*(interestRate*(Math.pow((1+interestRate), timePeriod)))/((Math.pow((1+interestRate), timePeriod))-1);
		return AValue;
	}
	//7
	public static double findAFValue(double future, double interestRate, double timePeriod) {
		interestRate=interestRate/100;
		//System.out.println("Find A/F using formula: Annual=Future*(i/((1+i)^time-1)");
		//System.out.println("Find A/F using formula: Annual="+future+"*("+interestRate+"/((1+"+interestRate+")^"+timePeriod+"-1)");
		double AValue=future*(interestRate/(Math.pow((1+interestRate),timePeriod)-1));
		return AValue;
	}
	//8
	public static double findFAValue(double annual, double interestRate, double timePeriod) {
		interestRate=interestRate/100;
		//System.out.println("Find F/A using formula: Annual=Future*(((1+i)^time-1)/i");
		//System.out.println("Find F/A using formula: Annual="+annual+"*(((1+"+interestRate+")^"+timePeriod+")-1)/"+interestRate);
		double FValue=annual*((Math.pow((1+interestRate),timePeriod)-1)/interestRate);
		return FValue;
	}
	//9
	public static double simpleCashFlowGradientAtTimePeriod(double baseAmount,  double gradient,double timePeriod) {
	//	System.out.println("Cash Flow at time n=base amount+(time-1)*gradient");
		double cashFlow=baseAmount+(timePeriod-1)*gradient;
	//	System.out.println("Cash Flow at" +timePeriod+ "="+baseAmount+"+("+timePeriod+"-1)*"+gradient);
		return cashFlow;
	}
	public static double presentCashFlowGradientAtTimePeriod(double baseAmount,  double gradient,double interest,double timePeriod) {
		//	System.out.println("Cash Flow at time n=base amount+(time-1)*gradient");
			double present=(findPAValue(baseAmount, timePeriod, interest))+findPG(gradient, interest, timePeriod);
		//	System.out.println("Cash Flow at" +timePeriod+ "="+baseAmount+"+("+timePeriod+"-1)*"+gradient);
			return present;
		}
	//10
	public static double determineArithmeticGradientPercent(double baseAmount, double finalAmount,double timePeriod){
	//	System.out.println("Arithmetic Gradient can be found using: (final amount-base amount)/(amount of time - 1)");
		double arithmeticGradient=(finalAmount-baseAmount)/(timePeriod-1);
	//	System.out.println("("+finalAmount+"-"+baseAmount+")/("+timePeriod+"-1)");
		return arithmeticGradient;
	}
	public static double determineArithmeticGradientValue(double toAccumulate, double baseAmount, double interestRate,double timePeriod){
	//	System.out.println("Arithmetic Gradient can be found using: (final amount-base amount)/(amount of time - 1)");
		double arithmeticGradientValue=(-findPAValue(baseAmount, interestRate, timePeriod)+findPFValue(toAccumulate, interestRate, timePeriod))/findPG(1, interestRate, timePeriod);
	//	System.out.println("("+finalAmount+"-"+baseAmount+")/("+timePeriod+"-1)");
		return arithmeticGradientValue;
	}
	//11
	public static double findPG(double gradient, double interestRate, double timePeriod){
		interestRate=interestRate/100;
		//System.out.println("Present Gradient can be found using: (1/interest Rate)(((((1+interest Rate)^time Period)-1)/interest Rate*(1+interest Rate)^time period)\n-(time period/(1+interest Rate)^time period");
		double PValue=(1.0/interestRate)*((Math.pow((interestRate+1), timePeriod)-1)/(interestRate*(Math.pow((interestRate+1), timePeriod)))-(timePeriod/(Math.pow((interestRate+1), timePeriod))));
		
		//double PValue2=(Math.pow((interestRate+1), timePeriod)-interestRate*timePeriod-1)/Math.pow(interestRate, 2.0)*Math.pow(interestRate+1, timePeriod);
		//System.out.println("("+finalAmount+"-"+baseAmount+")/("+timePeriod+"-1)");
		return gradient*PValue;
	}
	//12
	public static double findAG(double gradient,  double interestRate, double timePeriod) {
		interestRate=interestRate/100;
		double AValue=gradient*((1/interestRate)-timePeriod/(Math.pow(interestRate+1, timePeriod)-1));
		return AValue;
	}
	//13
	public static double findFG(double gradient,  double interestRate, double timePeriod) {
		interestRate=interestRate/100;
		double FValue=gradient*(1/interestRate)*(Math.pow(1+interestRate, timePeriod)-1)/interestRate-1;
		return FValue;
	}
	public static double findPresentUniformValue(double gradient, double baseAmount,double interestRate , double timePeriod) {
		double currentValue=0;
		currentValue=findPAValue(baseAmount, interestRate,timePeriod)+findPG(gradient, interestRate, timePeriod);
		return currentValue;
	}
	public static double findFutureUniformValue(double gradient, double baseAmount,double interestRate , double timePeriod) {
		double currentValue=0;
		currentValue=findFAValue(baseAmount, interestRate,timePeriod)+findFG(gradient, interestRate, timePeriod);
		return currentValue;
	}
	public static double findAnnualUniformValue(double gradient, double baseAmount,double interestRate , double timePeriod) {
		double currentValue=0;
		currentValue=baseAmount+findAG(gradient, interestRate, timePeriod);
		return currentValue;
	}
	//14
	public static double findCurrentGeometric(double gradientPercent, double initialValue, double interestRate, double timePeriod) {
		interestRate=interestRate/100;
		gradientPercent=gradientPercent/100;
		if(gradientPercent!=interestRate) {
		double innerCalc=(1+gradientPercent)/(1+interestRate);
		double pGValue=initialValue*(1-(Math.pow(innerCalc, timePeriod)))/(interestRate-gradientPercent);
		return pGValue;
		}else {
		double	pGValue=initialValue*(timePeriod/(1+interestRate));
		return pGValue;
		}
	}
	public static double findYear0AnnualWorth(double annual, double initialPayment, double interestRate, double timePeriod1, double timePeriod2) {
		double shiftedP=annual*findPAValue(1, interestRate, timePeriod1)*findPFValue(1, interestRate, timePeriod2);
		double year0Annual=initialPayment+shiftedP;
		return year0Annual;
		
	}
	public static double findEquivalentAnnualShifted(double originalSeriesAnnualWorth,double interestRate, double originalTimePeriod, double shiftedTimePeriod) {
	double shiftedP=originalSeriesAnnualWorth*findPAValue(1, interestRate, originalTimePeriod)*findPFValue(1, interestRate, shiftedTimePeriod);
	double shiftedA=findAPValue(shiftedP, interestRate, (originalTimePeriod+shiftedTimePeriod+1));
	return shiftedA;
	}
	public static double findEquivalentPresentShifted(double originalSeriesAnnualWorth,double interestRate, double originalTimePeriod, double shiftedTimePeriod) {
		double shiftedP=originalSeriesAnnualWorth*findPAValue(1, interestRate, originalTimePeriod)*findPFValue(1, interestRate, shiftedTimePeriod);
		//double shiftedSeriesValue=findAPValue(shiftedP, interestRate/100, originalTimePeriod+shiftedTimePeriod+1);
		return shiftedP;
		}
	public static double findEquivalentFutureShifted(double originalSeriesAnnualWorth,double interestRate, double originalTimePeriod, double shiftedTimePeriod) {
		double shiftedP=originalSeriesAnnualWorth*findPAValue(1, interestRate, originalTimePeriod)*findPFValue(1, interestRate, shiftedTimePeriod);
		double shiftedF=findFPValue(shiftedP, interestRate, originalTimePeriod+shiftedTimePeriod);
		return shiftedF;
		}
	public static double calculateNominal(double interestRate,double perTimePeriod, double timeToFind) {
		interestRate=interestRate/100;
		if(perTimePeriod>timeToFind) {
			return interestRate*timeToFind;
		}
		else 
			return 0;	
	}
	public static double calculateEffectiveAnnual(double interestRate, char interestPeriodMeasure) {
		interestRate=interestRate/100;
		double measureToYear=0;
		if(interestPeriodMeasure=='m') {
			measureToYear=12;
		}else if(interestPeriodMeasure=='y') {
			measureToYear=1;
		}else if(interestPeriodMeasure=='d') {
			measureToYear=365;
		}else if(interestPeriodMeasure=='s') {
			measureToYear=2;
		}else if(interestPeriodMeasure=='q') {
			measureToYear=4;
		}else if(interestPeriodMeasure=='w') {
			measureToYear=52;
		}
		double annualEffective=(Math.pow((interestRate+1), measureToYear)-1);
		return annualEffective;
	}
	public static double calculateEffectiveAny(double nominalInterestRate, double compoundPeriod) {
		nominalInterestRate=nominalInterestRate/100.0;
		double calculateEffectiveAny=100*(Math.pow(((nominalInterestRate/compoundPeriod)+1.0), compoundPeriod)-1.0);
		//System.out.println(Math.pow(((nominalInterestRate+1.0)/compoundPeriod), compoundPeriod));
		
		return calculateEffectiveAny;
	}
	public static double calculateContinuous(double interestRate){
		double e=2.71828;
		double continuous=Math.pow(e, interestRate)-1;
		return continuous;
	}
	public static double findMaxAmountPresentGradient(double gradient, double baseAmount, double interestRate, double timePeriod) {
		double maxAmount=findPAValue(baseAmount, interestRate, timePeriod)+findPG(gradient, interestRate, timePeriod);
		return maxAmount;
	}
	public static double findRateFP(double future, double present, double timePeriod) {
		double e=2.71828;
		double futureLog=Math.log(future);
		double presentLog=Math.log(present);
		double difference=(futureLog-presentLog);
		double quotient = difference / timePeriod;
		//double test=difference/timePeriod-1;
		double rateFP=Math.pow(e, (quotient))-1;
		return rateFP;
	}
	public static double findRateFA(double future, double annual, double timePeriod) {
		double e=2.71828;
		double futureLog=Math.log(future);
		double annualLog=Math.log(annual);
		double difference=(annualLog-futureLog);
		double quotient = difference / timePeriod;
		
		//double test=difference/timePeriod-1;
		double rateFP=1-Math.sqrt(Math.pow(e, (quotient)));
		return rateFP;
	}
	//e^((log(f)/n)-(log(p))/n)-1
	
}

