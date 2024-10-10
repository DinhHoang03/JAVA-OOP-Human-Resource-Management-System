package HRMSystem;

public class RegularEmployee extends Employee{

	private DepartmentHead manager;
		
	public RegularEmployee() {
		
	}
	
	public RegularEmployee(String id, String fullName, String phoneNumber, int numOfWorkingDay) {
		super(id, fullName, phoneNumber, numOfWorkingDay);
	}
	
	public RegularEmployee(String id, String fullName, String phoneNumber, int numOfWorkingDay, double dailySalary,
			DepartmentHead manager) {
		super(id, fullName, phoneNumber, numOfWorkingDay, dailySalary);
		this.manager = null;
	}
	

	public void setManager(DepartmentHead manager) {
		this.manager = manager;
	}
	
	public DepartmentHead getManager() {
		return manager;
	}
	
	@Override
	public double salaryCaculation() {
		return dailySalary * numOfWorkingDay;
	}
}
