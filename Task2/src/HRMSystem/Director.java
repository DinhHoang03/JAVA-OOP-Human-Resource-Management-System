package HRMSystem;

public class Director extends Employee{
	
	private double companyShares;

	public Director(String id, String fullName, String phoneNumber, int numOfWorkingDay, double dailySalary,
			double companyShares) {
		super(id, fullName, phoneNumber, numOfWorkingDay, dailySalary);
		if(companyShares < 100) {
			this.companyShares = companyShares;
		}
	}

	public double getCompanyShares() {
		return companyShares;
	}

	public void setCompanyShares(double companyShares) {
		this.companyShares = companyShares;
	}

	@Override
	public double salaryCaculation() {
		return dailySalary * numOfWorkingDay;
	}
}
