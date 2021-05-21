package Backend;
import static config.Config.connUrl;
import static config.Config.password;
import static config.Config.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;


public class Reciept {
	
	public int id;
	public int company_id;
	public String client_company;
	public String client_name;
	public Connection con;
	public List<Item> items;
	
	private void connectToDatabase(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			this.con = DriverManager.getConnection(connUrl,user,password);
		}
			catch(Exception e) {	
				System.out.println(e+"Db connection");
			}
	}
	
	public Reciept() {
		this.company_id=0;
		this.id=0;
		this.client_company="";
		this.client_name="";
		this.items=new ArrayList<Item>();
		this.connectToDatabase();
	}
	
	public Reciept(int id, int company_id, String client_name, String client_company) {
		this.id=id;
		this.company_id=company_id;
		this.client_name=client_name;
		this.client_company=client_company;
		this.items=new ArrayList<Item>();
		
	}
	
	//Add an item to the reciept
	public void addItem(int quantity, String name,double price) {
		this.items.add(new Item(quantity,name,price));
	}
	
	//Get the list of reciepts of company of the admin who is logged in
	public List<Reciept> getReciepts() {
		List<Reciept> r=new ArrayList<Reciept>();
		try {
			Statement st = con.createStatement();
			String sql="select company_id from BPO.Admin where username='"+login.username+"'";
			ResultSet rs=st.executeQuery(sql);
			rs.next();
			int company_id=rs.getInt(1);
			sql="select id,company_id,client_name,client_company from BPO.Reciepts where company_id="+company_id;
			rs=st.executeQuery(sql);
			while(rs.next()) {
				r.add(new Reciept(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4)));
			}
			st.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return r;
		
	}
	
	//Get the detailed info of reciept given its id(Get details of all the items present in the reciept)
	public Reciept getReciept(int id){
		Reciept res=new Reciept();
		try {
			Statement st = con.createStatement();
			ResultSet rs;
			String sql="select id,company_id,client_name,client_company from BPO.Reciepts where id="+id;
			rs=st.executeQuery(sql);
			rs.next();
			res=new Reciept(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4));
			sql="select quantity, name, price from Reciepts as R JOIN Reciepts_Items as RI on R.id=RI.reciept_id join Items as I on (RI.item_id=I.id) where R.id="+id+";";
			rs=st.executeQuery(sql);
			while(rs.next()) {
				res.addItem(rs.getInt(1), rs.getString(2), rs.getDouble(3));
			}
			
			st.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return res;
	}
	
	
	//Add a reciept given the entire reciept given an entire reciept object
	public void addReciept(Reciept res) {
		try {
			Statement st = con.createStatement();
			String sql="select company_id from BPO.Admin where username='"+login.username+"'";
			ResultSet rs=st.executeQuery(sql);
			int ids[]=new int[res.items.size()];
			rs.next();
			int company_id=rs.getInt(1); int i=0;
			for(Item it:res.items)
			{
				sql="insert into Items(quantity,name,price) values("+it.quantity+",'"+it.name+"',"+it.price+");";
				st.executeUpdate(sql);
				sql="SELECT LAST_INSERT_ID();";
				rs=st.executeQuery(sql); rs.next();
				ids[i++]=rs.getInt(1);
			}
			
			sql="insert into Reciepts(company_id,client_name,client_company) values("+company_id+",'"+res.client_name+"','"+res.client_company+"');";
			st.executeUpdate(sql);
			sql="SELECT LAST_INSERT_ID();";
			rs=st.executeQuery(sql); rs.next();
			int reciept_id=rs.getInt(1);
			
			for(int id:ids) {
				
				sql="insert into Reciepts_Items(reciept_id,item_id) values("+reciept_id+","+id+");";
				st.executeUpdate(sql);	
			}
			st.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		
	}
	
	public String toString() {
		String s=this.client_company+" "+this.client_name+" "+this.company_id+"\n";
		for(Item i:this.items)
			s+=i.name+" "+i.quantity+" "+i.price+"\n";
		return s;
	}
	public void closeConnection() throws Exception{
		this.con.close();
	}
	
	

}
