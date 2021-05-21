package Backend;

import java.util.*;
//import java.util.Date;

import static config.Config.*;
import java.sql.*;
import java.sql.Date; 
import java.text.SimpleDateFormat;


public class Company {
	
	private int company_id;
	private String name;
	private String address;
	public boolean ch_data_entry, ch_accounts, ch_ecom;
	public Connection con;
	
	private void connectToDatabase(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			this.con = DriverManager.getConnection(connUrl,user,password);
		}
			catch(Exception e) {	
				System.out.println(e+"Db connection");
			}
	}
	
	public Company() {
		this.company_id=0;
		this.name="";
		this.address="";
		this.ch_data_entry=false;
		this.ch_accounts=false;
		this.ch_ecom=false;
		this.connectToDatabase();
	}
	
	public Company(int company_id, String name, String address, boolean ch1, boolean ch2, boolean ch3){
		this.company_id=company_id;
		this.name=name;
		this.address=address;
		this.ch_data_entry=ch1;
		this.ch_accounts=ch2;
		this.ch_ecom=ch3;
		
		this.connectToDatabase();
	}
	
	public void initialise(int company_id, String name, String address, boolean ch1, boolean ch2, boolean ch3) {
		this.company_id=company_id;
		this.name=name;
		this.address=address;
		this.ch_data_entry=ch1;
		this.ch_accounts=ch2;
		this.ch_ecom=ch3;
		
	}
	
	//Returns the services chosen by the company
	//In the format -"xyz" x,y,z->[0,1]
	//x - finance service, y-data entry service, z-ecom service
	public String serviceList(int id) throws Exception {
		Statement st = con.createStatement();
		String sql="select ch_finance,ch_data,ch_ecom from BPO.Company where id="+id; String res="";
		try {
			ResultSet rs =st.executeQuery(sql);
			while(rs.next()) {
				res+=rs.getInt(1)+""+rs.getInt(2)+""+rs.getInt(3);
			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		st.close();
		return res;
	}
	
	//Returns a list of company names
	//Use this for displaying dropdown for admin register
	public List<String> getCompanies(){
		List<String> ls=new ArrayList<String>();
		try {
			Statement st = con.createStatement();
			String sql="select name from BPO.Company";
			
			
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				ls.add(rs.getString(1));
			}
			st.close();
			
		}
		catch(Exception e) {
			System.out.println(e.toString());
			
		}
		
		return ls;	
	}
	
	//Returns the employees of the company.
	//Returns a list of admins
	public List<Employee> getEmployees(){
		
		
		List<Employee> employees=new ArrayList<Employee>();
		try {
			Statement st = con.createStatement();
			String sql="select company_id from BPO.Admin where username='"+login.username+"'";
			ResultSet rs=st.executeQuery(sql);
			rs.next();
			int company_id=rs.getInt(1);
			sql="select * from BPO.Employee where company_id="+company_id;
			
			rs=st.executeQuery(sql);
			while(rs.next()) {
				Employee temp;
				employees.add(new Employee(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getDate(7),rs.getDouble(8),rs.getInt(9),rs.getString(10)));
			}
			st.close();
			
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return employees;	
		
	}
	
	
	//Returns the employees of the company whose name includes parameter 'name'
	public List<Employee> getEmployees(String name){
		List<Employee> employees=new ArrayList<Employee>();
		try {
			Statement st = con.createStatement();
			String sql="select company_id from BPO.Admin where username='"+login.username+"'";
			ResultSet rs=st.executeQuery(sql);
			rs.next();
			int company_id=rs.getInt(1);
			sql="select * from BPO.Employee where company_id="+company_id+" and (first_name LIKE '%"+name+"%' or last_name LIKE '%"+name+"%' );";
			
			rs=st.executeQuery(sql);
			while(rs.next()) {
				Employee temp;
				employees.add(new Employee(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getDate(7),rs.getDouble(8),rs.getInt(9),rs.getString(10)));
			}
			st.close();
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return employees;
		
	}
 	
	
	//Returns 1 if employee added successfully
	//Returns 0 if employee wasn't added properly
	public int addEmployee(String first_name,String last_name,String designation,String department,String dobs,String dojs,double salary, int account_number) {
		
		try {
			Statement st = con.createStatement();
			String sql="select company_id from BPO.Admin where username='"+login.username+"'";
			ResultSet rs=st.executeQuery(sql);
			rs.next();
			int company_id=rs.getInt(1);
			Date doj=Date.valueOf(dojs);
			Date dob=Date.valueOf(dobs);
//			String date = new SimpleDateFormat("yyyy-MM-dd").format(dob);
//			System.out.println(date);
			 
			sql="insert into BPO.Employee(company_id,first_name,designation,department,dob,doj,salary,account_number,last_name) values("+company_id+",'"+first_name+"','"+designation+"','"+department+"','"+new java.sql.Date(dob.getTime())+"','"+new java.sql.Date(doj.getTime())+"',"+salary+","+account_number+",'"+last_name+"')";
			int res=st.executeUpdate(sql);
			
			st.close();
			return res;
			
		}
		catch(Exception e) {
			System.out.println(e);
			return 0;
			
		}
	}
	
	
	//Delete an employee
	//Returns 1 if deleted successfully
	//Returns 0 if not deleted successfully
	public int deleteEmployee(int id) {
		try {
			Statement st = con.createStatement();
			String sql="delete from BPO.Employee where id="+id;
			int res=st.executeUpdate(sql);
			st.close();
			return res;	
		}
		catch(Exception e) {		
			System.out.println(e);
			return 0;	
		}
	}
	

	
	public void testdb() throws Exception {
		System.out.println("Connection established--->"+this.con);
		Statement st = con.createStatement();
		ResultSet rs =st.executeQuery("select * from BPO.Company");

		while(rs.next()) {
			System.out.println(rs.getString("name")+"-->"+rs.getString("address"));
		}
		st.close();
		
	}
	
	public void closeConnection() throws Exception{
		this.con.close();
	}
	
}

