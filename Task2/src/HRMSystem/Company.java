package HRMSystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Company {
	
	//Employee emp = new RegularEmployee();
	
	private String name;
	private int taxCode;
	private double monthlyRevenue;
	private List<Employee> employees; //Danh sách các nhân viên trong công ty
	
	
	//Constructor rỗng cũng phải có List để có thể thực hiện lưu trữ và sử dụng các hàm của Arr
	public Company() {
		this.employees = new ArrayList<>();
	}
	
	//Build constructor như trên để nhập thông tin công ty
	public Company(String name, int taxCode, double monthlyRevenue) {
		this.name = name;
		this.taxCode = taxCode;
		this.monthlyRevenue = monthlyRevenue;
	}

	//Getter Setter
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getTaxCode() {
		return taxCode;
	}


	public void setTaxCode(int taxCode) {
		this.taxCode = taxCode;
	}


	public double getMonthlyRevenue() {
		return monthlyRevenue;
	}
	
	//Phương thức thêm nhân viên
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}

	//Phương thức xóa nhân viên
	public void removeEmployee(String empId) {
		for(int i = 0; i < employees.size(); i++) {
			//Lấy từng phần tử của mảng employees
			Employee emp = employees.get(i);
			//Nếu cái phần tử trong mảng đó bằng với tham số của chúng ta thì sẽ thực hiện xóa
			if(emp.getId().equals(empId)) {
				employees.remove(i);
				break;
			}
		}
	}
	
	//Lấy hết các nhân viên trong công ty
	public List<Employee> getAllEmployees(){
		return employees;
	}
	
	//Phương thức tìm nhân viên viên theo id
	public RegularEmployee findRegularEmpById(String id) {
		for(Employee emp : employees) {
			if(emp instanceof RegularEmployee && emp.getId().equals(id)) {
				return (RegularEmployee) emp;				
			}
		}
		return null;
	}

	//Phương thức tìm trưởng phòng theo id
	public DepartmentHead findDepartmentHeadById(String id) {
		for(Employee emp : employees) {
			if(emp instanceof DepartmentHead && emp.getId().equals(id)) {
				return (DepartmentHead) emp;
			}
		}
		
		return null;
	}
	
	//Phương thức tính tổng lương của cả cty
	public double caculateTotalSalary() {
		double totalSalary = 0;
		
		for(Employee emp : employees) {
			totalSalary += emp.salaryCaculation();
		}
		
		return totalSalary;
	}
	
	//Phương thức tìm nhân viên thường với mức lương cao nhất
	public double findHighestRegularEmpSalary() {
		
		double highestSalaryRE = 0;
		
		for(Employee emp : employees) {
			//Down-casting từ Employee xuống RegularEmployee
			if(emp instanceof RegularEmployee) {
				RegularEmployee employee = (RegularEmployee) emp;
				//Tạo biến lưu trữ lương nhân viên và so sánh nó với biến highestSalary
				double salary = employee.salaryCaculation();
				
				//Logic condition
				if(salary > highestSalaryRE) {
					highestSalaryRE = salary;
				}
			}
		}
		//Trả về giá trị cao nhất
		return highestSalaryRE;
	}
	
	//Tìm trưởng phòng quản lí nhiều nhân viên nhất
	public DepartmentHead findDepartmentHeadWithMostubordinates() {
		//Tạo 2 biến topDH để lưu trữ trường phòng quản lí nhiều nhân viên nhất có lưu trữ giá trị thông tin của trưởng phòng
		DepartmentHead topDH = null; 
		//Biến này để giá trị là -1 giống với giá trị null của topDH
		int maxSubordinates = -1; 
		for(Employee emp : employees) {
			//Down-casting xuống DepartmentHead và lấy số lương nhân viên để so sánh với biến maxSurbordinates
			if(emp instanceof DepartmentHead) {
				DepartmentHead manager = (DepartmentHead) emp;
				int subordinatesCount = manager.getSubordinatesCount();
				
				//Logic condition
				if(subordinatesCount > maxSubordinates) {
					maxSubordinates = subordinatesCount;
					topDH = manager;
				}
			}
		}
		return topDH;
	}
	
	//Hàm tìm giám đốc với cổ phiếu cao nhất trong công ty, hàm này hoạt động tương tự với hàm trên
	public Director findTheDirectorWithHighestSharePercentage() {
		Director topDirector = null;
		double highestCompanyShares = -1;
		
		for(Employee emp : employees) {
			if(emp instanceof Director) {
				Director director = (Director) emp;
				if(director.getCompanyShares() > highestCompanyShares) {
					highestCompanyShares = director.getCompanyShares();
					topDirector = director;
				}
				
			}
		}
		
		return topDirector;
	}
	
	//Hàm xếp nhân viên theo bảng chữ cái
	public void sortEmpByName() {
		Collections.sort(employees, new Comparator<Employee>() {

			//Hàm so sánh kí tự chuỗi của collection sort
			@Override
			public int compare(Employee emp1, Employee emp2) {
				return emp1.getFullName().compareToIgnoreCase(emp2.getFullName());
			}
		});
	}

	//Tương tự với hàm trên thì hàm này hiển thị danh sách nhân viên theo lương từ cao nhất đến thấp nhấp
	public void sortEmployeesBySalaryDesc() {
		Collections.sort(employees, new Comparator<Employee>() {
			//Hàm so sánh lương
			@Override
			public int compare(Employee emp1, Employee emp2) {
				return Double.compare(emp2.salaryCaculation(), emp1.salaryCaculation());
			}
		});
	}
	
	public void totalIncome() {
		for(Employee emp : employees) {
			if(emp instanceof Director) {
				Director director = (Director) emp;
				double totalIncome = director.salaryCaculation();
				System.out.println("Director: " + director.getFullName() + " - Income: " + totalIncome + "$");
			}
		}
	}
	
	//Setter
	public void setMonthlyRevenue(double monthlyRevenue) {
		this.monthlyRevenue = monthlyRevenue;
	}
	
	//Hàm ghi đè chuỗi hiển thị thông tin công ty ở case 1
	@Override
	public String toString() { 
		return "Name of your company: " + name + " and your company TaxCode " + taxCode + " and your monthly revenue as well " + monthlyRevenue + "$";
	}
	
}
