package HRMSystem;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		//Thêm nhân viên và trưởng phòng vào danh sách trong công ty
		Company company = new Company();
		//Đối tượng rỗng
		Employee employee = null;
		
		Scanner sc = new Scanner(System.in);
		
		//Menu options
		System.out.println("Enter your number between 1 and 12 to pick up a option right down bellow here: ");
		
		while(true) {
			System.out.println("1. Enter company information.");
            System.out.println("2. Assign Regular Employees to Department Heads.");
            System.out.println("3. Add or delete employee information.");
            System.out.println("4. Output information for all employees in the company.");
            System.out.println("5. Calculate and display the total salary for the entire company.");
            System.out.println("6. Find the Regular Employee with the highest salary.");
            System.out.println("7. Find the Department Head with the highest number of subordinates.");
            System.out.println("8. Sort all employees in the company in alphabetical order.");
            System.out.println("9. Sort all employees in the company by descending salary.");
            System.out.println("10. Find the Director with the highest share percentage.");
            System.out.println("11. Calculate and display the total income for each Director.");
            System.out.println("12. Exit.");
            System.out.print("Choose an option: ");
            System.out.println();
            
            int option = sc.nextInt();
            sc.nextLine(); // clear Scanner line.
           
            switch(option) {
            case 1:
            	//Nhập thông tin công ty
                System.out.print("Type your company name here: ");
                String name = sc.next();
                sc.nextLine();
                System.out.print("Type your company tax code here: ");
                int taxCode = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter your monthly revenue of your company here: ");
                double monthlyRevenue = sc.nextDouble();
                sc.nextLine();
                //Khởi tạo đối tượng với 3 tham số truyền vào
                company = new Company(name, taxCode, monthlyRevenue);
                
                //In ra màn hình kết quả thông tin công ty
                System.out.println();
                System.out.println(company.toString());
                System.out.println("Your company information has been updated.");
                
                sc.nextLine();
                break;
                
            case 2:
                // Phân công nhân viên thường cho trưởng phòng
                System.out.println("Enter your employee ID here: ");
                String employeeId = sc.next();
                sc.nextLine();
                System.out.println("Enter your head department ID here: ");
                String headDepartmentId = sc.next();
                sc.nextLine();
                
                //Bắt đầu thực hiện ghép nối giữa việc tìm quản lí phòng và tìm nhân viên trong công ty xem có tồn tại trong List "employees" trong công ty không
                RegularEmployee regularEmployee = company.findRegularEmpById(employeeId);
                DepartmentHead departmentHead = company.findDepartmentHeadById(headDepartmentId);

                //Cài logic cho hàm này, nếu cả hai nhân viên này có tồn tại thì sẽ thực hiện việc ghép nối nhân viên làm cho trưởng phòng này
                if(regularEmployee != null && departmentHead != null) {
                	//Truyền cho hàm setManager này tham số là trưởng phòng đó vào hàm để ghép nối
                    regularEmployee.setManager(departmentHead);
                    //Sau đó sẽ lập tức kích hoạt hàm increaseSubordinates để có tăng một đơn vị nhân viên dưới quyền cho mình
                    departmentHead.increaseSubordinates();
                    //In ra màn hình 
                    System.out.println("Phân công việc nhân viên " + regularEmployee.getFullName() + " cho trưởng phòng " + departmentHead.getFullName() + " thành công.");
                }
                //Trường hợp không tìm thấy nhân viên và trường phòng theo điều kiện của bài toán 
                else {
                    System.err.println("Phân công việc thất bại! Không tìm thấy nhân viên hoặc trưởng phòng.");
                }
                break;

            case 3:
                // Thêm hoặc xóa thông tin nhân viên
            	System.out.println("Hãy lựa chọn tùy chọn sau để có thể dùng: ");
            	System.out.println("Lựa chọn 1: Thêm nhân viên.");
            	System.out.println("Lựa chọn 2: Xóa nhân viên.");
            	
            	//Tạo biến supOption để người dùng nhập giá trị cần tương tác với hệ thống
            	int subOption = sc.nextInt();
            	
            	sc.nextLine();
            	
            	//Lựa chọn 1: Thêm nhân viên
            	if(subOption == 1) {
            		System.out.println("Enter the employee ID here: ");
            		String id = sc.next();
            		System.out.println("Enter the employee full-name here: ");
            		String fullName = sc.next();
            		System.out.println("Enter the employee phonenumber here: ");
            		String phoneNumber = sc.next();
            		System.out.println("Enter the number of working days of your employee here:");
            		int workingDays = sc.nextInt();
            		
            		sc.nextLine();
            	
            		//Hiển thị các role choice cho người dùng nhập
            		System.out.println("Hãy lựa chọn các role tương ứng dưới đây cho nhân viên công ty bạn:");
            		System.out.println("Lựa chọn 1: Regular Employee.");
            		System.out.println("Lựa chọn 2: Department Head.");
            		System.out.println("Lựa chọn 3: Director.");
            		
            		int role = sc.nextInt();
            		
            		sc.nextLine();
            		
            		switch(role) { //Lựa chọn role tương ứng để lấy constructor của Employee và set lương tương ứng cho họ như điều kiện đã đặt sẵn ở dưới đây
            			case 1:
            				employee = new RegularEmployee(id, fullName, phoneNumber, workingDays);
            				employee.setDailySalary(100);
            				break;
            				
            			case 2:
            				employee = new DepartmentHead(id, fullName, phoneNumber, workingDays);
            				employee.setDailySalary(200);
            				break;
            				
            			case 3:
            				System.out.print("Enter Shares Percentage: ");
            				double companyShares = sc.nextDouble();
            				sc.nextLine();
            				employee = new Director(id, fullName, phoneNumber, workingDays, role, companyShares);
            				employee.setDailySalary(300);
            				break;
            		}
            			if(employee != null) {
            				company.addEmployee(employee);
            				System.out.println("Bạn đã thêm nhân viên thành công cho công ty của bạn.");
            				System.out.println();
            			}
            		//Lựa chọn 2: Xóa nhân viên
            	} else if(subOption == 2) {
            		String deleteEmpId;
            		System.out.println("Nhập id để xóa nhân viên đó: ");
            		deleteEmpId = sc.next();   
            		//Truyền tham số id của nhân viên có trong List của class Employee để thực hiện xóa
            		company.removeEmployee(deleteEmpId);
            		System.out.println("Đã xóa nhân viên thành công.");
            		System.out.println();
            	}
            	//Lựa chọn không đúng, quay về trang chủ
            	else {
            		System.err.println("This is a valid number, please choose only between 1 and 2, or else return to the menu sections!!");
            		break;
            	}
            	
                break;
                
                
            case 4:
                // Xuất thông tin tất cả nhân viên
            	System.out.println("Thông tin của các nhân viên: ");
            	for(Employee emp : company.getAllEmployees()) {
            		System.out.println(emp.toString());
            	}
            	
                break;
            case 5:
                // Tính và hiển thị tổng lương của công ty
            	double totalSalary = company.caculateTotalSalary();
            	System.out.println("Here is the total salary of your company: " + totalSalary + "$");
            	
                break;
            case 6:
                // Tìm nhân viên thường có lương cao nhất
            	double highestRegularEmployee = company.findHighestRegularEmpSalary();
            	//Đặt điều kiện nếu highestRegularEmployee khác không thì lập tức hàm này sẽ chạy để lấy tên nhân viên có lương cao nhất và mức lương của họ
            	if(highestRegularEmployee != 0) {
            		System.out.println("This is the hightest salary from a Regular Employee: " + employee.getFullName() + " with the total salary of "+ highestRegularEmployee + "$");
            	}else {
            		System.err.println("No information found !!");
            	}
                break;
            case 7:
                // Tìm trưởng phòng có số lượng nhân viên dưới quyền nhiều nhất
            	DepartmentHead headWithMostSubordinates = company.findDepartmentHeadWithMostubordinates();
            	//Nếu có quản lí trong công ty thì sẽ chạy tìm quản lí có đông nhân viên nhất
            	if(headWithMostSubordinates != null) {
            		System.out.println("This is the Head Department with the most subordinates in the company: ");
            		System.out.println(headWithMostSubordinates);
            	}else {
            		System.err.println("There is no information about this stagement!");
            	}
            	break;
            case 8:
                // Sắp xếp nhân viên theo thứ tự chữ cái
            	company.sortEmpByName();
            	System.out.println("Danh sách nhân viên theo bảng chữ cái");
            	
            	for(Employee emp : company.getAllEmployees()) {
            		System.out.println(emp.getFullName());
            	}
                break;
            case 9: 
                // Sắp xếp nhân viên theo lương giảm dần
            	company.sortEmployeesBySalaryDesc();           	
            	System.out.println("Danh sách nhân viên theo lương giảm dần: ");
            	
            	for(Employee emp : company.getAllEmployees()) {
            		System.out.println(emp.getFullName() + " and " + emp.salaryCaculation());
            	}
                break;
            case 10:
                // Tìm giám đốc có tỷ lệ cổ phần cao nhất
            	Director directorWithTheMostCompanyShares = company.findTheDirectorWithHighestSharePercentage();
            	if(directorWithTheMostCompanyShares != null) {
            		System.out.println("This is the Director with the highest company shares percentage: ");
            		System.out.println(directorWithTheMostCompanyShares);
            	}else {
            		System.err.println("There is no information about this stagement!");
            	}
            	
                break;
            case 11:
                // Tính và hiển thị thu nhập của giám đốc
            	company.totalIncome();
                break;
            case 12:
                System.out.println("Exiting..");
                return;
            default:
            	System.err.println("Valid number type. You can only type in lines of 1 to 12 !!!");
            }
		}
		
	}
}
