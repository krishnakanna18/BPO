package Backend;
import static config.Config.connUrl;
import static config.Config.password;
import static config.Config.user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Transactions {
	
	public int target;
	public double amount;
	public int id;
	public int company_id;
	public String purpose;
	public Date date;
	public String type;
	public 	enum Months{
		JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC
	};
	
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
	

	
	public Transactions() {
		this.connectToDatabase();
	}
	
	//Initialise the transactions
	public Transactions(int id,int target, String type,double amount,int company_id, String purpose, Date date) {
			
		this.company_id=company_id;
		this.target=target;
		this.amount=amount;
		this.purpose=purpose;
		this.date=date;
		this.id=id;
		this.type=type;
//		Date doj=Date.valueOf(dojs);

	}
	
	//Get the transaction list
	public List<Transactions> getTransactions(){
		
		List<Transactions> trs=new ArrayList<Transactions>();
		try {
			Statement st = con.createStatement();
			String sql="select company_id from BPO.Admin where username='"+login.username+"'";
			ResultSet rs=st.executeQuery(sql);
			rs.next();
			int company_id=rs.getInt(1);
			sql="select * from BPO.Transactions where company_id="+company_id;
			rs=st.executeQuery(sql);
			while(rs.next()) {
				trs.add(new Transactions(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDouble(4),rs.getInt(5),rs.getString(6),rs.getDate(7)));
			}
			st.close();
			
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return trs;
	}
	
	//Get the transactions from start to end date
	public List<Transactions> getTransactions(String start, String end){
		List<Transactions> trs=new ArrayList<Transactions>();
		try {
			Statement st = con.createStatement();
			String sql="select company_id from BPO.Admin where username='"+login.username+"'";
			ResultSet rs=st.executeQuery(sql);
			rs.next();
			int company_id=rs.getInt(1);
			Date strt=Date.valueOf(start);
			Date en=Date.valueOf(end);
			sql="select * from BPO.Transactions where company_id="+company_id+" and dos BETWEEN '"+strt+"' and '"+en+"';";
			rs=st.executeQuery(sql);
			while(rs.next()) {
				trs.add(new Transactions(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDouble(4),rs.getInt(5),rs.getString(6),rs.getDate(7)));
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return trs;
	}
	
	//Get a particular transaction
	public Transactions getTransactions(int id) {
		
		Transactions tr=new Transactions();
		try {
			Statement st = con.createStatement();
			String sql="select company_id from BPO.Admin where username='"+login.username+"'";
			ResultSet rs=st.executeQuery(sql);
			rs.next();
			int company_id=rs.getInt(1);
			sql="select * from BPO.Transactions where company_id="+company_id+" and id="+id;
			rs=st.executeQuery(sql);
			if(rs.next()) {
				tr=new Transactions(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDouble(4),rs.getInt(5),rs.getString(6),rs.getDate(7));
			}
			st.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return tr;
		
		
	}
	
	
	//Adding a transaction
	public void addTransactions(int target, String type,double amount,String purpose, String date) {
		
		try {
			Statement st = con.createStatement();
			String sql="select company_id from BPO.Admin where username='"+login.username+"'";
			ResultSet rs=st.executeQuery(sql);
			rs.next();
			int company_id=rs.getInt(1);
			Date dos=Date.valueOf(date);
			sql="insert into BPO.Transactions(target,type,amount,purpose,dos,company_id) values("+target+",'"+type+"',"+amount+",'"+purpose+"','"+dos+"',"+company_id +");";
			int n=st.executeUpdate(sql);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	
	//Generate the report for transactions given the type and year
	//Type should match either "Debit" or "Credit", year should be an integer 
	//Returns an Map object.
	public Map<String,Double> report(String type, int year){
		Map<String,Double> mp=new HashMap<String,Double>();
		try { 
			Statement st = con.createStatement();
			String sql="select company_id from BPO.Admin where username='"+login.username+"'";
			ResultSet rs=st.executeQuery(sql);
			rs.next();
			int company_id=rs.getInt(1);
			sql="select MONTH(dos), SUM(amount) from Transactions where company_id="+company_id+" and YEAR(dos)="+year+" and type='"+type+"' group by MONTH(dos);";
			rs=st.executeQuery(sql);
			while(rs.next()) 
				mp.put(String.valueOf(Months.values()[rs.getInt(1)-1]), rs.getDouble(2));
			
			st.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return mp;
	}
	
	
	//Generate the report for transactions give year, type and month
	//Type should match either "Debit" or "Credit", year should be an integer 
	//Returns an Map object.
	public Map<Integer,Double> report(String type, int year, int month){
		Map<Integer,Double> mp=new HashMap<Integer,Double>();
		try { 
			Statement st = con.createStatement();
			String sql="select company_id from BPO.Admin where username='"+login.username+"'";
			ResultSet rs=st.executeQuery(sql);
			rs.next();
			int company_id=rs.getInt(1);
			sql="select DAY(dos), SUM(amount) from Transactions where company_id="+company_id+" and YEAR(dos)="+year+" and MONTH(dos)="+month+" and type='"+type+"' group by DAY(dos);";
			rs=st.executeQuery(sql);
			while(rs.next()) {
				mp.put(rs.getInt(1), rs.getDouble(2));
			}
			st.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return mp;
	}
	
	
	//Payroll get sakaries 
	public List<Employee> getSalaryList(){
		List<Employee> emps=new ArrayList<Employee>();
		try {
			Statement st = con.createStatement();
			String sql="select company_id from BPO.Admin where username='"+login.username+"'";
			ResultSet rs=st.executeQuery(sql);
			rs.next();
			int company_id=rs.getInt(1);
			sql="select first_name, last_name, designation, salary, sal_given,id from BPO.Employee where company_id="+company_id;
			rs=st.executeQuery(sql);
			while(rs.next()) {
				emps.add(new Employee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getBoolean(5),rs.getInt(6)));
			}
			st.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return emps;
	}
	
	//Get salaries
	public int distributeSalaries() {
		
		try {
			Statement st = con.createStatement();
			String sql="select company_id from BPO.Admin where username='"+login.username+"'";
			ResultSet rs=st.executeQuery(sql);
			rs.next(); 
			int sal=0,balance=0;
			int company_id=rs.getInt(1);
			sql="select SUM(salary) from Employee where company_id="+company_id+" and sal_given=0";
			rs=st.executeQuery(sql);
			if(rs.next()) {
				sal=rs.getInt(1);
			}
			sql="select balance from Account where company_id="+company_id;
			rs=st.executeQuery(sql);
			if(rs.next()) {
				balance=rs.getInt(1);
			}
			System.out.println(sal+":"+balance);
			
			if(balance<sal)
				return 0;
			sql="update BPO.Employee set sal_given=1 where company_id="+company_id+" and sal_given=0";
			st.executeUpdate(sql);
			
			balance-=sal;
			sql="update Account set balance="+balance+" where company_id="+company_id;
			st.executeUpdate(sql);
			
			st.close();
			return 1;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return 1;
	}
	
	
	//Distribute the salary for a given list of employees
	public int distributeSalaries(int[] arr) {

		try {
			Statement st = con.createStatement();
			String sql="select company_id from BPO.Admin where username='"+login.username+"'";
			ResultSet rs=st.executeQuery(sql);
			rs.next(); 
			int sal=0,balance=0;
			int company_id=rs.getInt(1);
			String ids="";
			for(Integer i:arr) ids+= i+",";
			ids=ids.substring(0, ids.length()-1);
			System.out.println(ids);
			
			sql="select SUM(salary) from Employee where company_id="+company_id+" and sal_given=0 and id in ( "+ids+" );";
			rs=st.executeQuery(sql);
			if(rs.next()) {
				sal=rs.getInt(1);
			}
			sql="select balance from Account where company_id="+company_id;
			rs=st.executeQuery(sql);
			if(rs.next()) {
				balance=rs.getInt(1);
			}
			System.out.println(sal+":"+balance);
			
			if(balance<sal)
				return 0;
			sql="update BPO.Employee set sal_given=1 where company_id="+company_id+" and sal_given=0 and id in ( "+ids+" );";
			st.executeUpdate(sql);
			
			balance-=sal;
			sql="update Account set balance="+balance+" where company_id="+company_id;
			st.executeUpdate(sql);
					
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return 1;
	}
	
	
	
	
	
	
	//get current balance
	public double curBalance() {
		try {
			Statement st = con.createStatement();
			String sql="select company_id from BPO.Admin where username='"+login.username+"'";
			ResultSet rs=st.executeQuery(sql);
			rs.next(); 
			double balance=0;
			int company_id=rs.getInt(1);
			sql="select balance from Account where company_id="+company_id;
			rs=st.executeQuery(sql);
			if(rs.next()) {
				balance=rs.getDouble(1);
			}
			return balance;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return 0;
	}
	
	//Get the total salary to be distributed
	public double totSalary() {
		try {
			Statement st = con.createStatement();
			String sql="select company_id from BPO.Admin where username='"+login.username+"'";
			ResultSet rs=st.executeQuery(sql);
			rs.next(); 
			double sal=0;
			int company_id=rs.getInt(1);
			sql="select SUM(salary) from Employee where company_id="+company_id+" and sal_given=0";
			rs=st.executeQuery(sql);
			if(rs.next()) {
				sal=rs.getDouble(1);
			}
			return sal;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return 0;
	}
	

	public String toString() {
		return this.purpose+":"+this.type+":"+this.amount+":"+this.date;
	}
	
	
	

}
