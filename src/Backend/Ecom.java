package Backend;

import static config.Config.connUrl;
import static config.Config.password;
import static config.Config.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Ecom {
	
public Connection con;
	
	public Ecom() {
		this.connectToDatabase();
	}
	
	private void connectToDatabase(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			this.con = DriverManager.getConnection(connUrl,user,password);
		}
			catch(Exception e) {	
				System.out.println(e+"Db connection");
			}
	}
	
	//List all the queries available
	public List<Query> getQueries(){
		List<Query> qrs=new ArrayList<Query>();
		try {
			Statement st = con.createStatement();
			String sql="select company_id from BPO.Admin where username='"+login.username+"'";
			ResultSet rs=st.executeQuery(sql);
			rs.next();
			int company_id=rs.getInt(1);
			sql="select * from BPO.Query where company_id="+company_id;
			rs=st.executeQuery(sql);
			while(rs.next()) {
				
				qrs.add(new Query(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
				
			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return qrs;
	}
	
	
	//List all the feedback
	public List<Feedback> getFeedbacks(){
		List<Feedback> fds=new ArrayList<Feedback>();
		try {
			Statement st = con.createStatement();
			String sql="select company_id from BPO.Admin where username='"+login.username+"'";
			ResultSet rs=st.executeQuery(sql);
			rs.next();
			int company_id=rs.getInt(1);
			sql="select * from BPO.Feedback where company_id="+company_id;
			rs=st.executeQuery(sql);
			while(rs.next()) {
				
				fds.add(new Feedback(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
				
			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return fds;
		
	}
	
	
	
	
	//Add a query
	public int addQuery(String name, String email, String query, String company_name) {
		
		try {
			Statement st = con.createStatement();
			String sql="select id from BPO.Company where name='"+company_name+"'";
			ResultSet rs=st.executeQuery(sql);
			rs.next();
			int company_id=rs.getInt(1);
			sql="insert into Query(company_id,name,email,query) values("+company_id+",'"+name+"','"+email+"','"+query+"')";
			st.executeUpdate(sql);
			return 1;
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return 0;
		
	}
	
	
	public int addFeedback(String name, String feedback, String company_name) {
		try {
			Statement st = con.createStatement();
			String sql="select id from BPO.Company where name='"+company_name+"'";
			ResultSet rs=st.executeQuery(sql);
			rs.next();
			int company_id=rs.getInt(1);
			sql="insert into Feedback(company_id,name,feedback) values("+company_id+",'"+name+"','"+feedback+"');";
			st.executeUpdate(sql);
			return 1;
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return 0;
	}
	
	
	public void Reply(int id, String reply) {
		try {
			Statement st = con.createStatement();
			String sql;
			ResultSet rs;
			String from, password, recipient,subject;
			
			password="MrudulPrabandh@123";
			from="heyareyouyell";
			subject="Reply to your query";
			sql="select email from Query where id="+id;
			rs=st.executeQuery(sql);
			rs.next();
			recipient=rs.getString(1);
			String[] to= { recipient};
			System.out.println(recipient);
			Mail m=new Mail();
			m.sendFromGMail(from, password, to, subject, reply);
			sql="delete from Query where id="+id;
			st.executeUpdate(sql);
			
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		
		
	}
	
	public void closeConnection() throws Exception{
		this.con.close();
	}

}
