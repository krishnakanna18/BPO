package Backend;

import java.sql.*;
import java.util.*;
import static config.Config.*;

public class Admin {
	
	private int id;
	private int company_id;
	private String username;
	public boolean role;
	public Company cp;				//Company admin is working at
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
	
	//Empty admin class to first initialize from db
	public Admin() {
		this.id=-1;
		this.company_id=-1;
		this.username="";
		this.role=false;
		this.connectToDatabase();
	}
	
	public Admin(int id, int company_id, String username, boolean role) {
		this.id=id;
		this.company_id=company_id;
		this.username=username;
		this.role=role;
		this.connectToDatabase();
	}
	
	//Initialize the class after retrieving from db
	public void initialise(int id, int company_id, String username, boolean role) {
		this.id=id;
		this.company_id=company_id;
		this.username=username;
		this.role=role;	
	}
	
	//Returns 1-Successful login
	//		  0- Wrong password
	//		  -1 - Admin doesn't exist
	public int login(String username, String password){
		
		try {
			Statement st = con.createStatement();
			String sql="select * from BPO.Admin where username='"+username+"';";
			ResultSet rs=st.executeQuery(sql);
			
			if(!rs.next())
				return -1;
			if(!rs.getString(4).equals(password))
				return 0;
			
			//If the information is right the class is initialized with the admin's values
			this.initialise(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getBoolean(5));
			login.username=username;
			login.loggedin=true;
			return 1;
			
		}
		catch(Exception e) {
			System.out.println(e);
			
			return -1;
		}
		
	}
	
	//Method to register an admin
	//Returns 1 if successfully registered
	//Else returns 0
	public int register(String username, String password, String company_name, boolean role) {
		
		int ans;
		try {
			Statement st = con.createStatement();
			String sql="select id from BPO.Company where name='"+company_name+"';";
			ResultSet res=st.executeQuery(sql);
			res.next();
			int company_id=res.getInt(1);
			sql="insert into BPO.Admin( company_id, username, password, role) values('"+company_id+"','"+username+"','"+password+"',"+role+");";
			int rs=st.executeUpdate(sql);
			this.initialise(-1,company_id,username,role);
			ans=1;
			login.username=username;
			login.loggedin=true;
			st.close();
		}
		catch(Exception e) {
			System.out.println(e);
			ans=-1;
		}
		
		return ans;
		
	}
	
	public void closeConnection() throws Exception{
		this.con.close();
	}
		

}
