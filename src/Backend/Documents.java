package Backend;

import static config.Config.connUrl;
import static config.Config.password;
import static config.Config.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Documents {
	
	public int company_id;
	public int id;
	public String path;
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
	
	public Documents() {
		this.connectToDatabase();
	}
	
	
	public Documents(int id,int company_id,String path) {
		
		this.company_id=company_id;
		this.id=id;
		this.path=path;
		
	}
	
	
	//Get the file paths
	public List<String> getFilePaths(){
		List<String> files=new ArrayList<String>();
		try {
			Statement st = con.createStatement();
			String sql="select company_id from BPO.Admin where username='"+login.username+"'";
			ResultSet rs=st.executeQuery(sql);
			rs.next();
			int company_id=rs.getInt(1);
			sql="select path from Documents where company_id="+company_id;
			rs=st.executeQuery(sql);
			while(rs.next()) {
				files.add(rs.getString(1));
			}
			
			st.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return files;
		
	}
	
	
	//Add a file to a company
	public int addFile(String s) {
		try {
			String path="",check="";
			for(int i=0; i<s.length(); i++) {
				if(s.charAt(i)=='\\') {
					path+='\\';
					check+="\\\\";
				}
				else{ path+=s.charAt(i);
					check+=s.charAt(i);
				}
			}
			Statement st = con.createStatement();
			String sql="select company_id from BPO.Admin where username='"+login.username+"'";
			ResultSet rs=st.executeQuery(sql);
			rs.next();
			int company_id=rs.getInt(1);
			sql="select path from Documents where company_id="+company_id+" and path LIKE '"+check+"';";
			System.out.println(sql);
			rs=st.executeQuery(sql);
			
			if(rs.next()==true) return 0;
			
			sql="insert into Documents(company_id,path) values("+company_id+",'"+path+"');";
			System.out.println(sql);
			st.executeUpdate(sql);
			
		}
		catch(Exception e) {
			System.out.println(e);
			
		}
		return 1;
		
	}


}
