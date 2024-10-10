package HRMSystem;

public class DepartmentHead extends Employee{
	private int subordinatesCount;

	//Constructor rỗng
	public DepartmentHead() {
		
	}
	
	//Constructor nha
	public DepartmentHead(String id, String fullName, String phoneNumber, int numOfWorkingDay) {
		super(id, fullName, phoneNumber, numOfWorkingDay);
	}
	
	public DepartmentHead(String id, String fullName, String phoneNumber, int numOfWorkingDay, double dailySalary,
			int subordinatesCount) {
		super(id, fullName, phoneNumber, numOfWorkingDay, dailySalary);
		this.subordinatesCount = 0;
	}

	public int getSubordinatesCount() {
		return subordinatesCount;
	}

	public void setSubordinatesCount(int subordinatesCount) {
		this.subordinatesCount = subordinatesCount;
	}
	
	public void increaseSubordinates() {
		subordinatesCount ++;
	}

	@Override
	public double salaryCaculation() {
		return dailySalary * numOfWorkingDay + 100 * subordinatesCount;
	}
	
	@Override
    public String toString() {
        return super.toString() + ", Subordinates Count: " + subordinatesCount;
    }
}
