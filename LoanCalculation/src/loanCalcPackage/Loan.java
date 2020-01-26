package loanCalcPackage;

public class Loan {
	private double interest;
	private double amountBorrowed;
	private double rateOfAccrual;
	private double amountAccrued;
	public Loan(double interest, double amountBorrowed) {
		this.interest = (interest/100)/365;
		this.amountBorrowed = amountBorrowed;
		this.rateOfAccrual=this.amountBorrowed*this.interest;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest/100;
	}
	public double getAmountBorrowed() {
		return amountBorrowed;
	}
	public void setAmountBorrowed(double amountBorrowed) {
		this.amountBorrowed = amountBorrowed;
	}
	public double getRateOfAccrual() {
		return rateOfAccrual;
	}
	public void setRateOfAccrual(double rateOfAccrual) {
		this.rateOfAccrual = rateOfAccrual;
	}
	public double getAmountAccrued() {
		return this.amountAccrued;
	}
	public void calculateAmountAccrued(int days) {
		this.amountAccrued+=rateOfAccrual*days;
	}
	
}
