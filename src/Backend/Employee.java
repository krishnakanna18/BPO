package Backend;

import java.util.Date;

public class Employee {
	
	public int id; //Id of the employee
	public int company_id; //Company id
	public String first_name; 
	public String last_name;
	public String designation;
	public String department;
	public Date dob;
	public Date doj;
	public double salary;
	public int account_number;
	public boolean sal_given;
	
	
	public Employee(int id,int company_id,String first_name,String designation,String department,Date dob,Date doj,double salary, int account_number,String last_name) {
		
		this.id=id;
		this.company_id=company_id;
		this.first_name=first_name;
		this.last_name=last_name;
		this.designation=designation;
		this.department=department;
		this.dob=dob;
		this.doj=doj;
		this.salary=salary;
		this.account_number=account_number;
	
	}
	
	
	public Employee(String first_name, String last_name, String designation, double salary, boolean sal_given, int id) {
		this.first_name=first_name;
		this.last_name=last_name;
		this.designation=designation;
		this.salary=salary;
		this.sal_given=sal_given;
		this.company_id=id;
	}
	public String toString() {
		return this.first_name+" "+this.last_name+" "+this.designation+" "+this.salary;
	}
	
	
	
	
	
}
