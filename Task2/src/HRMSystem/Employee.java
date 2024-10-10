package HRMSystem;

abstract class Employee {
	protected String id;
	protected String fullName;
	protected String phoneNumber;
	protected int numOfWorkingDay;
	protected double dailySalary;

	//Constructer rỗng
	public Employee() {
		
	}
	
	// Build constructor có tham số của employee để truyền thông tin nhân viên vào trong mảng (class main) 
	public Employee(String id, String fullName, String phoneNumber, int numOfWorkingDay) {
		this.id = id;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.numOfWorkingDay = numOfWorkingDay;
	}
	
	//Build tiếp một constructor phục vụ cho việc tính lương cho các role nhân viên(Subclass)
	public Employee(String id, String fullName, String phoneNumber, int numOfWorkingDay, double dailySalary) {
		this.id = id;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.numOfWorkingDay = numOfWorkingDay;
		this.dailySalary = dailySalary;
	}

	//Getter setter
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public int getNumOfWorkingDay() {
		return numOfWorkingDay;
	}


	public void setNumOfWorkingDay(int numOfWorkingDay) {
		this.numOfWorkingDay = numOfWorkingDay;
	}


	public double getDailySalary() {
		return dailySalary;
	}


	public void setDailySalary(double dailySalary) {
		this.dailySalary = dailySalary;
	}
	
	//Phương thức trừu tượng để tính lương tổng cho cả 3 roles
	public abstract double salaryCaculation();
	
	//Phương thức ghi đè chuỗi
	@Override
	public String toString() {
		return "Employee ID: " + id + ",Full Name: " + fullName + " ,Phone Number: " + phoneNumber + " ,Number of woring days: " + numOfWorkingDay + ",Daily salary: " + dailySalary + "$";
	}
}
